package com.fast2.zimin.system.dao;

import java.util.List;

import com.fast2.zimin.system.entity.CommonCode;

public interface CommonCodeDao {
	public CommonCode getCode(String groupCode, String code) throws Exception;

	public List<CommonCode> getCodeList(String groupCode, String useYn,
			String orderColumn) throws Exception;

	public List<CommonCode> getCodeListByClassification(String groupCode,
			String classification, String useYn, String orderColumn)
			throws Exception;

	public void addCommonCode(CommonCode commonCode) throws Exception;

	public void editCommonCode(CommonCode commonCode) throws Exception;

	public void deleteCommonCode(CommonCode commonCode) throws Exception;

	public int deleteCommonCodesByGroupCode(String groupCode) throws Exception;

	public int getMaxDisplayOrder(String groupCode) throws Exception;
}
