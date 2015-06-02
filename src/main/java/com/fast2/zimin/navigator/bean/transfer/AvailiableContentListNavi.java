package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;

import java.util.List;

/**
 * Created by ez2sarang on 15. 5. 18..
 */
public class AvailiableContentListNavi extends PageableNavi {
    private List<ContentInfo> contentList;
    public AvailiableContentListNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public AvailiableContentListNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public AvailiableContentListNavi(ResultType result) {
        super(result);
    }

    public AvailiableContentListNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public List<ContentInfo> getContentList() {
        return contentList;
    }

    public void setContentList(List<ContentInfo> contentList) {
        this.contentList = contentList;
    }
}
