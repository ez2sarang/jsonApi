package com.fast2.zimin.system.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fast2.zimin.system.dao.CommonCodeDao;
import com.fast2.zimin.system.entity.CommonCode;
import com.fast2.zimin.system.service.CommonCodeManager;
import com.fast2.zimin.system.service.CommonCodeService;
import com.fast2.zimin.util.Constants;

@Service("commonCodeService")
@Transactional(propagation = Propagation.SUPPORTS)
public class CommonCodeServiceImpl implements CommonCodeService {
	@Autowired
	private CommonCodeDao commonCodeDao;

	@Autowired
	private CommonCodeManager commonCodeManager;

	@Override
	public CommonCode getCode(String groupCode, String code) throws Exception {
		return commonCodeDao.getCode(groupCode, code);
	}

	@Override
	public List<CommonCode> getCodeList(String groupCode, String useYn,
			String orderColumn) throws Exception {
		return commonCodeDao.getCodeList(groupCode, useYn, orderColumn);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void addCommonCode(CommonCode commonCode) throws Exception {
		int maxDisplayOrder = commonCodeDao.getMaxDisplayOrder(commonCode
				.getGroupCode());
		commonCode.setDisplayOrder(maxDisplayOrder + 1);
		commonCodeDao.addCommonCode(commonCode);

		// 그룹코드가 아닐경우 캐쉬 리셋
		if (!StringUtils.equals(commonCode.getGroupCode(),
				Constants.COMMONCODE.ROOT_GROUP_CODE)) {
			commonCodeManager.initGroup(commonCode.getGroupCode());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void editCommonCode(CommonCode commonCode) throws Exception {
		commonCodeDao.editCommonCode(commonCode);

		// 그룹코드가 아닐경우 캐쉬 리셋
		if (!StringUtils.equals(commonCode.getGroupCode(),
				Constants.COMMONCODE.ROOT_GROUP_CODE)) {
			commonCodeManager.initGroup(commonCode.getGroupCode());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteCommonCode(String groupCode, String code)
			throws Exception {
		CommonCode commonCode = commonCodeDao.getCode(groupCode, code);

		if (commonCode != null) {
			commonCodeDao.deleteCommonCode(commonCode);
		}

		// 그룹코드가 아닐경우 캐쉬 삭제
		if (!StringUtils.equals(commonCode.getGroupCode(),
				Constants.COMMONCODE.ROOT_GROUP_CODE)) {
			commonCodeManager.initGroup(commonCode.getGroupCode());
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteCommonCodesByGroupCode(String groupCode) throws Exception {
		// 전체코드 삭제 방지
		if (!StringUtils
				.equals(groupCode, Constants.COMMONCODE.ROOT_GROUP_CODE)) {
			// 자식코드들 삭제
			commonCodeDao.deleteCommonCodesByGroupCode(groupCode);

			// 그룹코드 삭제
			CommonCode commonCode = commonCodeDao.getCode(
					Constants.COMMONCODE.ROOT_GROUP_CODE, groupCode);
			if (commonCode != null) {
				commonCodeDao.deleteCommonCode(commonCode);
			}

			// 캐쉬 삭제
			commonCodeManager.initGroup(groupCode);
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	public void reorderCommonCode(String groupCode, String[] codes)
			throws Exception {
		for (int i = 0; i < codes.length; i++) {
			CommonCode commonCode = commonCodeDao.getCode(groupCode, codes[i]);
			commonCode.setDisplayOrder(i + 1);
			commonCodeDao.addCommonCode(commonCode);
		}

		// 캐쉬 리셋
		commonCodeManager.initGroup(groupCode);
	}

	@Override
	public List<CommonCode> getCodeListByClassification(String groupCode,
			String classification, String useYn, String orderColumn)
			throws Exception {
		return commonCodeDao.getCodeListByClassification(groupCode,
				classification, useYn, orderColumn);
	}
}
