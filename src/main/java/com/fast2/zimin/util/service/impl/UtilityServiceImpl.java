package com.fast2.zimin.util.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fast2.zimin.util.dao.UtilityDao;
import com.fast2.zimin.util.service.UtilityService;

@Service
@Transactional
public class UtilityServiceImpl implements UtilityService {

	@Autowired
	private UtilityDao utilityDao;
	
	@Override
	public String getNextCustomTableIdx(String tableName) throws Exception {
		return utilityDao.getNextCustomTableIdx(tableName);
	}

}
