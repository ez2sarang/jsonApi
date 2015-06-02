package com.fast2.zimin.system.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fast2.zimin.system.dao.CommonCodeDao;
import com.fast2.zimin.system.entity.CommonCode;

@Repository("commonCodeDao")
public class CommonCodeDaoImpl implements CommonCodeDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public CommonCode getCode(String groupCode, String code) throws Exception {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				CommonCode.class);
		crit.add(Restrictions.eq("groupCode", groupCode));
		crit.add(Restrictions.eq("code", code));

		return (CommonCode) crit.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommonCode> getCodeList(String groupCode, String useYn,
			String orderColumn) throws Exception {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				CommonCode.class);
		crit.add(Restrictions.eq("groupCode", groupCode));

		if (StringUtils.isNotBlank(useYn)) {
			crit.add(Restrictions.eq("useYn", useYn));
		}

		if (StringUtils.isNotBlank(orderColumn)) {
			crit.addOrder(Order.asc(orderColumn));
		} else {
			crit.addOrder(Order.asc("displayOrder")).addOrder(
					Order.asc("codeName"));
		}

		return (List<CommonCode>) crit.list();
	}

	@Override
	public void addCommonCode(CommonCode commonCode) throws Exception {
		sessionFactory.getCurrentSession().save(commonCode);
	}

	@Override
	public void editCommonCode(CommonCode commonCode) throws Exception {
		sessionFactory.getCurrentSession().update(commonCode);
	}

	@Override
	public void deleteCommonCode(CommonCode commonCode) throws Exception {
		sessionFactory.getCurrentSession().delete(commonCode);
	}

	@Override
	public int deleteCommonCodesByGroupCode(String groupCode) throws Exception {
		String hql = "delete from CommonCode where groupCode = :groupCode";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("groupCode", groupCode);
		return query.executeUpdate();
	}

	@Override
	public int getMaxDisplayOrder(String groupCode) throws Exception {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				CommonCode.class);
		crit.setProjection(Projections.max("displayOrder"));
		crit.add(Restrictions.eq("groupCode", groupCode));
		Integer maxValue = (Integer) crit.uniqueResult();
		if (maxValue == null)
			return 0;
		else
			return maxValue.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CommonCode> getCodeListByClassification(String groupCode,
			String classification, String useYn, String orderColumn)
			throws Exception {
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(
				CommonCode.class);
		crit.add(Restrictions.eq("groupCode", groupCode));

		if (StringUtils.isBlank(classification)) {
			crit.add(Restrictions.or(Restrictions.isNull("classification"),
					Restrictions.eq("classification", "")));
		} else {
			crit.add(Restrictions.eq("classification", classification));
		}

		if (StringUtils.isNotBlank(useYn)) {
			crit.add(Restrictions.eq("useYn", useYn));
		}

		if (StringUtils.isNotBlank(orderColumn)) {
			crit.addOrder(Order.asc(orderColumn));
		} else {
			crit.addOrder(Order.asc("displayOrder")).addOrder(
					Order.asc("codeName"));
		}

		return (List<CommonCode>) crit.list();
	}

}
