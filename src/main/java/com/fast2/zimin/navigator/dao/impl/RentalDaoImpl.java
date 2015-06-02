package com.fast2.zimin.navigator.dao.impl;

import com.fast2.zimin.navigator.bean.entity.PlayableContentGroup;
import com.fast2.zimin.navigator.bean.entity.PlayedHistory;
import com.fast2.zimin.navigator.bean.entity.RentalOffer;
import com.fast2.zimin.navigator.bean.transfer.*;
import com.fast2.zimin.navigator.config.ResultType;
import com.fast2.zimin.util.DateUtil;
import com.fast2.zimin.util.HibernateUtil;
import com.fast2.zimin.util.TheLogger;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ez2sarang on 15. 4. 8..
 */
@Repository("rentalDao")
public class RentalDaoImpl implements com.fast2.zimin.navigator.dao.RentalDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Serializable entity) throws Exception {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public PlayedHistory getPlayedHistory(NotifyContentPlayNavi navi) throws Exception {
        return (PlayedHistory)sessionFactory.getCurrentSession()
                .createCriteria(PlayedHistory.class)
                .add(Restrictions.eq("fileType", navi.getAssetType()))
                .add(Restrictions.eq("subscriberId", navi.getUserId()))
                .add(Restrictions.eq("contentGroupId", navi.getCgId()))
                .add(Restrictions.eq("playType", navi.getControlType()))
                .uniqueResult();
    }

    @Override
    public PlayableContentGroup getPlayableContentGroup(PlayableContentGroup entity) throws Exception {
        return (PlayableContentGroup)sessionFactory.getCurrentSession()
                .createCriteria(PlayableContentGroup.class)
                .add(Restrictions.eq("subscriberId", entity.getSubscriberId()))
                .add(Restrictions.eq("offerId", entity.getOfferId()))
                .add(Restrictions.eq("contentGroupId", entity.getContentGroupId()))
                .uniqueResult();
    }

