package com.fast2.zimin.navigator.controller;

import com.fast2.zimin.navigator.bean.entity.Menu;
import com.fast2.zimin.navigator.bean.entity.Offer;
import com.fast2.zimin.navigator.bean.transfer.*;
import com.fast2.zimin.navigator.config.ResultType;
import com.fast2.zimin.navigator.service.AuthenticationService;
import com.fast2.zimin.navigator.service.PresentationService;
import com.fast2.zimin.navigator.service.RentalService;
import com.fast2.zimin.util.MessageSourceAccessor;
import com.fast2.zimin.util.TheLogger;
import com.fast2.zimin.util.datatable.Columns;
import com.fast2.zimin.util.datatable.Order;
import com.fast2.zimin.util.datatable.QueryModel;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Properties;

/**
 * Created by ez2sarang on 15. 3. 25..
 */
//@Configuration
@PropertySource("file:${catalina.home}/conf/zimin-navigator/config.properties")
@Controller
public class PresentationController {
    @Autowired
    private PresentationService presentationService;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer;

    @Autowired
    private MessageSourceAccessor wmSource;

    public static final String IMAGE_REPOSITORY_ROOT_PATH;
    public static final String SERVER_CONTEXT_PATH;
    public static final String POSTER_PREFIX;
    public static final String POSTER_URL_FORM;

    static
    {
//        @Inject
//        FileSystemResource fsResource;
//        SERVER_CONTEXT_PATH = System.getProperty("image.local.path");
//        super().getServletContext().getResourceAsStream("/WEB-INF/somename.properties");
        Properties props = null;
        try
        {
//            Properties props = PropertiesLoaderUtils.loadAllProperties("file:${catalina.home}/conf/zimin-navigator/config.properties");
//            Properties props = PropertiesLoaderUtils.loadProperties(new FileSystemResource("file:${catalina.home}/conf/zimin-navigator/config.properties"));
//            props = PropertiesLoaderUtils.loadProperties(new UrlResource("file:${catalina.home}/conf/zimin-navigator/config.properties"));
            props = PropertiesLoaderUtils.loadProperties(new UrlResource("file:"+System.getProperty("catalina.home")+"/conf/zimin-navigator/config.properties"));
//            props = PropertiesLoaderUtils.loadProperties(new UrlResource("file:/Users/ez2sarang/Documents/dev/server/tomcat/apache-tomcat-7.0.47/conf/zimin-navigator/config.properties"));
//            PropertyPlaceholderConfigurer configurer=new PropertyPlaceholderConfigurer();
            System.out.println("============================");
            System.out.println(props.getProperty("image.local.path"));
            System.out.println(props.getProperty("${image.local.path}"));
            System.out.println(System.getProperty("catalina.home"));
//            System.out.println(wmSource);
            System.out.println("============================");
        }
        catch(Exception err)
        {
            err.printStackTrace();
            throw new ExceptionInInitializerError(err);
        }
        finally
        {
            IMAGE_REPOSITORY_ROOT_PATH = props.getProperty("image.local.path");
            SERVER_CONTEXT_PATH = props.getProperty("server.context.path");
            POSTER_PREFIX = props.getProperty("image.url.prefix");
            POSTER_URL_FORM = props.getProperty("image.url.form");
        }
    }


/*    @Inject
    public PresentationController(@Value("${image.local.path}")final String imageLocalPath, @Value("${image.context.path}")final String serverContextPath) {
        IMAGE_REPOSITORY_ROOT_PATH = imageLocalPath;
//        SERVER_CONTEXT_PATH = serverContextPath;
    }*/

    /*public static String getPath(@Value("${image.local.path}")final String imageLocalPath) {
        return imageLocalPath;
    }*/

    @RequestMapping(value = "/sample")
    public String sample(HttpServletRequest request) {
        /*MenuListNavi navi = new MenuListNavi(ResultType.UNKNOWN_ERROR, request.getParameter("transactionToken"));
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                return navi;
            } else {
                return new MenuListNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new MenuListNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }*/
        return "postTest";
    }

