package com.fast2.zimin.marketing.dao.impl;

import java.math.BigInteger;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fast2.zimin.marketing.dao.BannerDao;
import com.fast2.zimin.marketing.entity.Banner;
import com.fast2.zimin.marketing.entity.BannerImage;
import com.fast2.zimin.util.FullTextSearchKeywordUtil;

@Repository("bannerDao")
public class BannerDaoImpl implements BannerDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Banner getBanner(Long bannerId) throws Exception {
		return (Banner) sessionFactory.getCurrentSession().get(Banner.class,
				bannerId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Banner> getBannerList(Banner banner, int fetchSize,
			int firstResult, String orderColumnName, String orderDir)
			throws Exception {
		// 유효기간 전체선택이면 필터 안함
		if (banner.isSearchOptionPeriodPast()
				&& banner.isSearchOptionPeriodPresent()
				&& banner.isSearchOptionPeriodFuture()) {
			banner.setSearchOptionPeriodPast(false);
			banner.setSearchOptionPeriodPresent(false);
			banner.setSearchOptionPeriodFuture(false);
		}

		StringBuffer bf = new StringBuffer();
		bf.append("select *                                                  ");
		bf.append("from Banner                                               ");
		bf.append("where 1                                                   ");
		if (StringUtils.isNotBlank(banner.getBannerName()))
			bf.append("  and match(bannerName) against (:bn in boolean mode) ");
		if (StringUtils.isNotBlank(banner.getBannerType()))
			bf.append("  and bannerType = :bannerType                        ");
		if (StringUtils.isNotBlank(banner.getLinkType()))
			bf.append("  and linkType = :linkType                            ");

		if (banner.isSearchOptionPeriodPast()
				|| banner.isSearchOptionPeriodPresent()
				|| banner.isSearchOptionPeriodFuture())
			bf.append("  and (" + makeLicenceTimeQuery(banner) + ")          ");

		if (StringUtils.isBlank(orderColumnName))
			bf.append("order by bannerId desc                                ");
		else
			bf.append(String
					.format("order by %s %s", orderColumnName, orderDir));

		Query query = sessionFactory.getCurrentSession()
				.createSQLQuery(bf.toString()).addEntity(Banner.class);

		if (StringUtils.isNotBlank(banner.getBannerName()))
			query.setString("bn", FullTextSearchKeywordUtil.parseKeyword(banner
					.getBannerName()));
		if (StringUtils.isNotBlank(banner.getBannerType()))
			query.setString("bannerType", banner.getBannerType());
		if (StringUtils.isNotBlank(banner.getLinkType()))
			query.setString("linkType", banner.getLinkType());

		query.setFirstResult(firstResult);
		query.setMaxResults(fetchSize);

		return (List<Banner>) query.list();
	}

	@Override
	public void addBanner(Banner banner) throws Exception {
		sessionFactory.getCurrentSession().save(banner);
	}

	@Override
	public void editBanner(Banner banner) throws Exception {
		sessionFactory.getCurrentSession().update(banner);
	}

	@Override
	public void deleteBanner(Banner banner) throws Exception {
		sessionFactory.getCurrentSession().delete(banner);
	}

	@Override
	public BannerImage getBannerImage(long idx) throws Exception {
		return (BannerImage) sessionFactory.getCurrentSession().get(
				BannerImage.class, idx);
	}

	@Override
	public void addBannerImage(BannerImage bannerImage) throws Exception {
		sessionFactory.getCurrentSession().save(bannerImage);
	}

	@Override
	public void editBannerImage(BannerImage bannerImage) throws Exception {
		sessionFactory.getCurrentSession().update(bannerImage);
	}

	@Override
	public void deleteBannerImage(BannerImage bannerImage) throws Exception {
		sessionFactory.getCurrentSession().delete(bannerImage);
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public long getBannerCount(Banner banner) throws Exception {
		// 유효기간 전체선택이면 필터 안함
		if (banner.isSearchOptionPeriodPast()
				&& banner.isSearchOptionPeriodPresent()
				&& banner.isSearchOptionPeriodFuture()) {
			banner.setSearchOptionPeriodPast(false);
			banner.setSearchOptionPeriodPresent(false);
			banner.setSearchOptionPeriodFuture(false);
		}

		StringBuffer bf = new StringBuffer();
		bf.append("select count(*)                                           ");
		bf.append("from Banner                                               ");
		bf.append("where 1                                                   ");
		if (StringUtils.isNotBlank(banner.getBannerName()))
			bf.append("  and match(bannerName) against (:bn in boolean mode) ");
		if (StringUtils.isNotBlank(banner.getBannerType()))
			bf.append("  and bannerType = :bannerType                        ");
		if (StringUtils.isNotBlank(banner.getLinkType()))
			bf.append("  and linkType = :linkType                            ");

		if (banner.isSearchOptionPeriodPast()
				|| banner.isSearchOptionPeriodPresent()
				|| banner.isSearchOptionPeriodFuture())
			bf.append("  and (" + makeLicenceTimeQuery(banner) + ")          ");

		Query query = sessionFactory.getCurrentSession().createSQLQuery(
				bf.toString());
		if (StringUtils.isNotBlank(banner.getBannerName()))
			query.setString("bn", FullTextSearchKeywordUtil.parseKeyword(banner
					.getBannerName()));
		if (StringUtils.isNotBlank(banner.getBannerType()))
			query.setString("bannerType", banner.getBannerType());
		if (StringUtils.isNotBlank(banner.getLinkType()))
			query.setString("linkType", banner.getLinkType());

		return ((BigInteger) query.uniqueResult()).longValue();
	}

	private String makeLicenceTimeQuery(Banner banner) {
		int i = 0;
		String str = "";
		if (banner.isSearchOptionPeriodPast()) {
			str += "licenceEndTime < now()";
			i++;
		}

		if (banner.isSearchOptionPeriodPresent()) {
			if (i > 0)
				str += " or ";

			str += "(licenceStartTime < now() and licenceEndTime > now())";
			i++;
		}

		if (banner.isSearchOptionPeriodFuture()) {
			if (i > 0)
				str += " or ";
			str += "licenceStartTime > now()";
		}

		return str;
	}
}
