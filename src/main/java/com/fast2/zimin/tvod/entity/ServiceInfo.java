package com.fast2.zimin.tvod.entity;

import java.util.HashMap;
import java.util.Map;

public class ServiceInfo {

	private Map<String, String> attibuteMap;
	
	private ScheduleInfo scheduleInfo;
	
	public ServiceInfo() {
		this.attibuteMap = new HashMap<String, String>();
		this.scheduleInfo = new ScheduleInfo();
	}

	public Map<String, String> getAttibuteMap() {
		return attibuteMap;
	}

	public void setAttibuteMap(Map<String, String> attibuteMap) {
		this.attibuteMap = attibuteMap;
	}

	public ScheduleInfo getScheduleInfo() {
		return scheduleInfo;
	}

	public void setScheduleInfo(ScheduleInfo scheduleInfo) {
		this.scheduleInfo = scheduleInfo;
	}
}
