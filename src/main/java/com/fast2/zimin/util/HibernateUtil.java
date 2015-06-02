package com.fast2.zimin.util;

import org.hibernate.Criteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.internal.CriteriaImpl;
import org.hibernate.internal.util.SerializationHelper;
import org.hibernate.loader.criteria.CriteriaJoinWalker;
import org.hibernate.loader.criteria.CriteriaQueryTranslator;
import org.hibernate.persister.entity.OuterJoinLoadable;
import org.hibernate.transform.ResultTransformer;

import java.io.Serializable;

/**
 * Created by ez2sarang on 2014. 11. 10..
 */
public class HibernateUtil {
    public static long getRowCount(Criteria criteria, Projection...rowCountProjection) {
        CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;

        Projection originalProjection = criteriaImpl.getProjection();
        ResultTransformer originalResultTransformer = criteriaImpl.getResultTransformer();

        long rows = 0;
        if(originalProjection == null) {
            if(rowCountProjection!=null&&rowCountProjection.length>0) {
                rows = Long.valueOf(criteria.setProjection(rowCountProjection[0]).uniqueResult().toString());
            } else {
                rows = Long.valueOf(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
            }
        } else {
            if(originalProjection.isGrouped()) {
                if(rowCountProjection!=null&&rowCountProjection.length>0) {
                    rows = Long.valueOf(criteria.setProjection(rowCountProjection[0]).uniqueResult().toString());
                } else {
                    Projection newProjection = criteriaImpl.getProjection();
                    rows = criteria.setProjection(newProjection).list().size();
                }
            } else {
                rows = Long.valueOf(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
            }
        }
        criteria.setProjection(originalProjection);
        criteria.setResultTransformer(originalResultTransformer);
        return rows;
    }

    public static String getSql(Criteria criteria) {
        CriteriaImpl criteriaImpl = (CriteriaImpl) criteria;
        SessionImplementor session = criteriaImpl.getSession();
        SessionFactoryImplementor factory = session.getFactory();
        CriteriaQueryTranslator translator = new CriteriaQueryTranslator(
                factory
                , criteriaImpl
                , criteriaImpl.getEntityOrClassName()
                , CriteriaQueryTranslator.ROOT_SQL_ALIAS
        );
        String[] implementors = factory.getImplementors(criteriaImpl.getEntityOrClassName());

        CriteriaJoinWalker walker = new CriteriaJoinWalker(
                (OuterJoinLoadable) factory.getEntityPersister(implementors[0])
                , translator
                , factory
                , criteriaImpl
                , criteriaImpl.getEntityOrClassName()
                , session.getLoadQueryInfluencers()
        );
        return walker.getSQLString();
    }

    public static <T> T clone(T original) {
        T clonedObject = (T) SerializationHelper.clone((Serializable)original);
        return clonedObject;
    }
}
