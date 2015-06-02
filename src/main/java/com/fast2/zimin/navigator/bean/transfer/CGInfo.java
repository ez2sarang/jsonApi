package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.controller.PresentationController;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Created by ez2sarang on 15. 5. 15..
 */
public class CGInfo {
    private long cgId;
    private String cgTitle;
    private String cgPoster;
    @JsonIgnore
    private String fileType;

    public long getCgId() {
        return cgId;
    }

    public void setCgId(long cgId) {
        this.cgId = cgId;
    }

    public String getCgPoster() {
        return String.format(PresentationController.POSTER_URL_FORM, PresentationController.SERVER_CONTEXT_PATH, PresentationController.POSTER_PREFIX, cgPoster);
    }

    public void setCgPoster(String cgPoster) {
        this.cgPoster = cgPoster;
    }

    public String getCgTitle() {
        return cgTitle;
    }

    public void setCgTitle(String cgTitle) {
        this.cgTitle = cgTitle;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
