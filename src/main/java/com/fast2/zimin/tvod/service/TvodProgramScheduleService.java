package com.fast2.zimin.tvod.service;

import java.util.List;
import java.util.Map;

import org.w3c.dom.Document;

import com.fast2.zimin.tvod.entity.ServiceInfo;
import com.fast2.zimin.tvod.entity.TvodEpgLoadHistory;

public interface TvodProgramScheduleService {

	public List<ServiceInfo> getServiceInfo(Document programScheduleDoc)  throws Exception;

	public Map<String, List<ServiceInfo>> separateServiceInfo(List<ServiceInfo> serviceInfoList) throws Exception;

	public void createServiceInfoXml(
			List<ServiceInfo> tvodServiceInfoList,
			String destDirRoot,
			String sourceFileName,
			String loadMethod,
			String tvodFtpHost,
			String tvodFtpUser,
			String tvodFtpPassword,
			String tvodFtpTargetDir,
			String tvodFtpTempDir) throws Exception;

	public void createTvodServiceInfoXml(
			List<ServiceInfo> tvodServiceInfoList,
			String destDirRoot,
			String sourceFileName,
			String loadMethod,
			String tvodFtpHost,
			String tvodFtpUser,
			String tvodFtpPassword,
			String tvodFtpTargetDir,
			String tvodFtpTempDir) throws Exception;

	public void createTstvServiceInfoXml(
			List<ServiceInfo> tvodServiceInfoList,
			String destDirRoot,
			String sourceFileName,
			String loadMethod,
			String tvodFtpHost,
			String tvodFtpUser,
			String tvodFtpPassword,
			String tvodFtpTargetDir,
			String tvodFtpTempDir) throws Exception;
	
	public void addTvodEpgLoadHistory(TvodEpgLoadHistory loadHistory) throws Exception;

	public long getServiceDeployHistoryListCount(Map<String, Object> paramMap) throws Exception;

	public List<TvodEpgLoadHistory> getServiceDeployHistoryList(Map<String, Object> paramMap) throws Exception;

}
