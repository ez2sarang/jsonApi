package com.fast2.zimin.system.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class CommonCode implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idx;
	private String groupCode;
	private String code;
	private String fullCode;
	private String codeName;
	private String description;
	private String useYn;
	private Integer displayOrder;
	private String classification;
	private String extendValue1;
	private String extendValue2;
	public Integer getIdx() {
		return idx;
	}
	public void setIdx(Integer idx) {
		this.idx = idx;
	}
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
	public String getFullCode() {
		return fullCode;
	}
	public void setFullCode(String fullCode) {
		this.fullCode = fullCode;
	}
	public String getCodeName() {
		return codeName;
	}
	public void setCodeName(String codeName) {
		this.codeName = codeName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Integer getDisplayOrder() {
		return displayOrder;
	}
	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
	public String getClassification() {
		return classification;
	}
	public void setClassification(String classification) {
		this.classification = classification;
	}
	public String getExtendValue1() {
		return extendValue1;
	}
	public void setExtendValue1(String extendValue1) {
		this.extendValue1 = extendValue1;
	}
	public String getExtendValue2() {
		return extendValue2;
	}
	public void setExtendValue2(String extendValue2) {
		this.extendValue2 = extendValue2;
	}
	@Override
	public String toString() {
		return "CommonCode [idx=" + idx + ", groupCode=" + groupCode
				+ ", code=" + code + ", fullCode=" + fullCode + ", codeName="
				+ codeName + ", description=" + description + ", useYn="
				+ useYn + ", displayOrder=" + displayOrder
				+ ", classification=" + classification + ", extendValue1="
				+ extendValue1 + ", extendValue2=" + extendValue2 + "]";
	}
}
