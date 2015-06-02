package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;

/**
 * Created by ez2sarang on 15. 5. 20..
 */
public class MenuInfoNavi extends ResponseNavi {
    private Long menuId;
    private String menuTitle;
    private String menuType;
    private Boolean isLeaf;
    public MenuInfoNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public MenuInfoNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public MenuInfoNavi(ResultType result) {
        super(result);
    }

    public MenuInfoNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public Boolean getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(Boolean isLeaf) {
        this.isLeaf = isLeaf;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuTitle() {
        return menuTitle;
    }

    public void setMenuTitle(String menuTitle) {
        this.menuTitle = menuTitle;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }
}
