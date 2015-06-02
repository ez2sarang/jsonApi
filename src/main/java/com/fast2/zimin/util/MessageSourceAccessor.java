package com.fast2.zimin.util;

import java.util.Locale;

import org.springframework.context.MessageSource;

public class MessageSourceAccessor extends
		org.springframework.context.support.MessageSourceAccessor {
	public MessageSourceAccessor(MessageSource messageSource) {
		super(messageSource);
	}

	public MessageSourceAccessor(MessageSource messageSource,
			Locale defaultLocale) {
		super(messageSource, defaultLocale);
	}

	public String getMsg(String code) {
		return super.getMessage(code, "No Element Found");
	}

	public String getMsg(String code, String arg) {
		return super.getMessage(code, new Object[] { arg }, "No Element Found");
	}

	public String getMsg(String code, Object[] args) {
		return super.getMessage(code, args, "No Element Found");
	}
	
	public Integer getInteger(String code) {
		return Integer.valueOf(super.getMessage(code, "0"));
	}
}
