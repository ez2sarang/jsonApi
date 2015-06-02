package com.fast2.zimin.system.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity
public class Locale implements Serializable {
	@Id
	private String localeCode;
	private String country;
	private String language;
	private boolean defaultLocale;
	private boolean required;
	private String alternativeLocaleCode;
	private Integer displayOrder;

	@Transient
	private String languageForInput;

	public String getLocaleCode() {
		return localeCode;
	}

	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public boolean isDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(boolean defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public boolean isRequired() {
		return required;
	}

	public void setRequired(boolean required) {
		this.required = required;
	}

	public String getAlternativeLocaleCode() {
		return alternativeLocaleCode;
	}

	public void setAlternativeLocaleCode(String alternativeLocaleCode) {
		this.alternativeLocaleCode = alternativeLocaleCode;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public String getLanguageForInput() {
		return languageForInput;
	}

	public void setLanguageForInput(String languageForInput) {
		this.languageForInput = languageForInput;
	}

	@Override
	public String toString() {
		return "Locale [localeCode=" + localeCode + ", country=" + country
				+ ", language=" + language + ", defaultLocale=" + defaultLocale
				+ ", required=" + required + ", alternativeLocaleCode="
				+ alternativeLocaleCode + ", displayOrder=" + displayOrder
				+ ", languageForInput=" + languageForInput + "]";
	}
}
