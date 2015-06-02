package com.fast2.zimin.util.tag;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.fast2.zimin.util.LabelValueBean;
import com.fast2.zimin.util.TheLogger;

@SuppressWarnings("serial")
public class LabelValueBeanTag extends RequestContextAwareTag {
	private String serviceBeanName;
	private String key1;
	private String key2;
	private String key3;

	public String getServiceBeanName() {
		return serviceBeanName;
	}

	public void setServiceBeanName(String serviceBeanName) {
		this.serviceBeanName = serviceBeanName;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getKey3() {
		return key3;
	}

	public void setKey3(String key3) {
		this.key3 = key3;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		if (StringUtils.isBlank(serviceBeanName)) {
			return SKIP_BODY;
		}

		try {
			Object object = getRequestContext().getWebApplicationContext()
					.getBean(serviceBeanName);
			Method method = object.getClass().getMethod("getLabelValueBean",
					String.class, String.class, String.class);
			LabelValueBean bean = (LabelValueBean) method.invoke(object, key1,
					key2, key3);
			pageContext.getOut().print(bean.getLabel());
		} catch (Exception e) {
			TheLogger.error(String.format(
					"LabelValueBean tag error(check %s.getLabelValueBean(,,))",
					serviceBeanName), e);
		}

		return SKIP_BODY;
	}

}
