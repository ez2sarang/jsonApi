package com.fast2.zimin.util;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

public class FullTextSearchKeywordUtil {
	public static String parseKeyword(String keyword) {
		if (StringUtils.isBlank(keyword))
			return keyword;

		if (StringUtils.endsWith(keyword, "\""))
			return keyword;

		if (!StringUtils.endsWith(keyword, "\""))
			return "\"" + keyword + "\"";
	
		if (StringUtils.indexOf(keyword, ' ') < 0) {
			if (StringUtils.endsWith(keyword, "\""))
				return keyword;
			else
				return keyword + "*";
		}

		String result = "";
		Set<String> quotesSet = new HashSet<>();
		try {
			// 검색 "귀여운 로고" -영상 ==> 검색* "귀여운 로고" -영상*
			String noQuetesStr = parseDoubleQuotes(keyword, quotesSet);
			int i = 0;
			for (String str : quotesSet) {
				if (i > 0)
					result += " ";

				result += str;
				i++;
			}

			if (StringUtils.isNotBlank(noQuetesStr)) {
				String parts[] = StringUtils.split(noQuetesStr, ' ');
				for (int j = 0; j < parts.length; j++) {
					if (i > 0)
						result += " ";

					result += parts[j] + "*";
					i++;
				}
			}
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return keyword;
		}
		return result;
	}

	private static String parseDoubleQuotes(String keyword,
			Set<String> quotesSet) throws Exception {
		int first = StringUtils.indexOf(keyword, '\"');
		if (first < 0)
			return keyword;

		int second = StringUtils.indexOf(keyword, '\"', first + 1);
		if (second < 0)
			return keyword;

		String newStr = StringUtils.left(keyword, first)
				+ StringUtils.substring(keyword, second + 1);
		quotesSet.add(StringUtils.substring(keyword, first, second + 1));

		return parseDoubleQuotes(newStr, quotesSet);
	}
}
