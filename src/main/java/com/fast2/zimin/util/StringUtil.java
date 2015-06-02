package com.fast2.zimin.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils {
	public static String concat(String src, String seperator, String appendStr) {
		String str = defaultString(src);
		if (isNotBlank(str)) {
			str += defaultString(seperator);
		}
		str += defaultString(appendStr);
		return str;
	}
	public static String escapeJSON(String string) {
        if (string == null || string.length() == 0) {
            return "";
        }
        char         c = 0;
        int          i;
        int          len = string.length();
        StringBuilder sb = new StringBuilder(len + 4);
        String       t;

        for (i = 0; i < len; i += 1) {
            c = string.charAt(i);
            switch (c) {
            case '\\':
            case '"':
                sb.append('\\');
                sb.append(c);
                break;
            case '/':
                sb.append('\\');
                sb.append(c);
                break;
            case '\b':
                sb.append("\\b");
                break;
            case '\t':
                sb.append("\\t");
                break;
            case '\n':
                sb.append("\\n");
                break;
            case '\f':
                sb.append("\\f");
                break;
            case '\r':
               sb.append("\\r");
               break;
            default:
                if (c < ' ') {
                    t = "000" + Integer.toHexString(c);
                    sb.append("\\u" + t.substring(t.length() - 4));
                } else {
                    sb.append(c);
                }
            }
        }
        return sb.toString();
    }
}
