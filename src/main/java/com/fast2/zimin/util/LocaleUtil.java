package com.fast2.zimin.util;

import com.fast2.zimin.system.entity.Locale;
import com.fast2.zimin.system.service.LocaleService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//@Component
public class LocaleUtil {
	private static HashMap<String, Locale> localeMap = null;
	private static HashMap<String, String> languageMap = null;
	private static List<Locale> localeList = null;
	private static String[] localeCodes = null;
	private static List<String> requiredLocaleCodeList = null;
	private static String defaultLocaleCode = "";

//	@Autowired
	private LocaleService localeService;

//	@PostConstruct
	private void initialize() {
		TheLogger.info("■■■■■ Supported Languages initializing..■■■■■");
		System.out.println("■■■■■ Supported Languages initializing..■■■■■");

		try {
			localeList = localeService.getLocaleList();
			if (localeList == null || localeList.size() == 0)
				throw new Exception("Locale table set first!!");

			localeMap = new HashMap<String, Locale>();
			languageMap = new HashMap<String, String>();
			localeCodes = new String[localeList.size()];
			requiredLocaleCodeList = new ArrayList<>();
			int i = 0;
			for (Locale locale : localeList) {
				locale.setLanguageForInput(locale.getLanguage());
				localeMap.put(locale.getLocaleCode(), locale);
				languageMap.put(locale.getLocaleCode(), locale.getLanguage());
				localeCodes[i++] = locale.getLocaleCode();
				if (locale.isRequired()) {
					locale.setLanguageForInput(locale.getLanguageForInput()
							+ "(*)");
					requiredLocaleCodeList.add(locale.getLocaleCode());
				}

				if (locale.isDefaultLocale())
					defaultLocaleCode = locale.getLocaleCode();
			}

			TheLogger.info("■■■■■ Supported Languages initialized..■■■■■");
			System.out.println("■■■■■ Supported Languages initialized..■■■■■");
		} catch (Exception e) {
			if (localeList == null) {
				localeList = new ArrayList<>();
				localeMap = new HashMap<String, Locale>();
				languageMap = new HashMap<String, String>();
				localeCodes = new String[0];
				requiredLocaleCodeList = new ArrayList<>();
			}

			TheLogger.error(e, "Supported Languages Fatal Error(%s)",
					e.getMessage());
		}
	}

	public static String getDefaultLocaleCode() {
		return defaultLocaleCode;
	}
/*
	public static String getDefaultLocale(Collection<MenuLocale> list,
			String propertyName) {
		String tempLocale = Constants.ResultMessageType.NOT_FOUND.getName();
		if (null != list) {
			for (Localeable data : list) {
				if (defaultLocaleCode.equals(data.getLocaleCode())) {
					try {
						tempLocale = PropertyUtils.getProperty(data,
								propertyName).toString();
						if (!"".equals(tempLocale.trim())) {
							return tempLocale;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				try {
					tempLocale = PropertyUtils.getProperty(data, propertyName)
							.toString();
					if (!(Constants.ResultMessageType.NOT_FOUND.getName()
							.equals(tempLocale) || "".equals(tempLocale.trim()))) {
						return tempLocale;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "".equals(tempLocale.trim()) ? Constants.ResultMessageType.EMPTY
				.getName() : tempLocale;
	}*/

	public static List<Locale> getLocaleList() {
		return localeList;
	}

	public static HashMap<String, Locale> getLocaleMap() {
		return localeMap;
	}

	public static String[] getLocaleCodes() {
		return localeCodes;
	}

	public static String getLanguage(String localeCode) {
		return languageMap.get(localeCode);
	}

	public static List<String> getRequiredLocaleCodeList() {
		return requiredLocaleCodeList;
	}
}