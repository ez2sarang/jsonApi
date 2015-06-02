package com.fast2.zimin.navigator.config;

/**
 * Created by ez2sarang on 15. 4. 8..
 */
public enum ContentSubsetType {
    Movie("11")
    , Preview("12")
    , Poster("21")
    , Thumbnail("22")
    ;

    public final String code;

    ContentSubsetType (String code) {
        this.code = code;
    }
}
