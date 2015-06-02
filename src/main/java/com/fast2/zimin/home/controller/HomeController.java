package com.fast2.zimin.home.controller;

import com.fast2.zimin.user.entity.ZiminUserDetails;
import com.fast2.zimin.util.Constants;
import com.fast2.zimin.util.MessageSourceAccessor;
import com.fast2.zimin.util.TheLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@Autowired
	private MessageSourceAccessor wmSource;
	
	@Autowired
	LocaleResolver resolver;

	@RequestMapping(value = { "/", "/index**", "/default**" }, method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("home/view_dashboard_main");
		try {
			ZiminUserDetails userDetails = (ZiminUserDetails) SecurityContextHolder
					.getContext().getAuthentication().getPrincipal();
			if (userDetails.getUserType().equals(Constants.COMMONCODE.USER_TYPE_ADMIN)) {
				model.setViewName("redirect:/serviceflow/listOfferConfirmForm.do");
			} else if (userDetails.getUserType().equals(Constants.COMMONCODE.USER_TYPE_QC)) {
				model.setViewName("redirect:/serviceflow/requestQcListForm.do");
			} else {
				model.setViewName("redirect:/asset/getOfferListForm.do");
			}
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
		}
		return model;

	}

	@RequestMapping(value = "/loginxxxx", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid username or password!");
			System.out.println("error-" + error);
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("home/login");

		return model;
	}

	@RequestMapping(value = "/accessDenied", method = RequestMethod.GET)
	public String accessDenied() {
		return "common/exception/access_denied_page";
	}
	
	/*@RequestMapping(value = "/setLocale", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String setLocale(@RequestParam("locale") String locale,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			Locale l = new Locale(locale);
			resolver.setLocale(request, response, l);
			resolver.resolveLocale(request);
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "Set Locale Error";
		}

		return "Success";
	}*/
}