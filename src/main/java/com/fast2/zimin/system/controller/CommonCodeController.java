package com.fast2.zimin.system.controller;

import com.fast2.zimin.system.entity.CommonCode;
import com.fast2.zimin.system.service.CommonCodeManager;
import com.fast2.zimin.system.service.CommonCodeService;
import com.fast2.zimin.util.Constants;
import com.fast2.zimin.util.MessageSourceAccessor;
import com.fast2.zimin.util.TheLogger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@SessionAttributes("commonCode")
public class CommonCodeController {
	@Autowired
	private CommonCodeService commonCodeService;

	@Autowired
	private CommonCodeManager commonCodeManager;

	@Autowired
	private MessageSourceAccessor wmSource;

	@RequestMapping(value = "/system/getCommonCodeGroupList")
	public String getCommonCodeGroupList(Model model, HttpServletRequest request)
			throws Exception {
		try {
			model.addAttribute("commonCodeList", commonCodeService.getCodeList(
					Constants.COMMONCODE.ROOT_GROUP_CODE, null, "codeName"));
		} catch (Exception e) {
			TheLogger.error(e, "getCommonCodeGroupList Exeption[%s]",
					e.getMessage());
			throw new Exception("CommonCode System Error");
		}

		return "system/commoncode/get_common_code_group_list_main";
	}

	@PreAuthorize("hasRole('ROLE_SYSADMIN')")
	@RequestMapping(value = "/system/addCommonCodeForm", method = RequestMethod.GET)
	public String addCommonCodeForm(Model model, HttpServletRequest request)
			throws Exception {
		CommonCode commonCode = new CommonCode();

		try {
			if (StringUtils.isBlank(request.getParameter("groupCode"))) {
				commonCode.setGroupCode(Constants.COMMONCODE.ROOT_GROUP_CODE);
			} else {
				commonCode.setGroupCode(request.getParameter("groupCode"));
			}
			commonCode.setUseYn("Y");

			model.addAttribute("commonCode", commonCode);
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "system/commoncode/add_commoncode_popup";
	}

	@PreAuthorize("hasRole('ROLE_SYSADMIN')")
	@RequestMapping(value = "/system/addCommonCode", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String addCommonCode(@ModelAttribute("commonCode") CommonCode commonCode,
			HttpServletRequest request, SessionStatus status) throws Exception {
		try {
			commonCodeService.addCommonCode(commonCode);

			status.setComplete();
		} catch (ConstraintViolationException e) {
			return String.format(
					"Code Duplication Error: GroupCode=%s, Code=%s",
					commonCode.getGroupCode(), commonCode.getCode());
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "CommonCode System Error";
		}

		return "Success";
	}

	@RequestMapping(value = "/system/viewCommonCode", method = RequestMethod.GET)
	public String viewCommonCode(Model model, HttpServletRequest request)
			throws Exception {
		String groupCode = request.getParameter("groupCode");
		if (StringUtils.isBlank(groupCode)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"groupCode"));
		}

		try {
			model.addAttribute("commonCodeGroup", commonCodeService.getCode(
					Constants.COMMONCODE.ROOT_GROUP_CODE, groupCode));
			model.addAttribute("commonCodeList",
					commonCodeService.getCodeList(groupCode, null, null));
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "system/commoncode/view_commoncode_div";
	}

	@PreAuthorize("hasRole('ROLE_SYSADMIN')")
	@RequestMapping(value = "/system/editCommonCodeForm", method = RequestMethod.GET)
	public String editCommonCodeForm(Model model, HttpServletRequest request)
			throws Exception {
		String groupCode = request.getParameter("groupCode");
		if (StringUtils.isBlank(groupCode)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"groupCode"));
		}

