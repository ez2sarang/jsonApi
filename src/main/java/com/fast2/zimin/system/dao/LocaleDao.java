package com.fast2.zimin.system.dao;

import java.util.List;

import com.fast2.zimin.system.entity.Locale;

public interface LocaleDao {
	public Locale getLocale(String localeCode) throws Exception;

	public List<Locale> getLocaleList() throws Exception;
}