    @RequestMapping(value = "/getMenuList")
    public @ResponseBody
    ResponseNavi getMenuList(HttpServletRequest request, @ModelAttribute("navi") MenuListNavi navi) throws Exception {
        try {
            navi.setRootMenuId("".equals(StringUtils.defaultString(request.getParameter("menuPid")))?null:NumberUtils.toLong(request.getParameter("menuPid")));
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                Menu parentMenu = new Menu();
                parentMenu.setParentId(navi.getRootMenuId());
                navi.setMenuList(presentationService.getTreeRoot(parentMenu));
                return navi;
            } else {
                return new MenuListNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new MenuListNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/getMenuInfo")
    public @ResponseBody
    ResponseNavi getMenuInfo(@ModelAttribute("navi") MenuInfoNavi navi) throws Exception {
        try {
            authenticationService.validSession(navi);
            if(navi.getMenuId() == null || 1 > navi.getMenuId()) {
                navi.setResultCode(ResultType.INVALID_PARAMETER, "menuId에 잘 못 된 값이 요청되었습니다.");
                return navi;
            }
            if(navi.getResultCode() == ResultType.OK.code) {
                presentationService.getMenuInfo(navi);
                return navi;
            } else {
                return new MenuInfoNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new MenuInfoNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/getOfferList")
    public @ResponseBody
    ResponseNavi getOfferList(HttpServletRequest request, @ModelAttribute("navi") OfferListNavi navi) throws Exception {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                if(navi.getListCount() == null || 1 > navi.getListCount()) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "화면에 표시할 목록이 1보다 적은 값이 요청되었습니다.");
                    return navi;
                }
                QueryModel queryModel = new QueryModel();
                queryModel.setStart(navi.getListCount() * (navi.getPageNo() - 1));
                queryModel.setLength(navi.getListCount());
                if (!"".equals(StringUtils.defaultString(request.getParameter("sortItem")))) {
                    queryModel.setColumns(Arrays.asList(new Columns[]{new Columns(request.getParameter("sortItem"))}));
                    queryModel.setOrder(Arrays.asList(new Order[]{new Order(0, request.getParameter("sortDirection"))}));
                }
                presentationService.getOfferList(navi, queryModel, NumberUtils.toLong(request.getParameter("menuId")));
                return navi;
            } else {
                return new OfferListNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new OfferListNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/getPcgInfo")
    public @ResponseBody
    ResponseNavi getPcgInfo(HttpServletRequest request, @ModelAttribute("navi") OfferInfoNavi navi) throws Exception {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                long offerId = NumberUtils.toInt(request.getParameter("offerId"));
                String sourceId = request.getParameter("sourceId");
                if(1 > offerId) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "offerId에 잘 못 된 값이 요청되었습니다.");
                    return navi;
                }
                /*if(null == sourceId) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "sourceId 값은 필수 입니다.");
                    return navi;
                }*/
                presentationService.getOfferInfo(navi, offerId);
                return navi;
            } else {
                return new OfferInfoNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new OfferInfoNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/getRelatedOfferList")
    public @ResponseBody
    ResponseNavi getRelatedOfferList(HttpServletRequest request, @ModelAttribute("navi") RelatedOfferListNavi navi) {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                long offerId = NumberUtils.toInt(request.getParameter("offerId"));
                if(1 > offerId) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "offerId에 잘 못 된 값이 요청되었습니다.");
                    return navi;
                }
                navi.setOfferList(presentationService.getRelatedOfferList(navi.getUserId(), offerId));
                return navi;
            } else {
                return new RelatedOfferListNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new RelatedOfferListNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/getCGInfo")
    public @ResponseBody
    ResponseNavi getCGInfo(HttpServletRequest request, @ModelAttribute("navi") CGInfoNavi navi) {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                long offerId = NumberUtils.toInt(request.getParameter("offerId"));
                if(1 > offerId) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "offerId에 잘 못 된 값이 요청되었습니다.");
                    return navi;
                }
                Offer offer =presentationService.getOfferInfo(offerId);
                if(null == offer) {
                    navi.setResultCode(ResultType.NOT_FOUND, "offer를 찾을 수 없습니다.");
                    return navi;
                }
                navi.setOfferTitle(offer.getTitle());
                navi.setCgList(presentationService.getCGInfoList(offerId));
                return navi;
            } else {
                return new CGInfoNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new CGInfoNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/getAssetType")
    public @ResponseBody
    ResponseNavi getAssetType(@ModelAttribute("navi") AssetTypeNavi navi) {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                if(navi.getOfferId() == null || 1 > navi.getOfferId()) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "offerId에 잘 못 된 값이 요청되었습니다.");
                    return navi;
                }
                if(navi.getCgId() == null || 1 > navi.getCgId()) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "cgId에 잘 못 된 값이 요청되었습니다.");
                    return navi;
                }
                String assetType = presentationService.getAssetType(navi.getOfferId(), navi.getCgId());
                if(null == assetType) {
                    navi.setResultCode(ResultType.NOT_FOUND, "assetType을 찾을 수 없습니다.");
                    return navi;
                }
                navi.setAssetType(assetType);
                return navi;
            } else {
                return new AssetTypeNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new AssetTypeNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = "/getSSContentInfo")
    public @ResponseBody
    ResponseNavi getSSContentInfo(@ModelAttribute("navi") SSContentInfoNavi navi) {
        try {
            authenticationService.validSession(navi);
            if(navi.getResultCode() == ResultType.OK.code) {
                if(navi.getOfferId() == null || 1 > navi.getOfferId()) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "offerId에 잘 못 된 값이 요청되었습니다.");
                    return navi;
                }
                if(navi.getCgId() == null || 1 > navi.getCgId()) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "cgId에 잘 못 된 값이 요청되었습니다.");
                    return navi;
                }
                if("".equals(StringUtils.defaultString(navi.getAssetType()))) {
                    navi.setResultCode(ResultType.INVALID_PARAMETER, "assetType에 잘 못 된 값이 요청되었습니다.");
                    return navi;
                }
                presentationService.setSSContentInfo(navi);
                return navi;
            } else {
                return new SSContentInfoNavi(ResultType.SESSION_OUT);
            }
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new SSContentInfoNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = /*POSTER_PREFIX+*/"/posterImagesXX/{zippedSubPathUrl}")
    public ResponseEntity<byte[]> posterImagesXX(@PathVariable final String zippedSubPathUrl) throws Exception {
        InputStream is = null;
        try {
            //BannerImage bannerImage = bannerService.getBannerImage(idx);
            is = new FileInputStream(IMAGE_REPOSITORY_ROOT_PATH + zippedSubPathUrl/*bannerImage.getFilePath()*/);

            final HttpHeaders headers = new HttpHeaders();
            String fileExt = FilenameUtils.getExtension(zippedSubPathUrl);//FilenameUtils.getExtension(bannerImage.getOriginalFileName());
            if (StringUtils.equalsIgnoreCase(fileExt, "jpg"))
                headers.setContentType(MediaType.IMAGE_JPEG);
            else
                headers.setContentType(MediaType.parseMediaType("image/" + fileExt.toLowerCase()));
            return new ResponseEntity<byte[]>(IOUtils.toByteArray(is), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
    }

    @RequestMapping(value = "/notifyContentPlay")
    public @ResponseBody
    ResponseNavi notifyContentPlay(@ModelAttribute("navi") NotifyContentPlayNavi navi) {
        try {
            if(navi.getCgId() == null || 1 > navi.getCgId()) {
                navi.setResultCode(ResultType.INVALID_PARAMETER, "cgId에 잘 못 된 값이 요청되었습니다.");
                return navi;
            }
            if("".equals(StringUtils.defaultString(navi.getAssetType()))) {
                navi.setResultCode(ResultType.INVALID_PARAMETER, "assetType에 잘 못 된 값이 요청되었습니다.");
                return navi;
            }
            if("".equals(StringUtils.defaultString(navi.getControlType()))) {
                navi.setResultCode(ResultType.INVALID_PARAMETER, "control type에 잘 못 된 값이 요청되었습니다.");
                return navi;
            }
            if(0 > navi.getOffset()) {
                navi.setResultCode(ResultType.INVALID_PARAMETER, "offset에 잘 못 된 값이 요청되었습니다.");
                return navi;
            }
            if("".equals(StringUtils.defaultString(navi.getUserId()))) {
                navi.setResultCode(ResultType.INVALID_PARAMETER, "User ID에 잘 못 된 값이 요청되었습니다.");
                return navi;
            }
            rentalService.setNotifyContentPlay(navi);
            return navi;
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            return new NotifyContentPlayNavi(ResultType.UNKNOWN_ERROR.code, e.getMessage());
        }
    }

    @RequestMapping(value = /*POSTER_PREFIX+*/"/posterImages")
    public ResponseEntity<byte[]> posterImages(@RequestParam("pId") String pId) throws Exception {
        InputStream is = null;
        try {
            //BannerImage bannerImage = bannerService.getBannerImage(idx);
            is = new FileInputStream(IMAGE_REPOSITORY_ROOT_PATH + pId/*bannerImage.getFilePath()*/);

            final HttpHeaders headers = new HttpHeaders();
            String fileExt = FilenameUtils.getExtension(pId);//FilenameUtils.getExtension(bannerImage.getOriginalFileName());
            if (StringUtils.equalsIgnoreCase(fileExt, "jpg"))
                headers.setContentType(MediaType.IMAGE_JPEG);
            else
                headers.setContentType(MediaType.parseMediaType("image/" + fileExt.toLowerCase()));
            return new ResponseEntity<byte[]>(IOUtils.toByteArray(is), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            TheLogger.error(e, "Exeption[%s]", e.getMessage());
            throw new Exception(e.getMessage());
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
