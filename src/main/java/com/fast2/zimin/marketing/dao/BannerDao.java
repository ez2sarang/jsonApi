package com.fast2.zimin.marketing.dao;

import java.util.List;

import com.fast2.zimin.marketing.entity.Banner;
import com.fast2.zimin.marketing.entity.BannerImage;

public interface BannerDao {

	public Banner getBanner(Long bannerId) throws Exception;

	public List<Banner> getBannerList(Banner banner, int fetchSize,
			int firstResult, String orderColumnName, String orderDir)
			throws Exception;

	public void addBanner(Banner banner) throws Exception;

	public void editBanner(Banner banner) throws Exception;

	public void deleteBanner(Banner banner) throws Exception;

	public BannerImage getBannerImage(long idx) throws Exception;

	public void addBannerImage(BannerImage bannerImage) throws Exception;

	public void editBannerImage(BannerImage bannerImage) throws Exception;

	public void deleteBannerImage(BannerImage bannerImage) throws Exception;

	public long getBannerCount(Banner banner) throws Exception;
}
