package com.fast2.zimin.system.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fast2.zimin.system.entity.CommonCode;
import com.fast2.zimin.util.Constants;
import com.fast2.zimin.util.TheLogger;

@Component
public class CommonCodeManager {
	private static final int CACHING_RELOAD_TERM_MINUTE = 60;
	private static HashMap<String, List<CommonCode>> groupMap = null;
	private static HashMap<String, Calendar> timeMap = null;

	@Autowired
	private CommonCodeService commonCodeService;

	@PostConstruct
	private void initialize() {
		TheLogger.info("■■■■■ CommonCode initializing..■■■■■");
		System.out.println("■■■■■ CommonCode initializing..■■■■■");

		initAll();
	}

	public String getCodeName(String groupCode, String code) {
		CommonCode commonCode = getCode(groupCode, code);
		if (commonCode == null)
			return null;

		return commonCode.getCodeName();
	}

	public CommonCode getCode(String groupCode, String code) {
		try {
			// 그룹코드 목록은 캐슁하지 않음
			if (StringUtils.equals(groupCode,
					Constants.COMMONCODE.ROOT_GROUP_CODE)) {
				return commonCodeService.getCode(groupCode, code);
			}

			boolean cacheReturn = (groupMap.containsKey(groupCode) && validCachingTime(groupCode));

			List<CommonCode> list = getCodeList(groupCode);

			if (list == null) {
				return null;
			}

			for (CommonCode commonCode : list) {
				if (StringUtils.equals(commonCode.getCode(), code)) {
					return commonCode;
				}
			}

			// 캐시에서 처리시 해당 코드가 없으면 DB에서 로딩후 리턴
			if (cacheReturn) {
				list = reloadCodeList(groupCode);
				for (CommonCode commonCode : list) {
					if (StringUtils.equals(commonCode.getCode(), code)) {
						return commonCode;
					}
				}
			}
		} catch (Exception e) {
			TheLogger.error(e,
					"CommonCode System Error(groupCode=%s, code=%s): %s",
					groupCode, code, e.getMessage());
		}

		return null;
	}

	/**
	 * 메모리에서 groupCode에 해당하는 코드들를 리턴함
	 * 
	 * @param groupCode
	 * @return
	 */
	public List<CommonCode> getCodeList(String groupCode) {
		if (groupMap.containsKey(groupCode) && validCachingTime(groupCode)) {
			return groupMap.get(groupCode);
		} else if (StringUtils.equals(groupCode,
				Constants.COMMONCODE.ROOT_GROUP_CODE)) {
			// 그룹코드 목록은 캐쉬에서 제외
			return null;
		} else { // 적재 후 리턴
			return reloadCodeList(groupCode);
		}
	}

	private List<CommonCode> reloadCodeList(String groupCode) {
		List<CommonCode> list = null;
		try {
			list = commonCodeService.getCodeList(groupCode, "Y", null);
		} catch (Exception e) {
			TheLogger.error(e, "CommonCode System Error(groupCode=%s): %s",
					groupCode, e.getMessage());
		}

		if (list == null || list.size() == 0)
			return null;

		groupMap.put(groupCode, list);
		timeMap.put(groupCode, Calendar.getInstance());

		TheLogger.info("■■■■■ CommonCode Group(%s) reloaded..■■■■■", groupCode);

		return list;
	}

	private boolean validCachingTime(String groupCode) {
		Calendar lastLoadingTime = timeMap.get(groupCode);
		if (lastLoadingTime == null)
			return false;

		Calendar now = Calendar.getInstance();
		now.add(Calendar.MINUTE, -CACHING_RELOAD_TERM_MINUTE);
		if (now.before(lastLoadingTime)) {
			return true;
		}

		return false;
	}

	/**
	 * 모든 데이터를 초기화 한다(제거한다)
	 */
	public void initAll() {
		if (groupMap != null) {
			groupMap.clear();
		}

		if (timeMap != null) {
			timeMap.clear();
		}

		groupMap = new HashMap<String, List<CommonCode>>();
		timeMap = new HashMap<String, Calendar>();
		TheLogger.info("■■■■■ CommonCode initialized.. ■■■■■");
		System.out.println("■■■■■ CommonCode initialized.. ■■■■■");
	}

	/**
	 * 해당 groupCode의 그룹을 초기화 한다.
	 * 
	 * @param groupCode
	 */
	public void initGroup(String groupCode) {
		if (groupMap != null) {
			groupMap.remove(groupCode);
		}

		if (timeMap != null) {
			timeMap.remove(groupCode);
		}

		TheLogger.info("■■■■■ CommonCode Group(%s) initialized..■■■■■",
				groupCode);
	}

	/**
	 * 해당 코드들중 구분자에 해당하는 코드만 리턴(메모리사용X)
	 * 
	 * @param groupCode
	 * @param classification
	 * @return
	 */
	public List<CommonCode> getCodeListByClassification(String groupCode,
			String classification) {
		try {
			return commonCodeService.getCodeListByClassification(groupCode,
					classification, "Y", null);
		} catch (Exception e) {
			TheLogger
					.error(e,
							"CommonCode System Error(groupCode=%s, classification=%s): %s",
							groupCode, classification, e.getMessage());
			return null;
		}
	}

	/**
	 * 해당 코드들중 구분자에 해당하는 코드(key)만 리턴(메모리사용X)
	 * 
	 * @param groupCode
	 * @param classification
	 * @return
	 */
	public String[] getCodesByClassification(String groupCode,
			String classification) {
		List<CommonCode> codeList = getCodeListByClassification(groupCode,
				classification);

		String[] codes = null;
		if (codeList == null) {
			codes = new String[0];
		} else {
			codes = new String[codeList.size()];
		}

		int i = 0;
		for (CommonCode commonCode : codeList) {
			codes[i++] = commonCode.getCode();
		}

		return codes;
	}
}