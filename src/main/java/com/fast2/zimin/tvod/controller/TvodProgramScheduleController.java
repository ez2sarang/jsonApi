package com.fast2.zimin.tvod.controller;

import com.fast2.zimin.tvod.entity.TvodEpgLoadHistory;
import com.fast2.zimin.tvod.service.TvodProgramScheduleService;
import com.fast2.zimin.tvod.thread.TvodProgramScheduleProcessThread;
import com.fast2.zimin.util.DataTableObject;
import com.fast2.zimin.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TvodProgramScheduleController {
	
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
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat(
				DateUtil.defaultDateTimeFormat), true);
		binder.registerCustomEditor(Date.class, editor);
	}
	
	@RequestMapping(value = "/tvod/requestProgramScheduleProcess")
	public @ResponseBody String requestProgramScheduleProcess(HttpServletRequest request)
	{
		String threadName = "TvodProgramScheduleProcessThread";
		Date now = new Date();

		List<Thread> threadList = new ArrayList<Thread>(Thread.getAllStackTraces().keySet());
		for(int i = 0 ; i < threadList.size() ; i++)
		{
			String currentThreadName = threadList.get(i).getName();
			if(currentThreadName.equals(threadName))
			{
				return String.format("%s : %s has been started before.", now.toString(), threadName);
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
		
		return String.format("%s : %s has been started.", now.toString(), threadName);
	}
	
	@RequestMapping(value = "/tvod/getTvodEpgLoadHistoryListForm")
	public String getTvodEpgLoadHistoryList(Model model)
	{
		return "tvod/tvod_epg_load_history_list_main";
	}
	
	@RequestMapping(value = "/tvod/getTvodEpgLoadHistoryList")
	public @ResponseBody DataTableObject getServiceDeployHistoryList(
			Model model,
			HttpServletRequest request)
	{
		DataTableObject dataTableObject = new DataTableObject();
		
		Map<String, String> columnMap = new HashMap<String, String>();
		for(int i = 0 ; i < 9 ; i++)
		{
			String paramName = "columns[" + i + "][name]";
			columnMap.put(String.valueOf(i), request.getParameter(paramName));
		}

		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		int draw = Integer.valueOf(request.getParameter("draw"));
		
		long listCount = 0;
		List<TvodEpgLoadHistory> tvodEpgLoadHistoryList;
		
		try
		{
			// parameter setting
			paramMap.put("orderColumn", columnMap.get(request.getParameter("order[0][column]")));
			paramMap.put("orderDir", request.getParameter("order[0][dir]"));
			
			// user defined search condition
			paramMap.put("scResult", request.getParameter("scResult"));
			paramMap.put("scFileName", request.getParameter("scFileName"));
			
			String scFromDate = request.getParameter("scFromDate");
			if((scFromDate != null) && (scFromDate.length() >= 10))
			{
				scFromDate = scFromDate.substring(0, 4) + scFromDate.substring(5, 7) + scFromDate.substring(8, 10); 
			}
			else
			{
				scFromDate = null;
			}
			paramMap.put("scFromDate", scFromDate);
			
			String paramToDate = request.getParameter("scToDate");
			String scToDate;
			
			if((paramToDate != null) && (paramToDate.length() >= 10))
			{
				paramToDate = paramToDate.substring(0, 4) + paramToDate.substring(5, 7) + paramToDate.substring(8, 10);
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyyMMdd");
				Calendar cal = Calendar.getInstance();
		        cal.setTime(transFormat.parse(paramToDate));
		        cal.add(Calendar.DATE, 1);
		        Date aDayAfter = cal.getTime();
		        scToDate = transFormat.format(aDayAfter);
			}
			else
			{
				scToDate = null;
			}
			paramMap.put("scToDate", scToDate);
			
			// pagination
			paramMap.put("start", Integer.valueOf(request.getParameter("start")));
			paramMap.put("size", Integer.valueOf(request.getParameter("length")));
			
			listCount = tvodProgramScheduleService.getServiceDeployHistoryListCount(paramMap);
			tvodEpgLoadHistoryList = tvodProgramScheduleService.getServiceDeployHistoryList(paramMap);
			
			for(int i = 0 ; i < tvodEpgLoadHistoryList.size() ; i++)
			{
				if(tvodEpgLoadHistoryList.get(i).getResult().equals("O"))
				{
					tvodEpgLoadHistoryList.get(i).setResult("succeeded");
				}
				else
				{
					tvodEpgLoadHistoryList.get(i).setResult("failed");
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			listCount = 0;
			tvodEpgLoadHistoryList = new ArrayList<TvodEpgLoadHistory>();
		}
		
		dataTableObject.setRecordsTotal(listCount);
		dataTableObject.setRecordsFiltered(listCount);
		dataTableObject.setDraw(draw);
		dataTableObject.setData(tvodEpgLoadHistoryList);

		return dataTableObject;
	}
}
