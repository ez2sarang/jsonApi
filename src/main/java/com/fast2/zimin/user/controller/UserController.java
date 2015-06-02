package com.fast2.zimin.user.controller;

import com.fast2.zimin.user.service.UserService;
import com.fast2.zimin.util.MessageSourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class UserController {
	@Autowired
	private UserService userService;

	@Autowired
	private MessageSourceAccessor wmSource;
	
	/*@Autowired
	private ContentProviderService contentProviderService;

	@RequestMapping(value = "/mymenu/editAccountForm", method = RequestMethod.GET)
	public String editAccountForm(Model model, HttpServletRequest request)
			throws Exception {
		try {
			String userId = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			model.addAttribute("user", userService.getUser(userId));
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "system/user/edit_account_popup";
	}

	@RequestMapping(value = "/mymenu/editAccount", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String editAccount(@ModelAttribute("user") User user,
			HttpServletRequest request, SessionStatus status) throws Exception {
		try {
			user.setUpdateId(SecurityContextHolder.getContext()
					.getAuthentication().getName());
			user.setUpdateTime(new Date());
			userService.editUser(user);

			status.setComplete();
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "User System Error";
		}

		return "Success";
	}

	@RequestMapping(value = "/mymenu/editMyPasswordForm", method = RequestMethod.GET)
	public String editMyPasswordForm(Model model, HttpServletRequest request)
			throws Exception {
		return "system/user/edit_my_password_popup";
	}

	@RequestMapping(value = "/mymenu/editMyPassword", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String editMyPassword(HttpServletRequest request) throws Exception {
		String password = request.getParameter("password");
		String newPassword = request.getParameter("newPassword");

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			String userId = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			User user = userService.getUser(userId);

			if (!passwordEncoder.matches(password, user.getPassword())) {
				return wmSource.getMsg("mymenu.error.password.wrong");
			}

			user.setPassword(passwordEncoder.encode(newPassword));
			user.setUpdateId(userId);
			user.setUpdateTime(new Date());
			userService.editUser(user);
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "User System Error";
		}

		return "Success";
	}

	@RequestMapping(value = "/system/getUserList")
	public String getUserList(Model model, HttpServletRequest request)
			throws Exception {
		try {
			User user = new User();
			user.setUserName(request.getParameter("userName"));
			user.setUserId(request.getParameter("userId"));

			model.addAttribute("userList", userService.getUserList(user));
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception("User System Error");
		}

		return "system/user/get_user_list_main";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/system/editUserForm", method = RequestMethod.GET)
	public String editUserForm(Model model, HttpServletRequest request)
			throws Exception {
		String userId = request.getParameter("userId");
		if (StringUtils.isBlank(userId)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"userId"));
		}

		try {
			User user = userService.getUser(userId);
			model.addAttribute("user", user);
			
			List<ContentProviderSelectItem> contentProviderSelectItems = contentProviderService.getContentProviderSelectItemList();
			model.addAttribute("contentProviderSelectItems", contentProviderSelectItems);
			
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "system/user/edit_user_popup";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/system/editUser", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String editUser(@ModelAttribute("user") User user,
			HttpServletRequest request, SessionStatus status) throws Exception {
		try {
			user.setUpdateId(SecurityContextHolder.getContext()
					.getAuthentication().getName());
			user.setUpdateTime(new Date());
			user.setSyncFlag("U");
			userService.editUser(user);

			status.setComplete();
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "User System Error";
		}

		return "Success";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/system/editUserPasswordForm", method = RequestMethod.GET)
	public String editUserPasswordForm(Model model, HttpServletRequest request)
			throws Exception {
		String userId = request.getParameter("userId");
		if (StringUtils.isBlank(userId)) {
			throw new Exception(wmSource.getMsg("exceptions.param.required",
					"userId"));
		}

		model.addAttribute("userId", userId);

		return "system/user/edit_user_password_popup";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/system/editUserPassword", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String editUserPassword(HttpServletRequest request) throws Exception {
		String userId = request.getParameter("userId");
		String newPassword = request.getParameter("newPassword");

		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			User user = userService.getUser(userId);
			user.setPassword(passwordEncoder.encode(newPassword));
			user.setUpdateId(SecurityContextHolder.getContext()
					.getAuthentication().getName());
			user.setUpdateTime(new Date());
			userService.editUser(user);
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "User System Error";
		}

		return "Success";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/system/addUserForm", method = RequestMethod.GET)
	public String addUserForm(Model model, HttpServletRequest request)
			throws Exception {
		User user = new User();

		try {
			// Default 값 셋팅
			user.setState(Constants.COMMONCODE.USER_STATE_NOMAL);
			user.setUserType(Constants.COMMONCODE.USER_TYPE_GEN);
			model.addAttribute("user", user);
			
			List<ContentProviderSelectItem> contentProviderSelectItems = contentProviderService.getContentProviderSelectItemList();
			model.addAttribute("contentProviderSelectItems", contentProviderSelectItems);
			
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			throw new Exception(e.getMessage());
		}

		return "system/user/add_user_popup";
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/system/addUser", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String addUser(@ModelAttribute("user") User user,
			HttpServletRequest request, SessionStatus status) throws Exception {
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			user.setUpdateId(SecurityContextHolder.getContext()
					.getAuthentication().getName());
			user.setUpdateTime(new Date());

			List<Role> roleList = new ArrayList<>();
			//roleList.add(new Role("USER", null, null));
			if (StringUtils.equals(user.getUserType(),
					Constants.COMMONCODE.USER_TYPE_ADMIN)) {
				roleList.add(new Role("ADMIN", null, null));
			} else if (StringUtils.equals(user.getUserType(),
					Constants.COMMONCODE.USER_TYPE_QC)) {
				roleList.add(new Role("QC", null, null));
			} else {
				roleList.add(new Role("USER", null, null));
			}
			user.setRoleList(roleList);

			userService.addUser(user);
			status.setComplete();
		} catch (ConstraintViolationException e) {
			return String.format("User Duplication Error: UserId=%s",
					user.getUserId());
		} catch (Exception e) {
			TheLogger.error(e, "Exeption[%s]", e.getMessage());
			return "User System Error";
		}

		return "Success";
	}*/
}