    @Override
    public void setRentalOfferList(PurchasedListNavi navi) throws Exception {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(RentalOffer.class)
                    .add(Restrictions.eq("subscriberId", navi.getUserId()))
                    ;
            if(!"".equals(StringUtils.defaultString(navi.getFromDate())) && !"".equals(StringUtils.defaultString(navi.getToDate()))) {
                criteria.add(Restrictions.between("createDateTime", DateUtil.convertStrToDate(navi.getFromDate()+"000000", "yyyyMMddHHmmss"), DateUtil.convertStrToDate(navi.getToDate()+"235959", "yyyyMMddHHmmss")));
            }
            navi.setTotalCount((int) HibernateUtil.getRowCount(criteria));
            if(!"".equals(StringUtils.defaultString(navi.getSortItem()))) {
                criteria.addOrder("DESC".equals(navi.getSortDirection())?Order.desc(navi.getSortItem()):Order.asc(navi.getSortItem()));
            }

            criteria.setFirstResult(navi.getListCount() * (navi.getPageNo() - 1));
            criteria.setMaxResults(navi.getListCount());

            List<PurchasedInfo> purchasedInfos = new ArrayList<PurchasedInfo>();
            List<RentalOffer> rentalOffers = criteria.list();
            if(null == rentalOffers || rentalOffers.size()<1) {
                navi.setResultCode(ResultType.NOT_FOUND);
                return;
            }
            for(RentalOffer rentalOffer : rentalOffers) {
                purchasedInfos.add(new PurchasedInfo(rentalOffer.getOfferId(), rentalOffer.getOfferTitle(), rentalOffer.getPrice(), DateUtil.getDateStr(rentalOffer.getCreateDateTime(), "yyyyMMddHHmm"), DateUtil.getDateStr(rentalOffer.getLicenseEndDateTime(), "yyyyMMddHHmm")));
            }
            navi.setPurchasedList(purchasedInfos);
            navi.setResultCode(ResultType.OK);
        } catch (Exception e) {
            TheLogger.error(e.getMessage());
            navi.setResultCode(ResultType.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public void setViewedList(ViewedListNavi navi) throws Exception {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PlayedHistory.class)
                    .add(Restrictions.eq("subscriberId", navi.getUserId()))
                    .add(Restrictions.eq("playType", "PLAY"))
                    ;
            if(!"".equals(StringUtils.defaultString(navi.getFromDate())) && !"".equals(StringUtils.defaultString(navi.getToDate()))) {
                criteria.add(Restrictions.between("currentPlayDateTime", DateUtil.convertStrToDate(navi.getFromDate()+"000000", "yyyyMMddHHmmss"), DateUtil.convertStrToDate(navi.getToDate()+"235959", "yyyyMMddHHmmss")));
            }
            navi.setTotalCount((int) HibernateUtil.getRowCount(criteria));
            if(!"".equals(StringUtils.defaultString(navi.getSortItem()))) {
                criteria.addOrder("DESC".equals(navi.getSortDirection())?Order.desc(navi.getSortItem()):Order.asc(navi.getSortItem()));
            }

            criteria.setFirstResult(navi.getListCount() * (navi.getPageNo() - 1));
            criteria.setMaxResults(navi.getListCount());

            List<ViewedInfo> viewedInfos = new ArrayList<ViewedInfo>();
            List<PlayedHistory> playedHistories = criteria.list();
            if(null == playedHistories || playedHistories.size()<1) {
                navi.setResultCode(ResultType.NOT_FOUND);
                return;
            }
            for(PlayedHistory playedHistory : playedHistories) {
                viewedInfos.add(new ViewedInfo(playedHistory.getOfferId(), playedHistory.getOfferTitle(), DateUtil.getDateStr(playedHistory.getExpireDate(), "yyyyMMddHHmm"), DateUtil.getDateStr(playedHistory.getPurchasedDate(), "yyyyMMddHHmm"), DateUtil.getDateStr(playedHistory.getCurrentPlayDateTime(), "yyyyMMddHHmm")));
            }
            navi.setViewedList(viewedInfos);
            navi.setResultCode(ResultType.OK);
        } catch (Exception e) {
            TheLogger.error(e.getMessage());
            navi.setResultCode(ResultType.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public void setAvailiableContentList(AvailiableContentListNavi navi) throws Exception {
        try {
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(PlayableContentGroup.class)
                    .add(Restrictions.eq("subscriberId", navi.getUserId()))
                    ;
            navi.setTotalCount((int) HibernateUtil.getRowCount(criteria));
            if(!"".equals(StringUtils.defaultString(navi.getSortItem()))) {
                criteria.addOrder("DESC".equals(navi.getSortDirection())?Order.desc(navi.getSortItem()):Order.asc(navi.getSortItem()));
            }

            criteria.setFirstResult(navi.getListCount() * (navi.getPageNo() - 1));
            criteria.setMaxResults(navi.getListCount());

            List<ContentInfo> contentInfos = new ArrayList<ContentInfo>();
            List<PlayableContentGroup> playableContentGroups = criteria.list();
            if(null == playableContentGroups || playableContentGroups.size()<1) {
                navi.setResultCode(ResultType.NOT_FOUND);
                return;
            }
            for(PlayableContentGroup playableContentGroup : playableContentGroups) {
                contentInfos.add(new ContentInfo(playableContentGroup.getOfferId(), playableContentGroup.getOfferTitle(), playableContentGroup.getPrice(), DateUtil.getDateStr(playableContentGroup.getCreateDateTime(), "yyyyMMddHHmm"), DateUtil.getDateStr(playableContentGroup.getLicenseEndDateTime(), "yyyyMMddHHmm")));
            }
            navi.setContentList(contentInfos);
            navi.setResultCode(ResultType.OK);
        } catch (Exception e) {
            TheLogger.error(e.getMessage());
            navi.setResultCode(ResultType.UNKNOWN_ERROR, e.getMessage());
        }
    }

    @Override
    public boolean isExistAsset(String subscriberId, Long contentGroupId, String fileType, String fileName) throws Exception {
        try {
            SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                    "select count(*) \n" +
                            "  from PlayableContentGroup a\n" +
                            " inner join ContentAsset b \n" +
                            "         on a.contentGroupId = b.contentGroupId \n" +
                            "        and a.fileType = b.fileType\n" +
                            " where a.subscriberId = :subscriberId\n" +
                            "   and a.contentGroupId = :contentGroupId\n" +
                            "   and a.fileType = :fileType\n" +
                            "   and now() < a.licenseEndDateTime\n" +
                            "   and b.contentSubsetType = '11'\n" +
                            "   and b.fileName = :fileName")
                    ;
            query.setString("subscriberId", subscriberId);
            query.setLong("contentGroupId", contentGroupId);
            query.setString("fileType", fileType);
            query.setString("fileName", fileName);
            return ((BigInteger)query.uniqueResult()).intValue() > 0;
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return false;
    }
}
