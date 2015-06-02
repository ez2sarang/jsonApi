package com.fast2.zimin.tvod.thread;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import com.fast2.zimin.tvod.entity.ServiceInfo;
import com.fast2.zimin.tvod.entity.TvodEpgLoadHistory;
import com.fast2.zimin.tvod.service.TvodProgramScheduleService;
import com.fast2.zimin.tvod.service.impl.TvodProgramScheduleServiceImpl;
import com.fast2.zimin.util.Constants;
import com.fast2.zimin.util.DateUtil;
import com.fast2.zimin.util.FileUtilities;
import com.fast2.zimin.util.TheLogger;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;

@Component
@Scope("prototype")
public class TvodProgramScheduleProcessThread extends Thread
{
	private String pathToLoad;
	private String pathInUse;
	private String pathTvodStbLoaded;
	private String pathTvodOttLoaded;
	private String pathTstvStbLoaded;
	private String pathTstvOttLoaded;
	private String pathFailed;
	private TvodProgramScheduleService tvodProgramScheduleService;
	
	private String loadMethod;
	private String tvodFtpHost;
	private String tvodFtpUser;
	private String tvodFtpPassword;
	private String tvodFtpTargetDir;
	private String tvodFtpTempDir;
	
	public TvodProgramScheduleProcessThread(
			String pathToLoad,
			String pathInUse,
			String pathTvodStbLoaded,
			String pathTvodOttLoaded,
			String pathTstvStbLoaded,
			String pathTstvOttLoaded,
			String pathFailed,
			TvodProgramScheduleService tvodProgramScheduleService,
			String loadMethod,
			String tvodFtpHost,
			String tvodFtpUser,
			String tvodFtpPassword,
			String tvodFtpTargetDir,
			String tvodFtpTempDir) {
		super();
		this.pathToLoad = pathToLoad;
		this.pathInUse = pathInUse;
		this.pathTvodStbLoaded = pathTvodStbLoaded;
		this.pathTvodOttLoaded = pathTvodOttLoaded;
		this.pathTstvStbLoaded = pathTstvStbLoaded;
		this.pathTstvOttLoaded = pathTstvOttLoaded;
		this.pathFailed = pathFailed;
		this.tvodProgramScheduleService = tvodProgramScheduleService;
		this.loadMethod = loadMethod;
		this.tvodFtpHost = tvodFtpHost;
		this.tvodFtpUser = tvodFtpUser;
		this.tvodFtpPassword = tvodFtpPassword;
		this.tvodFtpTargetDir = tvodFtpTargetDir;
		this.tvodFtpTempDir = tvodFtpTempDir;
	}


	@Override
	public void run()
	{
		TheLogger.info("■■■■■■■■■■■■■■■■■■■■");
		TheLogger.info("■ TvodProgramScheduleProcessThread start..");
		
		File[] fileList;

		while(true)
		{
			// 0. Get file list of "ToLoad" directory.
			fileList = FileUtilities.getFileList(pathToLoad);
			
			if((fileList == null) || (fileList.length == 0))
			{
				break;
			}
				
			if((fileList != null) && (fileList.length > 0))
			{
				for(int i = 0 ; i < fileList.length ; i++)
				{
					File file = fileList[i];

					TvodEpgLoadHistory loadHistory = new TvodEpgLoadHistory();
					loadHistory.setFileName(file.getName());
					loadHistory.setStartTime(new Date());

					// 1. Copy file(s) into "InUse" directory.
					File newFile = FileUtilities.moveFile(file, pathInUse, null);
					
					try
					{
						// 2. Parse XML file(s).
						Document programScheduleDoc = FileUtilities.getXmlDocument(newFile);
						
						List<ServiceInfo> serviceInfoList = tvodProgramScheduleService.getServiceInfo(programScheduleDoc);
						
						Map<String, List<ServiceInfo>> serviceInfoListMap = tvodProgramScheduleService.separateServiceInfo(serviceInfoList);
						
						// 3.1 Create new XML file(s) to export in TVOD target directory.
						tvodProgramScheduleService.createTvodServiceInfoXml(
								serviceInfoListMap.get(Constants.TVODANDTSTV.TYPE_NAME_TVOD),
								this.pathTvodStbLoaded,
								file.getName(),
								loadMethod,
								tvodFtpHost, tvodFtpUser, tvodFtpPassword, tvodFtpTargetDir, tvodFtpTempDir);

						tvodProgramScheduleService.createTvodServiceInfoXml(
								serviceInfoListMap.get(Constants.TVODANDTSTV.TYPE_NAME_TVOD),
								this.pathTvodOttLoaded,
								file.getName(),
								loadMethod,
								tvodFtpHost, tvodFtpUser, tvodFtpPassword, tvodFtpTargetDir, tvodFtpTempDir);
						
						// 3. Create new XML file(s) to export in TSTV target directory.
						tvodProgramScheduleService.createTstvServiceInfoXml(
								serviceInfoListMap.get(Constants.TVODANDTSTV.TYPE_NAME_TSTV),
								this.pathTstvStbLoaded,
								file.getName(),
								loadMethod,
								tvodFtpHost, tvodFtpUser, tvodFtpPassword, tvodFtpTargetDir, tvodFtpTempDir);
						
						tvodProgramScheduleService.createTstvServiceInfoXml(
								serviceInfoListMap.get(Constants.TVODANDTSTV.TYPE_NAME_TSTV),
								this.pathTstvOttLoaded,
								file.getName(),
								loadMethod,
								tvodFtpHost, tvodFtpUser, tvodFtpPassword, tvodFtpTargetDir, tvodFtpTempDir);

						// 4. Delete loaded file(s).
						// 20150105. Commented by LEE Sang-Youb.
//						newFile.delete();

						loadHistory.setEndTime(new Date());
						loadHistory.setResult("O");
						
					} catch(Exception e)
					{
						// If the process was failed then move source file into "Failed" directory
						e.printStackTrace();
						
						FileUtilities.moveFile(newFile, pathFailed, null);

						loadHistory.setEndTime(new Date());
						loadHistory.setResult("X");
						loadHistory.setResultMessage(e.getMessage());
					}
					
					// 5. Record this process result into database.
					try
					{
						tvodProgramScheduleService.addTvodEpgLoadHistory(loadHistory);
					} catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		TheLogger.info("■■■■■■■■■■■■■■■■■■■■");
		TheLogger.info("■ TvodProgramScheduleProcessThread end..");

	}
}
