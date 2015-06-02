package com.fast2.zimin.util;

import java.math.BigInteger;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

public class NumberUtil extends NumberUtils {
	/**
	 * null safe (default = 0L)
	 * 
	 * @param num
	 * @return
	 */
	public static long defaultLong(Long num) {
		return defaultLong(num, 0L);
	}

	/**
	 * null safe
	 * 
	 * @param num
	 * @param defaultNum
	 * @return
	 */
	public static long defaultLong(Long num, long defaultNum) {
		return num == null ? defaultNum : num;
	}

	public static BigInteger toBigInteger(String numStr, String defaultNumStr) {
		return new BigInteger(StringUtils.defaultString(numStr, defaultNumStr));
	}
}
