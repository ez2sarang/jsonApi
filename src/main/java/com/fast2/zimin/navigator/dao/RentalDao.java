package com.fast2.zimin.navigator.dao;

import com.fast2.zimin.navigator.bean.entity.PlayableContentGroup;
import com.fast2.zimin.navigator.bean.entity.PlayedHistory;
import com.fast2.zimin.navigator.bean.transfer.AvailiableContentListNavi;
import com.fast2.zimin.navigator.bean.transfer.NotifyContentPlayNavi;
import com.fast2.zimin.navigator.bean.transfer.PurchasedListNavi;
import com.fast2.zimin.navigator.bean.transfer.ViewedListNavi;

import java.io.Serializable;

/**
 * Created by ez2sarang on 15. 5. 15..
 */
public interface RentalDao {
    void save(Serializable entity) throws Exception;

    PlayedHistory getPlayedHistory(NotifyContentPlayNavi navi) throws Exception;

    PlayableContentGroup getPlayableContentGroup(PlayableContentGroup entity) throws Exception;

    void setRentalOfferList(PurchasedListNavi navi) throws Exception;

    void setViewedList(ViewedListNavi navi) throws Exception;

    void setAvailiableContentList(AvailiableContentListNavi navi) throws Exception;

    boolean isExistAsset(String subscriberId, Long contentGroupId, String fileType, String fileName) throws Exception;
}
