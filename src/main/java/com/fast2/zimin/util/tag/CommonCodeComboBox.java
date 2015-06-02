package com.fast2.zimin.util.tag;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.fast2.zimin.system.entity.CommonCode;
import com.fast2.zimin.system.service.CommonCodeService;

@SuppressWarnings("serial")
public class CommonCodeComboBox extends RequestContextAwareTag {
	private static final Logger log = Logger
			.getLogger(CommonCodeComboBox.class);

	private String groupCode;
	private Object selected;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public Object getSelected() {
		return selected;
	}

	public void setSelected(Object selected) {
		this.selected = selected;
	}

	@Autowired
	private CommonCodeService manager;

	@SuppressWarnings("rawtypes")
	@Override
	protected int doStartTagInternal() throws Exception {
		if (manager == null) {
			WebApplicationContext wac = getRequestContext()
					.getWebApplicationContext();
			AutowireCapableBeanFactory acbf = wac
					.getAutowireCapableBeanFactory();
			acbf.autowireBean(this);
		}

		if (StringUtils.isBlank(groupCode)) {
			return SKIP_BODY;
		}

		try {
			List<CommonCode> codeList = manager.getCodeList(groupCode, "Y",
					null);

			String optionStr = "";
			boolean select = false;
			if (codeList != null) {
				for (CommonCode code : codeList) {
					if (selected instanceof Object[]) {
						for (Object value : (Object[]) selected) {
							try {
								if (StringUtils.equals(value.toString(),
										code.getCode())) {
									optionStr += "<option value=\""
											+ code.getCode() + "\" selected>"
											+ code.getCodeName()
											+ "</option>\r\n";
									select = true;
									break;
								}
							} catch (Exception e) {
							}
						}
					} else if (selected instanceof Collection) {
						for (Object value : (Collection) selected) {
							try {
								if (StringUtils.equals(value.toString(),
										code.getCode())) {
									optionStr += "<option value=\""
											+ code.getCode() + "\" selected>"
											+ code.getCodeName()
											+ "</option>\r\n";
									select = true;
									break;
								}
							} catch (Exception e) {
							}
						}
					} else {
						try {
							if (StringUtils.equals(selected.toString(),
									code.getCode())) {
								optionStr += "<option value=\""
										+ code.getCode() + "\" selected>"
										+ code.getCodeName() + "</option>\r\n";
								select = true;
							}
						} catch (Exception e) {
						}
					}
					if (!select) {
						optionStr += "<option value=\"" + code.getCode()
								+ "\">" + code.getCodeName() + "</option>\r\n";
					}
					select = false;
				}
			}

			pageContext.getOut().print(optionStr);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}

		return SKIP_BODY;
	}
}