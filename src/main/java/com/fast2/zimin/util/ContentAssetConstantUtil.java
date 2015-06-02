package com.fast2.zimin.util;

import org.springframework.stereotype.Component;

@Component
public class ContentAssetConstantUtil {
	public static final String CLASSIFICATION_STB_UI = "stb_ui";
	public static final String CLASSIFICATION_STB = "stb";
	public static final String CLASSIFICATION_OTT = "ott";

	public static final String FILE_TYPE_SD = "1";
	public static final String FILE_TYPE_HD = "2";
	public static final String FILE_TYPE_3D = "3";
	public static final String FILE_TYPE_HQ = "4";
	public static final String FILE_TYPE_LQ = "5";
/*
	private static HashMap<String, List<ContentAssetConstant>> subsetTypeconstantListMap = null;
	private static HashMap<String, ContentAssetConstant> SubsetTypeFileTypeConstantMap = null;
	private static HashMap<String, List<ContentAssetConstant>> subsetTypeClassificationConstantListMap = null;

	@Autowired
	private ContentAssetService contentAssetService;

	@PostConstruct
	private void initialize() {
		TheLogger.info("■■■■■ ContentAssetConstant initializing..■■■■■");
		System.out.println("■■■■■ ContentAssetConstant initializing..■■■■■");

		try {
			subsetTypeconstantListMap = new HashMap<>();
			SubsetTypeFileTypeConstantMap = new HashMap<>();
			subsetTypeClassificationConstantListMap = new HashMap<>();

			List<String> subsetTypeList = contentAssetService
					.getContentAssetConstantSubsetTypeList();
			for (String subsetType : subsetTypeList) {
				List<ContentAssetConstant> constantList = contentAssetService
						.getContentAssetConstantList(subsetType);
				subsetTypeconstantListMap.put(subsetType, constantList);
				for (ContentAssetConstant constant : constantList) {
					SubsetTypeFileTypeConstantMap.put(subsetType + "_"
							+ constant.getFileType(), constant);
				}
			}
		} catch (Exception e) {
			TheLogger.error(e, "ContentAssetConstantManager System Error: %s",
					e.getMessage());
		}

		TheLogger.info("■■■■■ ContentAssetConstant initialized.. ■■■■■");
		System.out.println("■■■■■ ContentAssetConstant initialized.. ■■■■■");
	}

	public static List<ContentAssetConstant> getContentAssetConstantList(
			String contentSubsetType) {
		return subsetTypeconstantListMap.get(contentSubsetType);
	}

	public static List<ContentAssetConstant> getContentAssetConstantList(
			String contentSubsetType, String classification) {
		List<ContentAssetConstant> constantList = null;
		if (StringUtils.isBlank(classification)) {
			constantList = subsetTypeClassificationConstantListMap
					.get(contentSubsetType + "_null");
		} else {
			constantList = subsetTypeClassificationConstantListMap
					.get(contentSubsetType + "_" + classification);
		}

		if (constantList == null) {
			constantList = loadConstantList(contentSubsetType, classification);
		}

		return constantList;
	}

	public static ContentAssetConstant getContentAssetConstant(
			String contentSubsetType, String fileType) {
		return SubsetTypeFileTypeConstantMap.get(contentSubsetType + "_"
				+ fileType);
	}

	private static List<ContentAssetConstant> loadConstantList(
			String contentSubsetType, String classification) {
		List<ContentAssetConstant> classificationConstantList = new ArrayList<>();

		List<ContentAssetConstant> constantList = subsetTypeconstantListMap
				.get(contentSubsetType);
		for (ContentAssetConstant constant : constantList) {
			if (StringUtils.isBlank(classification)) {
				if (StringUtils.isBlank(constant.getClassification())) {
					classificationConstantList.add(constant);
				}
			} else {
				if (StringUtils.equals(classification,
						constant.getClassification())) {
					classificationConstantList.add(constant);
				}
			}
		}

		return classificationConstantList;
	}*/
}
