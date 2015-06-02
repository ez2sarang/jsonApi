package com.fast2.zimin.tvod.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleInfo {
	
	private Map<String, String> attibuteMap;
	
	private List<Map<String, String>> programInfoList;
	
	public ScheduleInfo() {
		this.attibuteMap = new HashMap<String, String>();
		this.programInfoList = new ArrayList<Map<String, String>>();
	}

	public Map<String, String> getAttibuteMap() {
		return attibuteMap;
	}

	public void setAttibuteMap(Map<String, String> attibuteMap) {
		this.attibuteMap = attibuteMap;
	}

	public List<Map<String, String>> getProgramInfoList() {
		return programInfoList;
	}

	public void setProgramInfoList(List<Map<String, String>> programInfoList) {
		this.programInfoList = programInfoList;
	}
}
