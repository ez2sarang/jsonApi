package com.fast2.zimin.tvod.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fast2.zimin.tvod.dao.TvodEpgLoadHistoryDao;
import com.fast2.zimin.tvod.entity.TvodEpgLoadHistory;
import com.fast2.zimin.util.DateUtil;

@Repository
public class TvodEpgLoadHistoryDaoImpl implements TvodEpgLoadHistoryDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public void addTvodEpgLoadHistory(TvodEpgLoadHistory tvodEpgLoadHistory) throws Exception {
		sessionFactory.getCurrentSession().save(tvodEpgLoadHistory);
	}

	@Override
	public long getServiceDeployHistoryListCount(Map<String, Object> paramMap) throws Exception {
		
		Criteria crit = sessionFactory.getCurrentSession().createCriteria(TvodEpgLoadHistory.class);
		crit.setProjection(Projections.rowCount());
		
		String scResult = (String)paramMap.get("scResult");
		String scFileName = (String)paramMap.get("scFileName");
		String scFromDate = (String)paramMap.get("scFromDate");
		String scToDate = (String)paramMap.get("scToDate");
		
		if((scResult != null) && (scResult.length() > 0))
		{
			crit.add(Restrictions.eq("result", scResult));
		}
				
		if((scFileName != null) && (scFileName.length() > 0))
		{
			crit.add(Restrictions.like("fileName", "%" + scFileName +"%"));
		}

		if((scFromDate != null) && (scFromDate.length() > 0))
		{
			crit.add(Restrictions.gt("startTime", DateUtil.convertStrToDate(scFromDate, "yyyyMMdd")));
		}
		
		if((scToDate != null) && (scToDate.length() > 0))
		{
			crit.add(Restrictions.gt("startTime", DateUtil.convertStrToDate(scToDate, "yyyyMMdd")));
//			crit.add(Restrictions.lt("startTime", scToDate));
		}

		List records = crit.list();
		Long rowCount = 0L;
		if(records != null)
		{
			rowCount = (Long)records.get(0);
		}
		
		return rowCount;
	}

	@Override
	public List<TvodEpgLoadHistory> getServiceDeployHistoryList(Map<String, Object> paramMap) throws Exception {

		Criteria crit = sessionFactory.getCurrentSession().createCriteria(TvodEpgLoadHistory.class);

		// paging
		Integer start = (Integer)paramMap.get("start");
		Integer size = (Integer)paramMap.get("size");
		
		crit.setFirstResult(start);
		crit.setMaxResults(size);
		
		String scResult = (String)paramMap.get("scResult");
		String scFileName = (String)paramMap.get("scFileName");
		String scFromDate = (String)paramMap.get("scFromDate");
		String scToDate = (String)paramMap.get("scToDate");
		
		if((scResult != null) && (scResult.length() > 0))
		{
			crit.add(Restrictions.eq("result", scResult));
		}
				
		if((scFileName != null) && (scFileName.length() > 0))
		{
			crit.add(Restrictions.like("fileName", "%" + scFileName +"%"));
		}

		if((scFromDate != null) && (scFromDate.length() > 0))
		{
			crit.add(Restrictions.gt("startTime", DateUtil.convertStrToDate(scFromDate, "yyyyMMdd")));
		}
		
		if((scToDate != null) && (scToDate.length() > 0))
		{
			crit.add(Restrictions.gt("startTime", DateUtil.convertStrToDate(scToDate, "yyyyMMdd")));
		}

		// ordering
		String orderColumn = (String)paramMap.get("orderColumn");
		String orderDir = (String)paramMap.get("orderDir");

		if((orderColumn != null) && (orderDir != null))
		{
			if(orderDir.equals("asc"))
			{
				crit.addOrder(Order.asc(orderColumn));
			}
			else if(orderDir.equals("desc"))
			{
				crit.addOrder(Order.desc(orderColumn));
			}
		}

		return crit.list();
	}

}
