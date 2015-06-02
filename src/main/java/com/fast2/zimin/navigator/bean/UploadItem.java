package com.fast2.zimin.navigator.bean;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Created by ez2sarang on 15. 5. 27..
 */
public class UploadItem {
    private String name;
//    private CommonsMultipartFile fileData;
    private CommonsMultipartFile[] fileData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CommonsMultipartFile[] getFileData() {
        return fileData;
    }

    public void setFileData(CommonsMultipartFile[] fileData) {
        this.fileData = fileData;
    }
}
