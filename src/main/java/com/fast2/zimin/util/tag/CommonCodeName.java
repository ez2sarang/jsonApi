package com.fast2.zimin.util.tag;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.fast2.zimin.system.entity.CommonCode;
import com.fast2.zimin.system.service.CommonCodeManager;
import com.fast2.zimin.util.TheLogger;

@SuppressWarnings("serial")
public class CommonCodeName extends RequestContextAwareTag {
	private String groupCode;
	private String code;

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Autowired
	private CommonCodeManager manager;

	@Override
	protected int doStartTagInternal() throws Exception {
		if (manager == null) {
			WebApplicationContext wac = getRequestContext()
					.getWebApplicationContext();
			AutowireCapableBeanFactory acbf = wac
					.getAutowireCapableBeanFactory();
			acbf.autowireBean(this);
		}

		if (StringUtils.isBlank(groupCode) || StringUtils.isBlank(code)) {
			return SKIP_BODY;
		}

		try {
			// 코드가 여러개일 경우(01,02)
			if (StringUtils.contains(code, ',')) {
				String[] codes = StringUtils.split(code, ',');
				String codeNamesStr = "";
				for (int i = 0; i < codes.length; i++) {
					if (i > 0) {
						codeNamesStr += ", ";
					}
					codeNamesStr += StringUtils.defaultString(manager.getCode(
							groupCode, codes[i]).getCodeName());
				}
				pageContext.getOut().print(codeNamesStr);
			} else {
				CommonCode commonCode = manager.getCode(groupCode, code);
				pageContext.getOut().print(
						StringUtils.defaultString(commonCode.getCodeName()));
			}
		} catch (Exception e) {
			TheLogger.error(e.getMessage(), e);
		}

		return SKIP_BODY;
	}
}