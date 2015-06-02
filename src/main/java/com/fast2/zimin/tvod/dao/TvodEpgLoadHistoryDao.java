package com.fast2.zimin.tvod.dao;

import java.util.List;
import java.util.Map;

import com.fast2.zimin.tvod.entity.TvodEpgLoadHistory;

public interface TvodEpgLoadHistoryDao {

	void addTvodEpgLoadHistory(TvodEpgLoadHistory tvodEpgLoadHistory) throws Exception;

	long getServiceDeployHistoryListCount(Map<String, Object> paramMap) throws Exception;

	List<TvodEpgLoadHistory> getServiceDeployHistoryList(Map<String, Object> paramMap) throws Exception;

}
