package com.fast2.zimin.tvod.job;

import com.fast2.zimin.tvod.service.TvodProgramScheduleService;
import com.fast2.zimin.tvod.thread.TvodProgramScheduleProcessThread;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import java.util.ArrayList;
import java.util.List;

@Component("tvodEpgFileCheckJob")
@DisallowConcurrentExecution
//public class TvodEpgFileCheckJob implements Job {
public class TvodEpgFileCheckJob extends QuartzJobBean {

	@Autowired
	private TvodProgramScheduleService tvodProgramScheduleService;
	
//	@Value("${system.epgToLoadDirName}")
	private String dirToLoad;
	
//	@Value("${system.epgInUseDirName}")
	private String dirInUse;

//	@Value("${system.epgFailedDirName}")
	private String dirFailed;

//	@Value("${system.tvodStbLoadedDirName}")
	private String dirTvodStbLoaded;

//	@Value("${system.tvodOttLoadedDirName}")
	private String dirTvodOttLoaded;

//	@Value("${system.tstvStbLoadedDirName}")
	private String dirTstvStbLoaded;

//	@Value("${system.tstvOttLoadedDirName}")
	private String dirTstvOttLoaded;

//	@Value("${system.serviceInfoLoadedMethod:NAS}")
	private String loadMethod;
	
//	@Value("${system.tvodFtpHost:}")
	private String tvodFtpHost;
	
//	@Value("${system.tvodFtpUser:}")
	private String tvodFtpUser;

//	@Value("${system.tvodFtpPassword:}")
	private String tvodFtpPassword;
	
//	@Value("${system.tvodFtpTargetDir:}")
	private String tvodFtpTargetDir;

//	@Value("${system.tvodFtpTempDir:}")
	private String tvodFtpTempDir;
/*	
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet("http://localhost:8080/zimin/tvod/requestProgramScheduleProcess.do");

        try
        {
            HttpResponse response = httpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();

            System.out.println(EntityUtils.toString(entity));
        } catch(Exception e)
        {
        	e.printStackTrace();
        }
        finally
        {
        	httpGet.releaseConnection();
        }		
	}
*/
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		
		SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
		
		String threadName = "TvodProgramScheduleProcessThread";

//		TvodProgramScheduleService tvodProgramScheduleService = new TvodProgramScheduleServiceImpl();

		List<Thread> threadList = new ArrayList<Thread>(Thread.getAllStackTraces().keySet());
		for(int i = 0 ; i < threadList.size() ; i++)
		{
			String currentThreadName = threadList.get(i).getName();
			if(currentThreadName.equals(threadName))
			{
				return;
			}
		}
		
		TvodProgramScheduleProcessThread tvodProgramScheduleProcessThread 
			= new TvodProgramScheduleProcessThread
					(
						dirToLoad,
						dirInUse,
						dirTvodStbLoaded, dirTvodOttLoaded, dirTstvStbLoaded, dirTstvOttLoaded,
						dirFailed,
						tvodProgramScheduleService,
						loadMethod,
						tvodFtpHost, tvodFtpUser, tvodFtpPassword, tvodFtpTargetDir, tvodFtpTempDir
					);
		
		tvodProgramScheduleProcessThread.setName(threadName);
 
		tvodProgramScheduleProcessThread.start();
		
	}

}
