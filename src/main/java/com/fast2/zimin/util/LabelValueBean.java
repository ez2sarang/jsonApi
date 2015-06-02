package com.fast2.zimin.util;

import java.io.Serializable;

import org.apache.commons.lang.StringEscapeUtils;

@SuppressWarnings("serial")
public class LabelValueBean implements Serializable {
	private String Label;
	private String Value;

	public String getLabel() {
		return Label;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}

	// 이하 String Escape 처리
	public String getEscapeLabelForHtml() {
		return StringEscapeUtils.escapeHtml(this.Label);
	}

	public String getEscapeLabelForJS() {
		return StringEscapeUtils.escapeJavaScript(this.Label);
	}

	public String getEscapeValueForHtml() {
		return StringEscapeUtils.escapeHtml(this.Value);
	}

	public String getEscapeValueForJS() {
		return StringEscapeUtils.escapeJavaScript(this.Value);
	}
}
