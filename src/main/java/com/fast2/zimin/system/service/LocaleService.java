package com.fast2.zimin.system.service;

import java.util.List;

import com.fast2.zimin.system.entity.Locale;

public interface LocaleService {
	public Locale getLocale(String localeCode) throws Exception;

	public List<Locale> getLocaleList() throws Exception;
}
