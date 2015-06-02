package com.fast2.zimin.navigator.config;

/**
 * Created by ez2sarang on 15. 4. 8..
 */
public enum FileType {
    HD("2")
    , SD("1")
    ;

    public final String code;

    FileType(String code) {
        this.code = code;
    }
}
