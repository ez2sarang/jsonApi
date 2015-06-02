package com.fast2.zimin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String defaultDateTimeFormat;
	public static String defaultDateFormat;
	public static String defaultTimeFormat;

	/**
	 * 현재 Date를 리턴함
	 * 
	 * @return
	 */
	public static Date getCurrentDate() {
		return new Date();
	}

	/**
	 * 오늘 0시를 리턴함
	 * 
	 * @return
	 */
	public static Date getCurrentDateMidnight() {
		String today = getDateStr(new Date(), "yyyyMMdd");
		return convertStrToDate(today + "000000000", "yyyyMMddHHmmssSSS");
	}

	/**
	 * config.properties에 지정된 형식으로 날짜/시간을 리턴한다.
	 * 
	 * @return
	 */
	public static String getCurrentDateTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateTimeFormat);
		return sdf.format(new Date());
	}

	/**
	 * config.properties에 지정된 형식으로 날짜를 리턴한다.
	 * 
	 * @return
	 */
	public static String getCurrentDateStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateTimeFormat);
		return sdf.format(new Date());
	}

	/**
	 * config.properties에 지정된 형식으로 시간을 리턴한다.
	 * 
	 * @return
	 */
	public static String getCurrentTimeStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(defaultDateTimeFormat);
		return sdf.format(new Date());
	}

	/**
	 * 주어진 패턴 날짜형 시스템일자를 구함
	 * 
	 * @param pattern
	 *            원하는 일자 패턴(예:yyyy/MM/dd HH:mm:ss)
	 * @return 시스템 일자
	 */
	public static String getCurrentStr(String pattern) {
		return getDateStr(new Date(), pattern);
	}

	/**
	 * 주어진 Date 객체를 이용하여 주어진 패턴 날짜형의 문자열을 구함.
	 * 
	 * @param date
	 *            원하는 일자의 Date 객체
	 * @param pattern
	 *            원하는 일자 패턴
	 * @return 주어진 패턴의 일자
	 */
	public static String getDateStr(Date date, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	/**
	 * 날짜/시각 스트링을 Date 형으로 변환한다.
	 * 
	 * @param dateTime
	 * @param dateFormat
	 * @return
	 */
	public static Date convertStrToDate(String dateTime, String dateFormat) {
		String simpleFormat = (dateFormat == null) ? defaultDateTimeFormat
				: dateFormat;

		SimpleDateFormat sdf = new SimpleDateFormat(simpleFormat);

		try {
			return sdf.parse(dateTime);
		} catch (ParseException e) {
			return null;
		}
	}

	/**
	 * config.properties 의 설정 값을 가져와 셋팅한다.
	 * 
	 * @param defaultDateTimeFormat
	 * @param defaultDateFormat
	 * @param defaultTimeFormat
	 */
	public static void initProperties(String defaultDateTimeFormat,
			String defaultDateFormat, String defaultTimeFormat) {
		DateUtil.defaultDateTimeFormat = defaultDateTimeFormat;
		DateUtil.defaultDateFormat = defaultDateFormat;
		DateUtil.defaultTimeFormat = defaultTimeFormat;
	}

	public static Date getUnlimitedDate() {
		return convertStrToDate("99991231000000000", "yyyyMMddHHmmssSSS");
	}

	/**
	 * dateStr이 해당 dateFormat에 맞는지 체크한다.
	 * 
	 * @param dateStr
	 * @param dateFromat
	 * @return
	 */
	public static boolean validDateFormat(String dateStr, String dateFormat) {

		if (dateStr == null) {
			return false;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		sdf.setLenient(false);

		try {
			// if not valid, it will throw ParseException
			@SuppressWarnings("unused")
			Date date = sdf.parse(dateStr);
		} catch (ParseException e) {
			return false;
		}

		return true;
	}
}