		String code = request.getParameter("code");
		if (StringUtils.isBlank(code)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"code"));
		}

		try {
			model.addAttribute("commonCode",
					commonCodeService.getCode(groupCode, code));
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "system/commoncode/edit_commoncode_popup";
	}

	@PreAuthorize("hasRole('ROLE_SYSADMIN')")
	@RequestMapping(value = "/system/editCommonCode", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String editCommonCode(@ModelAttribute("commonCode") CommonCode commonCode,
			HttpServletRequest request, SessionStatus status) throws Exception {
		try {
			commonCodeService.editCommonCode(commonCode);

			status.setComplete();
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "CommonCode System Error";
		}

		return "Success";
	}

	@PreAuthorize("hasRole('ROLE_SYSADMIN')")
	@RequestMapping(value = "/system/deleteCommonCode", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String deleteCommonCode(HttpServletRequest request) throws Exception {
		String groupCode = request.getParameter("groupCode");
		if (StringUtils.isBlank(groupCode)) {
			return wmSource.getMsg("exceptions.param.required", "groupCode");
		}

		String code = request.getParameter("code");
		if (StringUtils.isBlank(code)) {
			return wmSource.getMsg("exceptions.param.required", "code");
		}

		try {
			commonCodeService.deleteCommonCode(groupCode, code);
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "CommonCode System Error";
		}

		return "Success";
	}

	@PreAuthorize("hasRole('ROLE_SYSADMIN')")
	@RequestMapping(value = "/system/deleteCommonCodeGroup", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String deleteCommonCodeGroup(HttpServletRequest request) throws Exception {
		String groupCode = request.getParameter("groupCode");
		if (StringUtils.isBlank(groupCode)) {
			return wmSource.getMsg("exceptions.param.required", "groupCode");
		}

		try {
			commonCodeService.deleteCommonCodesByGroupCode(groupCode);
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "CommonCode System Error";
		}

		return "Success";
	}

	@PreAuthorize("hasRole('ROLE_SYSADMIN')")
	@RequestMapping(value = "/system/reorderCommonCodeForm", method = RequestMethod.GET)
	public String reorderCommonCodeForm(Model model, HttpServletRequest request)
			throws Exception {
		String groupCode = request.getParameter("groupCode");
		if (StringUtils.isBlank(groupCode)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"groupCode"));
		}

		try {
			model.addAttribute("groupCode", groupCode);
			model.addAttribute("commonCodeList",
					commonCodeService.getCodeList(groupCode, null, null));
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "system/commoncode/reoder_commoncode_popup";
	}

	@PreAuthorize("hasRole('ROLE_SYSADMIN')")
	@RequestMapping(value = "/system/reorderCommonCode", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String reorderCommonCode(HttpServletRequest request) throws Exception {
		String groupCode = request.getParameter("groupCode");
		if (StringUtils.isBlank(groupCode)) {
			return wmSource.getMsg("exceptions.param.required", "groupCode");
		}

		String[] codes = request.getParameterValues("code");
		if (codes == null || codes.length <= 0) {
			return wmSource.getMsg("exceptions.param.required", "code");
		}

		try {
			commonCodeService.reorderCommonCode(groupCode, codes);
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "CommonCode System Error";
		}

		return "Success";
	}

	@RequestMapping(value = "/system/applyCommonCodeCache", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String applyCommonCodeCache(HttpServletRequest request) throws Exception {
		String groupCode = request.getParameter("groupCode");
		if (StringUtils.isBlank(groupCode)) {
			return wmSource.getMsg("exceptions.param.required", "groupCode");
		}

		try {
			if (StringUtils.equals(groupCode,
					Constants.COMMONCODE.ROOT_GROUP_CODE)) {
				commonCodeManager.initAll();
			} else {
				commonCodeManager.initGroup(groupCode);
			}
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "CommonCode System Error";
		}

		return "Success";
	}

    @RequestMapping(value = "/assets/getCommonCode", method = RequestMethod.POST, produces = "application/json")
    public
    @ResponseBody
    Map<String, Object> getCommonCode(@RequestParam("groupCode") String groupCode) throws /*Marketer*/Exception {
        Map<String, Object> model = new HashMap<String, Object>();
        /*for(String code : groupCode.split("[^a-zA-Z0-9]")) {

            if("contentSubsetType".equalsIgnoreCase(code)) {
                model.put("contentSubsetType"+Constants.COMMONCODE.CONTENT_SUBSET_TYPE_MOVIE,
                        Functions.convertListToMap(ContentAssetConstantUtil.getContentAssetConstantList(Constants.COMMONCODE.CONTENT_SUBSET_TYPE_MOVIE), new Functions.ListToMapConverter<String, ContentAssetConstant>() {
                            @Override
                            public String getKey(ContentAssetConstant item) {
                                return item.getFileType();
                            }
                        })
                );
                model.put("contentSubsetType"+Constants.COMMONCODE.CONTENT_SUBSET_TYPE_PREVIEW,
                        Functions.convertListToMap(ContentAssetConstantUtil.getContentAssetConstantList(Constants.COMMONCODE.CONTENT_SUBSET_TYPE_PREVIEW), new Functions.ListToMapConverter<String, ContentAssetConstant>() {
                            @Override
                            public String getKey(ContentAssetConstant item) {
                                return item.getFileType();
                            }
                        })
                );
                model.put("contentSubsetType"+Constants.COMMONCODE.CONTENT_SUBSET_TYPE_POSTER,
                        Functions.convertListToMap(ContentAssetConstantUtil.getContentAssetConstantList(Constants.COMMONCODE.CONTENT_SUBSET_TYPE_POSTER), new Functions.ListToMapConverter<String, ContentAssetConstant>() {
                            @Override
                            public String getKey(ContentAssetConstant item) {
                                return item.getFileType();
                            }
                        })
                );
                model.put("contentSubsetType"+Constants.COMMONCODE.CONTENT_SUBSET_TYPE_THUMBNAIL,
                        Functions.convertListToMap(ContentAssetConstantUtil.getContentAssetConstantList(Constants.COMMONCODE.CONTENT_SUBSET_TYPE_THUMBNAIL), new Functions.ListToMapConverter<String, ContentAssetConstant>() {
                            @Override
                            public String getKey(ContentAssetConstant item) {
                                return item.getFileType();
                            }
                        })
                );
            } else {
                List<CommonCode> result = commonCodeManager.getCodeList(code);
                model.put(code, result);
            }

        }*/
        return model;
    }
}
