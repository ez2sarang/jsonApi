package com.fast2.zimin.navigator.bean.transfer;

import com.fast2.zimin.navigator.config.ResultType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * Created by ez2sarang on 15. 3. 25..
 */
public class MenuListNavi extends ResponseNavi {
    @JsonIgnore
    private Long rootMenuId;
    private List<MenuInfo> menuList;

    public MenuListNavi() {
        super(ResultType.UNKNOWN_ERROR);
    }

    public MenuListNavi(ResultType result, String transactionToken, List<MenuInfo> menuList) {
        super(result, transactionToken);
        this.menuList = menuList;
    }

    public MenuListNavi(ResultType result, String transactionToken) {
        super(result, transactionToken);
    }

    public MenuListNavi(ResultType result) {
        super(result);
    }

    public MenuListNavi(String resultCode, String errorString) {
        super(resultCode, errorString);
    }

    public List<MenuInfo> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<MenuInfo> menuList) {
        this.menuList = menuList;
    }

    public void setRootMenuId(Long rootMenuId) {
        this.rootMenuId = rootMenuId;
    }

    public Long getRootMenuId() {
        return rootMenuId;
    }
}
