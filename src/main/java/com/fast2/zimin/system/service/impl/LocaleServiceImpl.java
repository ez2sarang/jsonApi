package com.fast2.zimin.system.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fast2.zimin.system.dao.LocaleDao;
import com.fast2.zimin.system.entity.Locale;
import com.fast2.zimin.system.service.LocaleService;

@Service("localeService")
@Transactional(propagation = Propagation.SUPPORTS)
public class LocaleServiceImpl implements LocaleService {
	@Autowired
	LocaleDao localeDao;

	@Override
	public Locale getLocale(String localeCode) throws Exception {
		return localeDao.getLocale(localeCode);
	}

	@Override
	public List<Locale> getLocaleList() throws Exception {
		return localeDao.getLocaleList();
	}
}
