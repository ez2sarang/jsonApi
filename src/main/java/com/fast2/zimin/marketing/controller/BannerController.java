package com.fast2.zimin.marketing.controller;

import com.fast2.zimin.marketing.entity.Banner;
import com.fast2.zimin.marketing.entity.BannerImage;
import com.fast2.zimin.marketing.service.BannerService;
import com.fast2.zimin.system.service.CommonCodeManager;
import com.fast2.zimin.util.*;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@SessionAttributes("banner")
public class BannerController {
	@Autowired
	private BannerService bannerService;

	@Autowired
	private CommonCodeManager commonCodeManager;

	@Autowired
	private MessageSourceAccessor wmSource;

//	@Value("${system.baseFileRepositoryPath}")
	private String baseFilePath; // D:/temp

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat(
				DateUtil.defaultDateTimeFormat), true);
		binder.registerCustomEditor(Date.class, editor);
	}

	@RequestMapping(value = "/marketing/getBannerListForm")
	public String getBannerListForm(Model model, HttpServletRequest request)
			throws Exception {
		try {
			Banner banner = new Banner();

			model.addAttribute("banner", banner);
			model.addAttribute("bannerTypeCodeList", commonCodeManager
					.getCodeList(Constants.COMMONCODE.GROUP_CODE_BANNER_TYPE));
			model.addAttribute(
					"linkTypeCodeList",
					commonCodeManager
							.getCodeList(Constants.COMMONCODE.GROUP_CODE_BANNER_LINK_TYPE));
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception("Banner System Error");
		}
        if (StringUtils.equals(request.getParameter("popupOption"), "selectManage")) {
            return "marketing/get_banner_list_popup";
        } else {
            return "marketing/get_banner_list_main";
        }
	}

	@RequestMapping(value = "/marketing/getBannerList")
	public @ResponseBody
	DataTableObject getBannerList(Model model, HttpServletRequest request, @ModelAttribute("entity") Banner banner)
			throws Exception {
		DataTableObject bean = new DataTableObject();

		try {
			/*Banner banner = new Banner();
			banner.setBannerName(request.getParameter("bannerName"));
			banner.setBannerType(request.getParameter("bannerType"));
			banner.setLinkType(request.getParameter("linkType"));
			banner.setSearchOptionPeriodPast(BooleanUtils.toBoolean(request
					.getParameter("searchOptionPeriodPast")));
			banner.setSearchOptionPeriodPresent(BooleanUtils.toBoolean(request
					.getParameter("searchOptionPeriodPresent")));
			banner.setSearchOptionPeriodFuture(BooleanUtils.toBoolean(request
					.getParameter("searchOptionPeriodFuture")));*/

			String orderCloumnNum = request.getParameter("order[0][column]");
			String orderDir = "";
			String orderColumnName = "";
			try {
				if (BooleanUtils.toBoolean(request.getParameter(String.format(
						"columns[%s][orderable]", orderCloumnNum)))) {
					orderColumnName = request.getParameter(String.format(
							"columns[%s][data]", orderCloumnNum));
					orderDir = request.getParameter("order[0][dir]");
				}
			} catch (Exception e) {
				TheLogger.error(e, "Exeption[%s]", e.getMessage());
			}

			bean.setDraw(NumberUtils.toInt(request.getParameter("draw"), 1));
			bean.setRecordsTotal(bannerService.getBannerCount(new Banner()));
			bean.setRecordsFiltered(bannerService.getBannerCount(banner));
			bean.setData(bannerService.getBannerList(banner,
					NumberUtils.toInt(request.getParameter("length"), 10),
					NumberUtils.toInt(request.getParameter("start"), 0),
					orderColumnName, orderDir));

		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception("Banner System Error");
		}

		return bean;
	}

	@RequestMapping(value = "/marketing/addBannerForm", method = RequestMethod.GET)
	public String addBannerForm(Model model, HttpServletRequest request)
			throws Exception {
		Banner banner = new Banner();

		try {
			model.addAttribute("banner", banner);
			model.addAttribute("localeList", LocaleUtil.getLocaleList());
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "marketing/add_banner_popup";
	}

	@RequestMapping(value = "/marketing/addBanner", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String addBanner(@ModelAttribute("banner") Banner banner,
			MultipartHttpServletRequest request, SessionStatus status)
			throws Exception {
		List<String> uploadedFileList = new ArrayList<>();
		try {
			String fileDirStr = getTargetBannerDir();
			File fileDir = new File(fileDirStr);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}

			List<BannerImage> bannerImageList = new ArrayList<>();
			Map<String, MultipartFile> fileMap = request.getFileMap();
			for (MultipartFile multipartFile : fileMap.values()) {
				if (StringUtils.isNotBlank(multipartFile.getOriginalFilename())
						&& multipartFile.getSize() > 0) {
					BannerImage bannerImage = new BannerImage();
					bannerImage.setLocaleCode(multipartFile.getName());
					// baseFilePath/banner/201408/20140828185959256
					String filePath = fileDirStr + "/"
							+ System.currentTimeMillis()
							+ (int) (Math.random() * 1000);
					bannerImage.setFilePath(filePath);
					bannerImage.setOriginalFileName(multipartFile
							.getOriginalFilename());
					bannerImageList.add(bannerImage);

					File file = new File(filePath);
					multipartFile.transferTo(file);
					uploadedFileList.add(filePath);
				}
			}

			banner.setBannerImageList(bannerImageList);

			String userId = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			Date now = new Date();

			banner.setCreateId(userId);
			banner.setCreateTime(now);
			banner.setUpdateId(userId);
			banner.setUpdateTime(now);

			bannerService.addBanner(banner);

			status.setComplete();
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());

			// 업로드된 파일 삭제
			for (String filePath : uploadedFileList) {
				try {
					File file = new File(filePath);
					if (file.isFile())
						file.delete();
				} catch (Exception e2) {
				}
			}

			return "Banner System Error";
		}

		return "Success";
	}

	@RequestMapping(value = "/marketing/editBannerForm", method = RequestMethod.GET)
	public String editBannerForm(Model model, HttpServletRequest request)
			throws Exception {
		String bannerId = request.getParameter("bannerId");
		if (StringUtils.isBlank(bannerId)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"bannerId"));
		}

		try {
			model.addAttribute("banner",
					bannerService.getBanner(NumberUtils.toLong(bannerId)));
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "marketing/edit_banner_popup";
	}

	@RequestMapping(value = "/marketing/editBanner", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String editBanner(@ModelAttribute("banner") Banner banner,
			HttpServletRequest request, SessionStatus status) throws Exception {
		try {
			banner.setUpdateId(SecurityContextHolder.getContext()
					.getAuthentication().getName());
			banner.setUpdateTime(new Date());
			bannerService.editBanner(banner);

			status.setComplete();
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "Banner System Error";
		}

		return "Success";
	}

	@RequestMapping(value = "/marketing/addBannerImageForm", method = RequestMethod.GET)
	public String addBannerImageForm(Model model, HttpServletRequest request)
			throws Exception {
		String bannerId = request.getParameter("bannerId");
		if (StringUtils.isBlank(bannerId)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"bannerId"));
		}
		String localeCode = request.getParameter("localeCode");
		if (StringUtils.isBlank(bannerId)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"localeCode"));
		}

		try {
			BannerImage bannerImage = new BannerImage();
			bannerImage.setBannerId(NumberUtils.toLong(bannerId));
			bannerImage.setLocaleCode(localeCode);
			model.addAttribute("bannerImage", bannerImage);
			model.addAttribute("language",
					LocaleUtil.getLanguage(bannerImage.getLocaleCode()));
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "marketing/add_banner_image_popup";
	}

	@RequestMapping(value = "/marketing/addBannerImage", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String addBannerImage(
			@ModelAttribute("bannerImage") BannerImage bannerImage,
			MultipartHttpServletRequest request, SessionStatus status)
			throws Exception {
		try {
			// 파일 저장 디렉토리 생성
			String fileDirStr = getTargetBannerDir();
			File fileDir = new File(fileDirStr);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}

			String filePath = fileDirStr + "/" + System.currentTimeMillis()
					+ (int) (Math.random() * 1000);

			MultipartFile multipartFile = request.getFile("newFile");
			bannerImage.setFilePath(filePath);
			bannerImage
					.setOriginalFileName(multipartFile.getOriginalFilename());

			File file = new File(filePath);
			multipartFile.transferTo(file);

			bannerService.addBannerImage(bannerImage);

			status.setComplete();
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());

			// 업로드된 파일 삭제
			try {
				File file = new File(bannerImage.getFilePath());
				if (StringUtils.isNotBlank(bannerImage.getFilePath())
						&& file.isFile())
					file.delete();
			} catch (Exception e2) {
			}

			return "Banner System Error";
		}

		return "Success";
	}

	@RequestMapping(value = "/marketing/editBannerImageForm", method = RequestMethod.GET)
	public String editBannerImageForm(Model model, HttpServletRequest request)
			throws Exception {
		String idx = request.getParameter("idx");
		if (StringUtils.isBlank(idx)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"idx"));
		}

		try {
			BannerImage bannerImage = bannerService.getBannerImage(NumberUtils
					.toLong(idx));
			model.addAttribute("bannerImage", bannerImage);
			model.addAttribute("language",
					LocaleUtil.getLanguage(bannerImage.getLocaleCode()));
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "marketing/edit_banner_image_popup";
	}

	@RequestMapping(value = "/marketing/editBannerImage", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String editBannerImage(
			@ModelAttribute("bannerImage") BannerImage bannerImage,
			MultipartHttpServletRequest request, SessionStatus status)
			throws Exception {
		try {
			// 기존파일
			String originFilePath = bannerImage.getFilePath();

			// 파일 저장 디렉토리 생성
			String fileDirStr = getTargetBannerDir();
			File fileDir = new File(fileDirStr);
			if (!fileDir.exists()) {
				fileDir.mkdirs();
			}

			String filePath = fileDirStr + "/" + System.currentTimeMillis()
					+ (int) (Math.random() * 1000);

			MultipartFile multipartFile = request.getFile("newFile");
			bannerImage.setFilePath(filePath);
			bannerImage
					.setOriginalFileName(multipartFile.getOriginalFilename());

			File file = new File(filePath);
			multipartFile.transferTo(file);

			bannerService.editBannerImage(bannerImage);

			// 기존파일 삭제
			try {
				File originFile = new File(originFilePath);
				if (originFile.isFile())
					originFile.delete();
			} catch (Exception e) {
			}

			status.setComplete();
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());

			// 업로드된 파일 삭제
			try {
				File file = new File(bannerImage.getFilePath());
				if (StringUtils.isNotBlank(bannerImage.getFilePath())
						&& file.isFile())
					file.delete();
			} catch (Exception e2) {
			}

			return "Banner System Error";
		}

		return "Success";
	}

	@RequestMapping(value = "/marketing/deleteBannerImage", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String deleteBannerImage(HttpServletRequest request) throws Exception {
		String idx = request.getParameter("idx");
		if (StringUtils.isBlank(idx)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"idx"));
		}

		try {
			BannerImage bannerImage = bannerService.getBannerImage(NumberUtils
					.toLong(idx));
			String filePath = bannerImage.getFilePath();
			bannerService.deleteBannerImage(bannerImage);
			try {
				File file = new File(filePath);
				if (file.isFile())
					file.delete();
			} catch (Exception e) {
			}
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "Banner System Error";
		}

		return "Success";
	}

	@RequestMapping(value = "/marketing/deleteBanner/{bannerId}", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String deleteBanner(HttpServletRequest request,
			@PathVariable final Long bannerId) throws Exception {
		try {
			Banner banner = bannerService.getBannerWithChildren(bannerId);
			bannerService.deleteBanner(banner);
			for (BannerImage bannerImage : banner.getBannerImageList()) {
				try {
					File file = new File(bannerImage.getFilePath());
					if (file.isFile())
						file.delete();
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "Banner System Error";
		}

		return "Success";
	}

	@RequestMapping(value = "/marketing/viewBanner", method = RequestMethod.GET)
	public String viewBanner(Model model, HttpServletRequest request)
			throws Exception {
		String bannerId = request.getParameter("bannerId");
		if (StringUtils.isBlank(bannerId)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"bannerId"));
		}

		try {
			Banner banner = bannerService.getBannerWithChildren(NumberUtils
					.toLong(bannerId));
			Map<String, BannerImage> bannerImageMap = new HashMap<String, BannerImage>();
			for (BannerImage bannerImage : banner.getBannerImageList()) {
				bannerImageMap.put(bannerImage.getLocaleCode(), bannerImage);
			}

			model.addAttribute("banner", banner);
			model.addAttribute("localeList", LocaleUtil.getLocaleList());
			model.addAttribute("bannerImageMap", bannerImageMap);
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "marketing/view_banner_div";
	}

	@RequestMapping(value = "/marketing/getBannerImage/{idx}", method = RequestMethod.GET)
	public ResponseEntity<byte[]> getBannerImage(@PathVariable final Long idx)
			throws Exception {
		InputStream is = null;
		try {
			BannerImage bannerImage = bannerService.getBannerImage(idx);
			is = new FileInputStream(bannerImage.getFilePath());

			final HttpHeaders headers = new HttpHeaders();
			String fileExt = FilenameUtils.getExtension(bannerImage
					.getOriginalFileName());
			if (StringUtils.equalsIgnoreCase(fileExt, "jpg"))
				headers.setContentType(MediaType.IMAGE_JPEG);
			else
				headers.setContentType(MediaType.parseMediaType("image/"
						+ fileExt.toLowerCase()));

			return new ResponseEntity<byte[]>(IOUtils.toByteArray(is), headers,
					HttpStatus.CREATED);
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

	private String getTargetBannerDir() throws Exception {
		if (StringUtils.isBlank(baseFilePath)) {
			throw new Exception("Not set [system.baseFileRepositoryPath=]");
		}

		return baseFilePath + "/" + "banner" + "/"
				+ DateUtil.getCurrentStr("yyyyMM");
	}
}
