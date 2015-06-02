package com.fast2.zimin.navigator.dao;

import com.fast2.zimin.navigator.bean.entity.Menu;
import com.fast2.zimin.navigator.bean.entity.Offer;
import com.fast2.zimin.navigator.bean.transfer.CGInfo;
import com.fast2.zimin.navigator.bean.transfer.RelatedOffer;
import com.fast2.zimin.navigator.bean.transfer.SSContentInfoNavi;
import com.fast2.zimin.util.DataTableObject;
import com.fast2.zimin.util.datatable.QueryModel;

import java.util.List;

/**
 * Created by ez2sarang on 15. 3. 25..
 */
public interface PresentationDao {
    Menu getRoootMenuId(String menuGroupId) throws Exception;

    List<Menu> getTreesByParentId(Long parentId) throws Exception;

    Menu getMenuInfo(Long menuId) throws Exception;

    DataTableObject getOfferList(QueryModel queryModel, long menuId) throws Exception;

    Offer getOffer(Long offerId) throws Exception;

    List<RelatedOffer> getRelatedOfferList(String subscriberId, Long offerId) throws Exception;

    List<CGInfo> getCGInfoList(Long offerId) throws Exception;

    SSContentInfoNavi getSSContentInfo(String subscriberId, Long offerId, Long contentGroupId, String fileType) throws Exception;
}
