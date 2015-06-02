package com.fast2.zimin.util;

import org.apache.commons.lang.RandomStringUtils;

/**
 * Created by ez2sarang on 15. 5. 12..
 */
public class SimpleRandomKeyFactory {
    private static final int HEADER_COUNT = 8;
    private static final int FOOTER_COUNT = 10;
    private static final String DELIMITER = "-";

    public synchronized String createSessionKey() {
        String header = RandomStringUtils.randomNumeric(HEADER_COUNT);
        String footer = RandomStringUtils.randomNumeric(FOOTER_COUNT);
        return header + DELIMITER + footer;
    }

    public static void main(String[] args) {
        SimpleRandomKeyFactory srf = new SimpleRandomKeyFactory();
        System.out.println(srf.createSessionKey());
    }
}
