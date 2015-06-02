package com.fast2.zimin.marketing.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fast2.zimin.marketing.dao.BannerDao;
import com.fast2.zimin.marketing.entity.Banner;
import com.fast2.zimin.marketing.entity.BannerImage;
import com.fast2.zimin.marketing.service.BannerService;

@Service("bannerService")
@Transactional(propagation = Propagation.SUPPORTS)
public class BannerServiceImpl implements BannerService {
	@Autowired
	private BannerDao bannerDao;

	@Override
	public Banner getBanner(Long bannerId) throws Exception {
		return bannerDao.getBanner(bannerId);
	}

	@Override
	public Banner getBannerWithChildren(Long bannerId) throws Exception {
		Banner banner = bannerDao.getBanner(bannerId);
		banner.getBannerImageList().size();
		return banner;
	}

	@Override
	public List<Banner> getBannerList(Banner banner, int fetchSize,
			int firstResult, String orderColumnName, String orderDir)
			throws Exception {
		return bannerDao.getBannerList(banner, fetchSize, firstResult,
				orderColumnName, orderDir);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addBanner(Banner banner) throws Exception {
		bannerDao.addBanner(banner);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editBanner(Banner banner) throws Exception {
		bannerDao.editBanner(banner);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteBanner(Banner banner) throws Exception {
		bannerDao.deleteBanner(banner);
	}

	@Override
	public BannerImage getBannerImage(long idx) throws Exception {
		return bannerDao.getBannerImage(idx);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addBannerImage(BannerImage bannerImage) throws Exception {
		bannerDao.addBannerImage(bannerImage);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editBannerImage(BannerImage bannerImage) throws Exception {
		BannerImage newImage = new BannerImage();
		newImage.setBannerId(bannerImage.getBannerId());
		newImage.setLocaleCode(bannerImage.getLocaleCode());
		newImage.setFilePath(bannerImage.getFilePath());
		newImage.setOriginalFileName(bannerImage.getOriginalFileName());
		bannerDao.deleteBannerImage(bannerImage);
		bannerDao.addBannerImage(newImage);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteBannerImage(BannerImage bannerImage) throws Exception {
		bannerDao.deleteBannerImage(bannerImage);
	}

	@Override
	public long getBannerCount(Banner banner) throws Exception {
		return bannerDao.getBannerCount(banner);
	}
}
