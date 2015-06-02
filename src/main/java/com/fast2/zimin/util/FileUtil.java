package com.fast2.zimin.util;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileExistsException;
import org.apache.commons.io.FileUtils;

public class FileUtil extends FileUtils {
	public static void copyDirectory(File srcDir, File destDir)
			throws IOException {
		String srcPath = srcDir.getPath();
		String destPath = destDir.getPath();
		if (StringUtil.isBlank(srcPath) || StringUtil.equals(srcPath, "/")
				|| StringUtil.isBlank(destPath)
				|| StringUtil.equals(destPath, "/")) {
			String errorMsg = String
					.format("copyDirectory Fail, Dir Protection Error(srcDir=%s, destDir=%s)",
							srcPath, destPath);
			TheLogger.error(errorMsg);
			throw new IOException(errorMsg);
		}

		FileUtils.copyDirectory(srcDir, destDir);
	}

	/**
	 * 주의:대상 디렉토리가 있으면 지우고 이동 시킨다.
	 * @param srcDir
	 * @param destDir
	 * @throws IOException
	 */
	public static void moveDirectory(File srcDir, File destDir)
			throws IOException {
		String srcPath = srcDir.getPath();
		String destPath = destDir.getPath();
		if (StringUtil.isBlank(srcPath) || StringUtil.equals(srcPath, "/")
				|| StringUtil.isBlank(destPath)
				|| StringUtil.equals(destPath, "/")) {
			String errorMsg = String
					.format("moveDirectory Fail, Dir Protection Error(srcDir=%s, destDir=%s)",
							srcPath, destPath);
			TheLogger.error(errorMsg);
			throw new IOException(errorMsg);
		}

		try {
			FileUtils.moveDirectory(srcDir, destDir);
		} catch (FileExistsException e) {
			TheLogger.info(
					"DestDir(%s) exists so DestDir delete and moveDirectory",
					destPath);

			try {
				FileUtils.deleteDirectory(destDir);
				FileUtils.moveDirectory(srcDir, destDir);
			} catch (Exception e2) {
			}
		}
	}

	/**
	 * 주의:대상 파일이 존재하면 지우고 이동 시킨다.
	 */
	public static void moveFile(File srcFile, File destFile) throws IOException {
		try {
			FileUtils.moveFile(srcFile, destFile);
		} catch (FileExistsException e) {
			TheLogger.info(
					"DestFile(%s) exists, so DestFile delete and moveFile",
					destFile.getPath());

			FileUtils.deleteQuietly(destFile);

			try {
				FileUtils.moveFile(srcFile, destFile);
			} catch (Exception e2) {
			}
		}
	}
}
