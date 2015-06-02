package com.fast2.zimin.util;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;

public class FileNameUtils extends FilenameUtils {
	/**
	 * 대상 디렉토리에 해당 파일이 있으면 파을명을 변경한다(test.txt --> test(1).txt)
	 * 
	 * @param filePath
	 * @return
	 */
	public static String renameIfExist(String filePath) {
		File file = new File(filePath);
		if (!file.exists())
			return filePath;

		String fileExtension = FilenameUtils.getExtension(filePath);
		String fileNamePart = FilenameUtils.removeExtension(filePath);

		return getIndexFileName(fileNamePart, fileExtension, 1);
	}

	private static String getIndexFileName(String fileNamePart,
			String fileExtension, int i) {
		String filePath = null;
		if (StringUtils.isBlank(fileExtension))
			filePath = String.format("%s(%d)", fileNamePart, i);
		else
			filePath = String.format("%s(%d).%s", fileNamePart, i,
					fileExtension);

		File file = new File(filePath);
		if (!file.exists())
			return filePath;

		// 여전히 중복이면
		return getIndexFileName(fileNamePart, fileExtension, i + 1);
	}

	/**
	 * (test.txt --> .txt) 확장자가 없으면 공백리턴
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getExtensionWithDot(String fileName) {
		String fileExt = FilenameUtils.getExtension(fileName);
		if (StringUtils.isBlank(fileExt))
			return "";
		else
			return "." + fileExt;
	}
}
