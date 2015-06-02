package com.fast2.zimin.marketing.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fast2.zimin.util.JsonDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("serial")
@Entity
public class Banner implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bannerId;
	private String bannerName;
	private String bannerType;
	private String linkType;
	private String linkInfo;
	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date licenceStartTime;
	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	private Date licenceEndTime;
	private String description;
	private String createId;
	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	private String updateId;
	@Column(columnDefinition = "DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateTime;

	@Transient
	private boolean searchOptionPeriodPast; // 검색옵션
	@Transient
	private boolean searchOptionPeriodPresent; // 검색옵션
	@Transient
	private boolean searchOptionPeriodFuture; // 검색옵션

	@OneToMany(cascade = CascadeType.ALL)
	// @Fetch(FetchMode.SUBSELECT)
	// @LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "bannerId")
	@JsonIgnore
	private List<BannerImage> bannerImageList;

	public Long getBannerId() {
		return bannerId;
	}

	public void setBannerId(Long bannerId) {
		this.bannerId = bannerId;
	}

	public String getBannerName() {
		return bannerName;
	}

	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}

	public String getBannerType() {
		return bannerType;
	}

	public void setBannerType(String bannerType) {
		this.bannerType = bannerType;
	}

	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getLinkInfo() {
		return linkInfo;
	}

	public void setLinkInfo(String linkInfo) {
		this.linkInfo = linkInfo;
	}

	public Date getLicenceStartTime() {
		return licenceStartTime;
	}

	public void setLicenceStartTime(Date licenceStartTime) {
		this.licenceStartTime = licenceStartTime;
	}

	public Date getLicenceEndTime() {
		return licenceEndTime;
	}

	public void setLicenceEndTime(Date licenceEndTime) {
		this.licenceEndTime = licenceEndTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public boolean isSearchOptionPeriodPast() {
		return searchOptionPeriodPast;
	}

	public void setSearchOptionPeriodPast(boolean searchOptionPeriodPast) {
		this.searchOptionPeriodPast = searchOptionPeriodPast;
	}

	public boolean isSearchOptionPeriodPresent() {
		return searchOptionPeriodPresent;
	}

	public void setSearchOptionPeriodPresent(boolean searchOptionPeriodPresent) {
		this.searchOptionPeriodPresent = searchOptionPeriodPresent;
	}

	public boolean isSearchOptionPeriodFuture() {
		return searchOptionPeriodFuture;
	}

	public void setSearchOptionPeriodFuture(boolean searchOptionPeriodFuture) {
		this.searchOptionPeriodFuture = searchOptionPeriodFuture;
	}

	public List<BannerImage> getBannerImageList() {
		return bannerImageList;
	}

	public void setBannerImageList(List<BannerImage> bannerImageList) {
		this.bannerImageList = bannerImageList;
	}

	@Override
	public String toString() {
		return "Banner [bannerId=" + bannerId + ", bannerName=" + bannerName
				+ ", bannerType=" + bannerType + ", linkType=" + linkType
				+ ", linkInfo=" + linkInfo + ", licenceStartTime="
				+ licenceStartTime + ", licenceEndTime=" + licenceEndTime
				+ ", description=" + description + ", createId=" + createId
				+ ", createTime=" + createTime + ", updateId=" + updateId
				+ ", updateTime=" + updateTime + ", searchOptionPeriodPast="
				+ searchOptionPeriodPast + ", searchOptionPeriodPresent="
				+ searchOptionPeriodPresent + ", searchOptionPeriodFuture="
				+ searchOptionPeriodFuture + ", bannerImageList="
				+ bannerImageList + "]";
	}
}
