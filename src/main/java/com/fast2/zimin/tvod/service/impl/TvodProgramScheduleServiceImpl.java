package com.fast2.zimin.tvod.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fast2.zimin.tvod.dao.TvodEpgLoadHistoryDao;
import com.fast2.zimin.tvod.entity.ServiceInfo;
import com.fast2.zimin.tvod.entity.TvodEpgLoadHistory;
import com.fast2.zimin.tvod.service.TvodProgramScheduleService;
import com.fast2.zimin.util.Constants;
import com.fast2.zimin.util.DateUtil;
import com.fast2.zimin.util.FileUtilities;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class TvodProgramScheduleServiceImpl implements TvodProgramScheduleService {
	
	@Autowired
	TvodEpgLoadHistoryDao tvodEpgLoadHistoryDao;

	@Override
	public List<ServiceInfo> getServiceInfo(Document programScheduleDoc) throws Exception {
		
		List<ServiceInfo> tvodServiceInfoList = new ArrayList<ServiceInfo>();
		
		programScheduleDoc.getDocumentElement().normalize();
		
		// 1. Get 'ProgramGuide' node list. 
		NodeList programGuideList = programScheduleDoc.getElementsByTagName("ProgramGuide");
		if(programGuideList.getLength() == 0)
		{
			throw new Exception("There is no 'ProgramGuide' in this file.");
		}

		for(int pgIdx = 0 ; pgIdx < programGuideList.getLength() ; pgIdx++)
		{
			ServiceInfo serviceInfo = new ServiceInfo();

			Node programGuideNode = programGuideList.item(pgIdx);
			
			if(programGuideNode.getNodeType() == Node.ELEMENT_NODE)
			{
				serviceInfo = setServiceInfoAttribute(serviceInfo, (Element)programGuideNode);
				serviceInfo = setScheduleInfoAttribute(serviceInfo, (Element)programGuideNode);
				serviceInfo = setProgramInfoAttribute(serviceInfo, (Element)programGuideNode);
			}
			
			tvodServiceInfoList.add(serviceInfo);
		}
		
		return tvodServiceInfoList;
	}

	@Override
	public Map<String, List<ServiceInfo>> separateServiceInfo(List<ServiceInfo> serviceInfoList) throws Exception {

		List<ServiceInfo> tvodServiceInfoList = new ArrayList<ServiceInfo>();
		List<ServiceInfo> tstvServiceInfoList = new ArrayList<ServiceInfo>();
		
		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		for(int i = 0 ; i < serviceInfoList.size() ; i++)
		{
			ServiceInfo serviceInfo = serviceInfoList.get(i);
			
			// root element - ServiceInfo
			Document doc = docBuilder.newDocument();
			Element serviceInfoElement = doc.createElement("ServiceInfo");
			doc.appendChild(serviceInfoElement);
			
			String isTVOD = serviceInfo.getAttibuteMap().get("IsTVOD");
			String isTimeShift = serviceInfo.getAttibuteMap().get("TimeShift");
			
			if(!isTVOD.equals("0"))
			{
				tvodServiceInfoList.add(serviceInfo);
			}
			
			if(!isTimeShift.equals("0"))
			{
				tstvServiceInfoList.add(serviceInfo);
			}
		}

		Map<String, List<ServiceInfo>> serviceInfoListMap = new HashMap<String, List<ServiceInfo>>();
		
		serviceInfoListMap.put(Constants.TVODANDTSTV.TYPE_NAME_TVOD, tvodServiceInfoList);
		serviceInfoListMap.put(Constants.TVODANDTSTV.TYPE_NAME_TSTV, tstvServiceInfoList);
		
		return serviceInfoListMap;
	}
	
	@Override
	public void createServiceInfoXml(
			List<ServiceInfo> serviceInfoList,
			String destDirRoot,
			String sourceFileName,
			String loadMethod,
			String tvodFtpHost,
			String tvodFtpUser,
			String tvodFtpPassword,
			String tvodFtpTargetDir,
			String tvodFtpTempDir) throws Exception {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		boolean bFtp = false;
		if(loadMethod.equals(Constants.FILEUTILS.TVOD_FILE_LOAD_METHOD_FTP))
		{
			bFtp = true;
		}
		
		for(int i = 0 ; i < serviceInfoList.size() ; i++)
		{
			ServiceInfo serviceInfo = serviceInfoList.get(i);
			
			// root element - ServiceInfo
			Document doc = docBuilder.newDocument();
			Element serviceInfoElement = doc.createElement("ServiceInfo");
			doc.appendChild(serviceInfoElement);
			
			File destDirPath = new File(destDirRoot + "/" + serviceInfo.getAttibuteMap().get("ID"));
			if(!destDirPath.exists())
			{
				destDirPath.mkdir();
			}
			
			List<String> serviceInfoAttributesList = new ArrayList<String>(serviceInfo.getAttibuteMap().keySet());
			Map<String, String> serviceInfoAttributeMap = serviceInfo.getAttibuteMap();
			for(int j = 0 ; j < serviceInfoAttributesList.size() ; j++)
			{
				String attribute = serviceInfoAttributesList.get(j);
				Attr attr = doc.createAttribute(attribute);
				attr.setValue(serviceInfoAttributeMap.get(attribute));
				serviceInfoElement.setAttributeNode(attr);
			}
			
			// ScheduleInfo element
			Element scheduleInfoElement = doc.createElement("ScheduleInfo");
			serviceInfoElement.appendChild(scheduleInfoElement);
			List<String> scheduleInfoAttributesList = new ArrayList<String>(serviceInfo.getScheduleInfo().getAttibuteMap().keySet());
			Map<String, String> scheduleInfoAttributesMap = serviceInfo.getScheduleInfo().getAttibuteMap();
			for(int j = 0 ; j < scheduleInfoAttributesList.size() ; j++)
			{
				String attribute = scheduleInfoAttributesList.get(j);
				Attr attr = doc.createAttribute(attribute);
				attr.setValue(scheduleInfoAttributesMap.get(attribute));
				scheduleInfoElement.setAttributeNode(attr);
			}
			
			// ProgramInfo elements
			List<Map<String, String>> programInfoMapList = serviceInfo.getScheduleInfo().getProgramInfoList();
			for(int j = 0 ; j < programInfoMapList.size() ; j++)
			{
				Element programInfoElement = doc.createElement("ProgramInfo");
				scheduleInfoElement.appendChild(programInfoElement);
				List<String> programInfoNodeList = new ArrayList<String>(serviceInfo.getScheduleInfo().getProgramInfoList().get(j).keySet());
				Map<String, String> programInfoMap = serviceInfo.getScheduleInfo().getProgramInfoList().get(j);
				for(int k = 0 ; k < programInfoNodeList.size() ; k++)
				{
					String nodeName = programInfoNodeList.get(k);
					Element piSubElement = doc.createElement(nodeName);
					piSubElement.appendChild(doc.createTextNode(programInfoMap.get(nodeName)));
					programInfoElement.appendChild(piSubElement);
				}
			}
			
			// write the content into xml file
//			String destFileName = String.format("%s/%s_SI_%04d.xml", destDirPath, i+1, sourceFileName.substring(0, sourceFileName.length()-4));
			String destFileName = String.format("%s/%s_SI_%04d.xml", destDirPath, FilenameUtils.removeExtension(sourceFileName), i+1);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(destFileName));
			transformer.transform(source, result);
			
			if(bFtp)
			{
				FileUtilities.ftpPut(
						tvodFtpHost, 
						tvodFtpUser, 
						tvodFtpPassword, 
						destFileName, 
						tvodFtpTargetDir,
						tvodFtpTempDir,
						Constants.FILEUTILS.FTP_MODE_PASSIVE);
			}
		}
	}

	@Override
	public void createTvodServiceInfoXml(
			List<ServiceInfo> serviceInfoList,
			String destDirRoot,
			String sourceFileName,
			String loadMethod,
			String tvodFtpHost,
			String tvodFtpUser,
			String tvodFtpPassword,
			String tvodFtpTargetDir,
			String tvodFtpTempDir) throws Exception {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		boolean bFtp = false;
		if(loadMethod.equals(Constants.FILEUTILS.TVOD_FILE_LOAD_METHOD_FTP))
		{
			bFtp = true;
		}
		
		for(int i = 0 ; i < serviceInfoList.size() ; i++)
		{
			ServiceInfo serviceInfo = serviceInfoList.get(i);
			
			// root element - ServiceInfo
			Document doc = docBuilder.newDocument();
			Element serviceInfoElement = doc.createElement("ServiceInfo");
			doc.appendChild(serviceInfoElement);
			
			File destDirPath = new File(destDirRoot + "/" + serviceInfo.getAttibuteMap().get("ID"));
			if(!destDirPath.exists())
			{
				destDirPath.mkdir();
			}
			
			List<String> serviceInfoAttributesList = new ArrayList<String>(serviceInfo.getAttibuteMap().keySet());
			Map<String, String> serviceInfoAttributeMap = serviceInfo.getAttibuteMap();
			for(int j = 0 ; j < serviceInfoAttributesList.size() ; j++)
			{
				String attribute = serviceInfoAttributesList.get(j);
				Attr attr = doc.createAttribute(attribute);
				if(attribute.equals("TimeShift"))
				{
					attr.setValue("0");
				}
				else
				{
					attr.setValue(serviceInfoAttributeMap.get(attribute));
				}
				serviceInfoElement.setAttributeNode(attr);
			}
			
			// ScheduleInfo element
			Element scheduleInfoElement = doc.createElement("ScheduleInfo");
			serviceInfoElement.appendChild(scheduleInfoElement);
			List<String> scheduleInfoAttributesList = new ArrayList<String>(serviceInfo.getScheduleInfo().getAttibuteMap().keySet());
			Map<String, String> scheduleInfoAttributesMap = serviceInfo.getScheduleInfo().getAttibuteMap();
			for(int j = 0 ; j < scheduleInfoAttributesList.size() ; j++)
			{
				String attribute = scheduleInfoAttributesList.get(j);
				Attr attr = doc.createAttribute(attribute);
				attr.setValue(scheduleInfoAttributesMap.get(attribute));
				scheduleInfoElement.setAttributeNode(attr);
			}
			
			// ProgramInfo elements
			List<Map<String, String>> programInfoMapList = serviceInfo.getScheduleInfo().getProgramInfoList();
			for(int j = 0 ; j < programInfoMapList.size() ; j++)
			{
				Element programInfoElement = doc.createElement("ProgramInfo");
				scheduleInfoElement.appendChild(programInfoElement);
				List<String> programInfoNodeList = new ArrayList<String>(serviceInfo.getScheduleInfo().getProgramInfoList().get(j).keySet());
				Map<String, String> programInfoMap = serviceInfo.getScheduleInfo().getProgramInfoList().get(j);
				for(int k = 0 ; k < programInfoNodeList.size() ; k++)
				{
					String nodeName = programInfoNodeList.get(k);
					Element piSubElement = doc.createElement(nodeName);
					piSubElement.appendChild(doc.createTextNode(programInfoMap.get(nodeName)));
					programInfoElement.appendChild(piSubElement);
				}
			}
			
			// write the content into xml file
//			String destFileName = String.format("%s/%s_SI_%04d.xml", destDirPath, i+1, sourceFileName.substring(0, sourceFileName.length()-4));
			String destFileName = String.format("%s/%s_SI_%04d.xml", destDirPath, FilenameUtils.removeExtension(sourceFileName), i+1);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(destFileName));
			transformer.transform(source, result);
			
			if(bFtp)
			{
				FileUtilities.ftpPut(
						tvodFtpHost, 
						tvodFtpUser, 
						tvodFtpPassword, 
						destFileName, 
						tvodFtpTargetDir,
						tvodFtpTempDir,
						Constants.FILEUTILS.FTP_MODE_PASSIVE);
			}
		}
	}

	@Override
	public void createTstvServiceInfoXml(
			List<ServiceInfo> serviceInfoList,
			String destDirRoot,
			String sourceFileName,
			String loadMethod,
			String tvodFtpHost,
			String tvodFtpUser,
			String tvodFtpPassword,
			String tvodFtpTargetDir,
			String tvodFtpTempDir) throws Exception {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		
		boolean bFtp = false;
		if(loadMethod.equals(Constants.FILEUTILS.TVOD_FILE_LOAD_METHOD_FTP))
		{
			bFtp = true;
		}
		
		for(int i = 0 ; i < serviceInfoList.size() ; i++)
		{
			ServiceInfo serviceInfo = serviceInfoList.get(i);
			
			// root element - ServiceInfo
			Document doc = docBuilder.newDocument();
			Element serviceInfoElement = doc.createElement("ServiceInfo");
			doc.appendChild(serviceInfoElement);
			
			File destDirPath = new File(destDirRoot + "/" + serviceInfo.getAttibuteMap().get("ID"));
			if(!destDirPath.exists())
			{
				destDirPath.mkdir();
			}
			
			List<String> serviceInfoAttributesList = new ArrayList<String>(serviceInfo.getAttibuteMap().keySet());
			Map<String, String> serviceInfoAttributeMap = serviceInfo.getAttibuteMap();
			for(int j = 0 ; j < serviceInfoAttributesList.size() ; j++)
			{
				String attribute = serviceInfoAttributesList.get(j);
				Attr attr = doc.createAttribute(attribute);
				if(attribute.equals("IsTVOD"))
				{
					attr.setValue("0");
				}
				else
				{
					attr.setValue(serviceInfoAttributeMap.get(attribute));
				}
				serviceInfoElement.setAttributeNode(attr);
			}
			
			// ScheduleInfo element
			Element scheduleInfoElement = doc.createElement("ScheduleInfo");
			serviceInfoElement.appendChild(scheduleInfoElement);
			List<String> scheduleInfoAttributesList = new ArrayList<String>(serviceInfo.getScheduleInfo().getAttibuteMap().keySet());
			Map<String, String> scheduleInfoAttributesMap = serviceInfo.getScheduleInfo().getAttibuteMap();
			for(int j = 0 ; j < scheduleInfoAttributesList.size() ; j++)
			{
				String attribute = scheduleInfoAttributesList.get(j);
				Attr attr = doc.createAttribute(attribute);
				attr.setValue(scheduleInfoAttributesMap.get(attribute));
				scheduleInfoElement.setAttributeNode(attr);
			}
			
			// ProgramInfo elements
			List<Map<String, String>> programInfoMapList = serviceInfo.getScheduleInfo().getProgramInfoList();
			for(int j = 0 ; j < programInfoMapList.size() ; j++)
			{
				Element programInfoElement = doc.createElement("ProgramInfo");
				scheduleInfoElement.appendChild(programInfoElement);
				List<String> programInfoNodeList = new ArrayList<String>(serviceInfo.getScheduleInfo().getProgramInfoList().get(j).keySet());
				Map<String, String> programInfoMap = serviceInfo.getScheduleInfo().getProgramInfoList().get(j);
				for(int k = 0 ; k < programInfoNodeList.size() ; k++)
				{
					String nodeName = programInfoNodeList.get(k);
					Element piSubElement = doc.createElement(nodeName);
					piSubElement.appendChild(doc.createTextNode(programInfoMap.get(nodeName)));
					programInfoElement.appendChild(piSubElement);
				}
			}
			
			// write the content into xml file
//			String destFileName = String.format("%s/%s_SI_%04d.xml", destDirPath, i+1, sourceFileName.substring(0, sourceFileName.length()-4));
			String destFileName = String.format("%s/%s_SI_%04d.xml", destDirPath, FilenameUtils.removeExtension(sourceFileName), i+1);
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(destFileName));
			transformer.transform(source, result);
			
			if(bFtp)
			{
				FileUtilities.ftpPut(
						tvodFtpHost, 
						tvodFtpUser, 
						tvodFtpPassword, 
						destFileName, 
						tvodFtpTargetDir,
						tvodFtpTempDir,
						Constants.FILEUTILS.FTP_MODE_PASSIVE);
			}
		}
	}
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addTvodEpgLoadHistory(TvodEpgLoadHistory tvodEpgLoadHistory) throws Exception {
		tvodEpgLoadHistoryDao.addTvodEpgLoadHistory(tvodEpgLoadHistory);
	}
	
	private ServiceInfo setServiceInfoAttribute(ServiceInfo tvodServiceInfo, Element programGuideElement) throws Exception
	{

		if(programGuideElement.getElementsByTagName("Service").item(0) == null)
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service");
		}
		Element serviceElement = (Element)programGuideElement.getElementsByTagName("Service").item(0);

		if(serviceElement.getElementsByTagName("IsTimeshift").item(0) == null)
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > IsTimeshift");
		}
		String timeShift = serviceElement.getElementsByTagName("IsTimeshift").item(0).getTextContent();
		
		if((timeShift == null) || (timeShift.length() == 0))
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > IsTimeshift");
		}

		if(serviceElement.getElementsByTagName("IsTVOD").item(0) == null)
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > IsTVOD");
		}
		String isTvod = serviceElement.getElementsByTagName("IsTVOD").item(0).getTextContent();
		
		if((isTvod == null) || (isTvod.length() == 0))
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > IsTVOD");
		}

		if(serviceElement.getElementsByTagName("TimeShiftSaveTimeInMin").item(0) == null)
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > TimeShiftSaveTimeInMin");
		}
		String timeShiftSaveTimeInMin = serviceElement.getElementsByTagName("TimeShiftSaveTimeInMin").item(0).getTextContent();
		
		if((timeShiftSaveTimeInMin == null) || (timeShiftSaveTimeInMin.length() == 0))
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > TimeShiftSaveTimeInMin");
		}
		
		if(serviceElement.getElementsByTagName("ServiceName").item(0) == null)
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > ServiceName");
		}
		Element serviceNameElement = (Element)serviceElement.getElementsByTagName("ServiceName").item(0);
		
		if(serviceNameElement.getElementsByTagName("Name").item(0) == null)
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > ServiceName > Name");
		}
		
		String name = serviceNameElement.getElementsByTagName("Name").item(0).getTextContent();
		
		if((name == null) || (name.length() == 0))
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > ServiceName > Name");
		}
		
		tvodServiceInfo.getAttibuteMap().put("IsTVOD", isTvod);
		tvodServiceInfo.getAttibuteMap().put("TimeShift", timeShift);
		tvodServiceInfo.getAttibuteMap().put("TimeShiftSaveTimeInMin", timeShiftSaveTimeInMin);
		tvodServiceInfo.getAttibuteMap().put("Name", name);
		tvodServiceInfo.getAttibuteMap().put("ShortName", name);
		
		String id = serviceElement.getAttribute("id");
		if((id == null) || (id.length() == 0))
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > id(attribute)");
		}
		
		tvodServiceInfo.getAttibuteMap().put("ID", id);
		
		return tvodServiceInfo;
	}

	private ServiceInfo setScheduleInfoAttribute(ServiceInfo tvodServiceInfo, Element programGuideElement) throws Exception 
	{
		Element serviceElement = (Element)programGuideElement.getElementsByTagName("Service").item(0);
		
		String start = serviceElement.getAttribute("start");
		if((start == null) || (start.length() == 0))
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > start(attribute)");
		}
		tvodServiceInfo.getScheduleInfo().getAttibuteMap().put("BeginTime", start);
		
		Date startDateTime = DateUtil.convertStrToDate(start, "yyyy-MM-dd HH:mm:ss");
		tvodServiceInfo.getScheduleInfo().getAttibuteMap().put("Date", DateUtil.getDateStr(startDateTime, "yyyy-MM-dd"));
		
		String end = serviceElement.getAttribute("end");
		if((end == null) || (end.length() == 0))
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Service > end(attribute)");
		}
		tvodServiceInfo.getScheduleInfo().getAttibuteMap().put("EndTime", end);
		
		return tvodServiceInfo;
	}

	private ServiceInfo setProgramInfoAttribute(ServiceInfo tvodServiceInfo, Element programGuideElement) throws Exception
	{
		NodeList programNodeList = programGuideElement.getElementsByTagName("Program");
		
		if(programNodeList.getLength() == 0)
		{
			throw new Exception("Data Missing : RootGuide > ProgramGuide > Program");
		}
		
		String id = tvodServiceInfo.getAttibuteMap().get("ID");
		
		for(int i = 0 ; i < programNodeList.getLength() ; i++)
		{
			// Get source data.
			Element programElement = (Element) programNodeList.item(i);
			
			String eventId = programElement.getAttribute("eventid");
			if((eventId == null) || (eventId.length() == 0))
			{
				throw new Exception("Data Missing : RootGuide > ProgramGuide > Program > eventid(attribute)");
			}
			
			Element periodElement = (Element)programElement.getElementsByTagName("Period").item(0);
			String start = periodElement.getAttribute("start");
			if((start == null) || (start.length() == 0))
			{
				throw new Exception("Data Missing : RootGuide > ProgramGuide > Program > Period > start(attribute)");
			}
			
			String end = periodElement.getAttribute("end");
			if((end == null) || (end.length() == 0))
			{
				throw new Exception("Data Missing : RootGuide > ProgramGuide > Program > Period > end(attribute)");
			}
			
			// Set target data.
			Map<String, String> programInfoMap = new HashMap<String, String>();
		
			Date startDateTime = DateUtil.convertStrToDate(start, "yyyy-MM-dd HH:mm:ss");
			Date endDateTime = DateUtil.convertStrToDate(end, "yyyy-MM-dd HH:mm:ss");

			String stringDate = DateUtil.getDateStr(startDateTime, "yyyyMMddHHmmss");
			String startDate = DateUtil.getDateStr(startDateTime, "yyyy-MM-dd");
			
			long duration = endDateTime.getTime() - startDateTime.getTime();
			long durationDays = duration / (24 * 60 * 60 * 1000);
			long durationHours = duration / (60 * 60 * 1000) % 24;
			long durationMinutes = duration / (60 * 1000) % 60;
			long durationSeconds = duration / 1000 % 60;
			String durationString = String.format("%02d:%02d:%02d:%02d", durationDays, durationHours, durationMinutes, durationSeconds);
			
			programInfoMap.put("EventID", eventId);
			programInfoMap.put("AssetID", stringDate + id + eventId);
			programInfoMap.put("FileName", stringDate + id + eventId + ".mpg");
			programInfoMap.put("OnAirDate", startDate);
			programInfoMap.put("BeginTime", start + ":00");
			programInfoMap.put("Duration", durationString);
			programInfoMap.put("EndTime", end + ":00");
			
			tvodServiceInfo.getScheduleInfo().getProgramInfoList().add(programInfoMap);
		}
		
		return tvodServiceInfo;
	}

	@Override
	public long getServiceDeployHistoryListCount(Map<String, Object> paramMap) throws Exception {
		return tvodEpgLoadHistoryDao.getServiceDeployHistoryListCount(paramMap);
	}

	@Override
	public List<TvodEpgLoadHistory> getServiceDeployHistoryList(Map<String, Object> paramMap) throws Exception {
		return tvodEpgLoadHistoryDao.getServiceDeployHistoryList(paramMap);
	}


}
