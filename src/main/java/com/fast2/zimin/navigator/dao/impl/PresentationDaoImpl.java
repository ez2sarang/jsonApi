package com.fast2.zimin.navigator.dao.impl;

import com.fast2.zimin.navigator.bean.entity.Menu;
import com.fast2.zimin.navigator.bean.entity.MenuOfferMap;
import com.fast2.zimin.navigator.bean.entity.Offer;
import com.fast2.zimin.navigator.bean.transfer.CGInfo;
import com.fast2.zimin.navigator.bean.transfer.RelatedOffer;
import com.fast2.zimin.navigator.bean.transfer.SSContentInfoNavi;
import com.fast2.zimin.util.DataTableObject;
import com.fast2.zimin.util.HibernateUtil;
import com.fast2.zimin.util.TheLogger;
import com.fast2.zimin.util.datatable.QueryModel;
import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.hibernate.type.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by ez2sarang on 15. 3. 25..
 */
@Repository("presentationDao")
public class PresentationDaoImpl implements com.fast2.zimin.navigator.dao.PresentationDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Menu getRoootMenuId(String menuGroupId) throws Exception {
        return (Menu)sessionFactory.getCurrentSession().createSQLQuery(
                "select * \n" +
                "  from Menu \n" +
                " where id \n" +
                "    in (select menuId from MenuGroupMap where groupId = :menuGroupId) \n" +
                "   and parentId is null\n" +
                "   and visible = 1\n" +
                "   and now() between validTermStart and validTermEnd\n" +
                " order by validTermEnd desc\n" +
                " limit 1")
                .addEntity(Menu.class)
                .setLong("menuGroupId", Long.valueOf(menuGroupId))
                .uniqueResult()
                ;
    }

    @Override
    public List<Menu> getTreesByParentId(Long parentId) throws Exception {
        Object treeList = null;
        try {
            treeList = sessionFactory.getCurrentSession()
                    .createCriteria(Menu.class)
                    .add(parentId == null || parentId == 0 ? Restrictions.isNull("parentId") : Restrictions.eq("parentId", parentId))
                    .add(Restrictions.eq("visible", true))
                    .add(Restrictions.lt("validTermStart", new Date()))
                    .add(Restrictions.gt("validTermEnd", new Date()))
                    .addOrder(Order.asc("displayOrder"))
                    .addOrder(Order.asc("id"))
                    .list();
        } catch (HibernateException e) {
            TheLogger.error(e.getMessage());
        }
        return (List<Menu>) treeList;
    }

    @Override
    public Menu getMenuInfo(Long menuId) throws Exception {
        try {
            return (Menu)sessionFactory.getCurrentSession()
                    .createCriteria(Menu.class)
                    .add(Restrictions.eq("id", menuId))
                    .uniqueResult();
        } catch (HibernateException e) {
            TheLogger.error(e.getMessage());
        }
        return null;
    }


    @Override
    public DataTableObject getOfferList(QueryModel queryModel, long menuId/*OfferListNavi navi*/) throws Exception {
        DataTableObject bean = new DataTableObject();
        try {
            DetachedCriteria mCriteria = DetachedCriteria.forClass(MenuOfferMap.class)
                    .add(Restrictions.eq("menuId", menuId))
                    .setProjection(Projections.property("offerId"));

            //TODO to append for search condition of terms
            Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(Offer.class, "offer")
                    //전시 기간
                    .add(Restrictions.lt("offer.startDateTime", new Date()))
                    .add(Restrictions.gt("offer.endDateTime", new Date()))
                    .add(Subqueries.propertyIn("offer.offerId", mCriteria));

            //TODO MenuOfferMap을 봐라보게끔 변경해야됨 - 쿼리성능개선
            /*Criteria criteria = sessionFactory.getCurrentSession()
                    .createCriteria(MenuOfferMap.class)
                    .add(Restrictions.eq("menuId", menuId))
                    ;*/

            bean.setRecordsTotal(HibernateUtil.getRowCount(criteria));
            bean.setDraw(queryModel.getDraw());
            bean.setRecordsFiltered(HibernateUtil.getRowCount(criteria));


            if (null != queryModel.getOrder()) {
                for(com.fast2.zimin.util.datatable.Order order : queryModel.getOrder()) {
                    if(order.getColumn()>-1) {
                        if("desc".equalsIgnoreCase(order.getDir())) {
                            criteria.addOrder(Order.desc(queryModel.getColumns().get(order.getColumn()).getName()));
                        } else {
                            criteria.addOrder(Order.asc(queryModel.getColumns().get(order.getColumn()).getName()));
                        }
                    }
                }
            }
            criteria.setFirstResult(queryModel.getStart());
            criteria.setMaxResults(queryModel.getLength());


            /*navi.setTotalCount((int) HibernateUtil.getRowCount(criteria));
            if(!"".equals(StringUtils.defaultString(navi.getSortItem()))) {
                criteria.addOrder("DESC".equals(navi.getSortDirection())?Order.desc(navi.getSortItem()):Order.asc(navi.getSortItem()));
            }

            criteria.setFirstResult(navi.getListCount() * (navi.getPageNo() - 1));
            criteria.setMaxResults(navi.getListCount());*/


            //TODO List<NvodRequestMedia>
            List<Offer> result = criteria.list();
            //bean.setRecordsFiltered(result == null ? 0 : result.size());
            bean.setData(result);
        } catch (Exception e) {
            TheLogger.error(e.getMessage());
            e.printStackTrace();
        }
        return bean;
    }

    @Override
    public Offer getOffer(Long offerId) throws Exception {
        return (Offer)sessionFactory.getCurrentSession().get(Offer.class, offerId);
    }

    @Override
    public List<RelatedOffer> getRelatedOfferList(String subscriberId, Long offerId) throws Exception {
        try {
            SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                    "select c.offerId \n" +
                    "     , c.title as offerTitle \n" +
                    "     , c.price as offerPrice \n" +
                    "     , concat(ifNull((select fileType from ContentAsset where contentGroupId = b.contentGroupId and contentSubsetType = 11 /*movie*/ and fileType = 1/*SD*/),'') \n" +
                    "             ,ifNull((select fileType from ContentAsset where contentGroupId = b.contentGroupId and contentSubsetType = 11 /*movie*/ and fileType = 2/*HD*/),'') \n" +
                    "             ) as assetType \n" +
                    "     , case when now() between c.licenseStartDateTime and c.licenseEndDateTime and (select count(*) from PlayableContentGroup where subscriberId = :subscriberId and offerId = c.offerId and contentGroupId = a.contentGroupId and now() < licenseEndDateTime ) > 0 then 1 else 0 end as isWatchValid \n" +
                    "     , c.rentalPeriod as rentalPeriod \n" +
                    "  from (select offerId, contentGroupId \n" +
                    "          from OfferContentGroup \n" +
                    "         where contentGroupId \n" +
                    "            in (select contentGroupId from OfferContentGroup where offerId = :offerId) \n" +
                    "       ) a \n" +
                    " inner join ContentGroup b on a.contentGroupId = b.contentGroupId \n" +
                    " inner join Offer c on a.offerId = c.offerId")
                    .addScalar("offerId", new LongType())
                    .addScalar("offerTitle", new StringType())
                    .addScalar("offerPrice", new IntegerType())
                    .addScalar("assetType", new StringType())
                    .addScalar("isWatchValid", new BooleanType())
                    .addScalar("rentalPeriod", new StringType())
            ;
            query.setLong("offerId", offerId);
            query.setString("subscriberId", subscriberId);
            query.setResultTransformer(Transformers.aliasToBean(RelatedOffer.class));
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CGInfo> getCGInfoList(Long offerId) throws Exception {
        try {
            SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                            "select a.contentGroupId as cgId\n" +
                            "     , a.title as cgTitle\n" +
                            "     , a.poster as cgPoster\n" +
                            "     , concat(ifNull((select fileType from ContentAsset where contentGroupId = b.contentGroupId and contentSubsetType = 11 /*movie*/ and fileType = 1/*SD*/),'') \n" +
                            "             ,ifNull((select fileType from ContentAsset where contentGroupId = b.contentGroupId and contentSubsetType = 11 /*movie*/ and fileType = 2/*HD*/),'') \n" +
                            "             ) as fileType \n" +
                            "  from ContentGroup a\n" +
                            " inner join OfferContentGroup b \n" +
                            "         on a.contentGroupId = b.contentGroupId \n" +
                            "        and b.offerId = :offerId")
                    .addScalar("cgId", new LongType())
                    .addScalar("cgTitle", new StringType())
                    .addScalar("cgPoster", new StringType())
                    .addScalar("fileType", new StringType())
                    ;
            query.setLong("offerId", offerId);
            query.setResultTransformer(Transformers.aliasToBean(CGInfo.class));
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SSContentInfoNavi getSSContentInfo(String subscriberId, Long offerId, Long contentGroupId, String fileType) throws Exception {
        try {
            SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(
                    "select a.fileName as ssPath\n" +
                    "     , ifNull((select p.offSet \n" +
                    "                 from PlayedHistory p \n" +
                    "                where p.subscriberId = b.subscriberId\n" +
                    "                  and p.contentGroupId = a.contentGroupId \n" +
                    "                  and p.fileType = a.fileType \n" +
                    "                  and p.playType = 'STOP'), 0) as 'offSet'\n" +
                    "  from ContentAsset a \n" +
                    " inner join PlayableContentGroup b \n" +
                    "         on b.contentGroupId = a.contentGroupId \n" +
                    "        and b.fileType = a.fileType \n" +
                    " where a.contentGroupId = :contentGroupId \n" +
                    "   and a.contentSubsetType = '11'\n" +
                    "   and a.fileType = :fileType \n" +
                    "   and b.offerId = :offerId \n" +
                    "   and b.subscriberId = :subscriberId \n" +
                    "   and now() < b.licenseEndDateTime")
                    .addScalar("ssPath", new StringType())
                    .addScalar("offSet", new DoubleType())
                    ;
            query.setString("subscriberId", subscriberId);
            query.setLong("offerId", offerId);
            query.setLong("contentGroupId", contentGroupId);
            query.setString("fileType", fileType);
            /*query.setLong(0, contentGroupId);
            query.setString(1, fileType);
            query.setLong(2, offerId);
            query.setString(3, subscriberId);*/
            query.setResultTransformer(Transformers.aliasToBean(SSContentInfoNavi.class));
            return (SSContentInfoNavi)query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        return null;
    }
}
