package com.fast2.zimin.navigator.controller;

import com.fast2.zimin.navigator.bean.transfer.*;
import com.fast2.zimin.navigator.config.ResultType;
import com.fast2.zimin.navigator.service.AuthenticationService;
import com.fast2.zimin.navigator.service.RentalService;
import com.fast2.zimin.util.TheLogger;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ez2sarang on 15. 4. 3..
 */
@Controller
public class RentalController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RentalService rentalService;

    @RequestMapping(value = "/purchaseOffer")
    public @ResponseBody
    ResponseNavi purchaseOffer(@ModelAttribute("navi") PurchaseOfferNavi navi) {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                if(!authenticationService.checkPassword(navi.getSessionToken(), navi.getPurchasePW())) {
                    navi.setResultCode(ResultType.INVALID_PASSWORD);
                    return navi;
                }
                rentalService.rentalOffer(navi);
                return navi;
            } else {
                return new PurchaseOfferNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new PurchaseOfferNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/getPurchasedList")
    public @ResponseBody
    ResponseNavi getPurchasedList(@ModelAttribute("navi") PurchasedListNavi navi) {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                if(navi.getListCount() == null || 1 > navi.getListCount()) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "화면에 표시할 목록이 1보다 적은 값이 요청되었습니다.");
                    return navi;
                }
                rentalService.setRentalOfferList(navi);
                return navi;
            } else {
                return new PurchasedListNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new PurchasedListNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/getViewedList")
    public @ResponseBody
    ResponseNavi getViewedList(@ModelAttribute("navi") ViewedListNavi navi) {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                if(navi.getListCount() == null || 1 > navi.getListCount()) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "화면에 표시할 목록이 1보다 적은 값이 요청되었습니다.");
                    return navi;
                }
                rentalService.setViewedList(navi);
                return navi;
            } else {
                return new ViewedListNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new ViewedListNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/getAvailiableContentList")
    public @ResponseBody
    ResponseNavi getAvailiableContentList(@ModelAttribute("navi") AvailiableContentListNavi navi) {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                if(navi.getListCount() == null || 1 > navi.getListCount()) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "화면에 표시할 목록이 1보다 적은 값이 요청되었습니다.");
                    return navi;
                }
                rentalService.setAvailiableContentList(navi);
                return navi;
            } else {
                return new AvailiableContentListNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new AvailiableContentListNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/checkViewAuthority")
    public @ResponseBody
    CheckViewAuthorityNavi checkViewAuthority(@ModelAttribute("navi") CheckViewAuthorityNavi navi) {
        try {
            if(navi.getCgId() == null || navi.getCgId() < 1) {
                navi.setResultCode(ResultType.INVALID_PARAMETER, "컨텐츠그룹 아이디가 없습니다.");
                return navi;
            }
            if("".equals(StringUtils.defaultString(navi.getAssetType()))) {
                navi.setResultCode(ResultType.INVALID_PARAMETER, "에셋타입이 없습니다.");
                return navi;
            }
            if("".equals(StringUtils.defaultString(navi.getClientId()))) {
                navi.setResultCode(ResultType.INVALID_PARAMETER, "사용자 아이디가 없습니다.");
                return navi;
            }
            if("".equals(StringUtils.defaultString(navi.getFileName()))) {
                navi.setResultCode(ResultType.INVALID_PARAMETER, "파일명이 없습니다.");
                return navi;
            }
            rentalService.setCheckViewAuth(navi);
            return navi;
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new CheckViewAuthorityNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }
}
