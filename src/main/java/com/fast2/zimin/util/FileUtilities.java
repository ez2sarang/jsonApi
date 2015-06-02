package com.fast2.zimin.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;



import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class FileUtilities {
	
	public static File[] getFileList(String directoryPath)
	{
		return new File(directoryPath).listFiles();
	}

//	public static Document getXmlDocument(File xmlFile) throws Exception {
//		
//		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
//		
//		Document doc = null;
//		
//		try
//		{
//			doc = dBuilder.parse(xmlFile);
//		} catch(Exception e)
//		{
//			throw e;
//		}
//		
//		return doc;
//	}

	public static Document getXmlDocument(File xmlFile) throws Exception{
		
		InputStream targetStream = null;
		try
		{
			targetStream = new FileInputStream(xmlFile);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			throw e;
		}

		DOMParser parser = new DOMParser();
		InputSource inputSource = new InputSource(targetStream);
		
		try
		{
			parser.parse(inputSource);
		}
		catch (SAXException e)
		{
			e.printStackTrace();
			try
			{
				targetStream.close();
				throw e;
			}
			catch (IOException e1)
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
				throw e1;
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
		Document doc = parser.getDocument();

		return doc;
	}
	
	public static File copyFile(File sourceFile, String destPath, String targetName) throws Exception {
		
		InputStream inStream = null;
		OutputStream outStream = null;
		
		File destFile;
		
		String destFilePath = destPath + "/";
		
		if((targetName == null) || (targetName.length() == 0))
		{
			destFilePath = destFilePath + sourceFile.getName();
		}
		else
		{
			destFilePath = destFilePath + targetName;
		}
		
		destFile =new File(destFilePath);
		
		inStream = new FileInputStream(sourceFile);
		outStream = new FileOutputStream(destFile);
		
		byte[] buffer = new byte[1024];
		int length;
		
    	//copy the file content in bytes
		while ((length = inStream.read(buffer)) > 0)
		{
			outStream.write(buffer, 0, length);
		}
		
		inStream.close();
		outStream.close();
			
		return destFile;
	}
	
	public static File moveFile(File sourceFile, String destPath, String destName)
	{
		String targetPath = destPath + "/";
		if((destName == null) || (destName.trim().length() == 0))
		{
			targetPath = targetPath + sourceFile.getName();
		}
		else
		{
			targetPath = targetPath + destName;
		}
		
		File newFile = new File(targetPath);
		sourceFile.renameTo(newFile);
		
		return newFile;
	}
	
	public static void ftpPut(
			String host,
			String user,
			String password,
			String sourcefileFullPath,
			String destDirectoryPath,
			String tempDirectoryPath,
			String mode) throws Exception
	{
		FTPClient ftp = null;
		
		ftp = new FTPClient();
		ftp.setControlEncoding("UTF-8");
		
		// separate port from host parameter. Delimiter is ':'.
		if(host.indexOf(":") > 0)
		{
			String[] hostAddrPart = host.split(":");
			ftp.connect(hostAddrPart[0], Integer.valueOf(hostAddrPart[1]));
		}
		else
		{
			ftp.connect(host);
		}
		
		ftp.login(user, password);
		
		if((tempDirectoryPath != null) && (tempDirectoryPath.length() > 0))
		{
			ftp.changeWorkingDirectory(tempDirectoryPath);
		}
		
		// set mode for file transer
		if(mode.equals(Constants.FILEUTILS.FTP_MODE_PASSIVE))
		{
			ftp.enterLocalPassiveMode();
		}
		else if(mode.equals(Constants.FILEUTILS.FTP_MODE_ACTIVE))
		{
			ftp.enterLocalActiveMode();
		}
		
		// set file type
		ftp.setBufferSize(1024000);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
		
		File uploadFile = new File(sourcefileFullPath);

		FileInputStream fis = new FileInputStream(uploadFile);

		boolean isSuccess = ftp.storeFile(uploadFile.getName(), fis);
		if(!isSuccess)
		{
			fis.close();
			throw new Exception("FTP file uploading was failed : " + sourcefileFullPath);
		}

		if (fis != null)
		{
			fis.close();
		}

		try
		{
			isSuccess = ftp.rename(tempDirectoryPath + "/" + uploadFile.getName(), destDirectoryPath + "/" + uploadFile.getName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		if(!isSuccess)
		{
			ftp.disconnect();
			throw new Exception("FTP file renaming was failed : " + sourcefileFullPath);
		}
		
		if (ftp != null && ftp.isConnected())
		{
			ftp.disconnect();
		}
	}
	
	public static void ftpPut(
			String host,
			String user,
			String password,
			String[] sourcefileFullPathArray,
			String destDirectoryPath,
			String tempDirectoryPath,
			String mode) throws Exception
	{
		FTPClient ftp = null;
		
		ftp = new FTPClient();
		ftp.setControlEncoding("UTF-8");
		
		// separate port from host parameter. Delimiter is ':'.
		if(host.indexOf(":") > 0)
		{
			String[] hostAddrPart = host.split(":");
			ftp.connect(hostAddrPart[0], Integer.valueOf(hostAddrPart[1]));
		}
		else
		{
			ftp.connect(host);
		}
		
		ftp.login(user, password);
		
		if((tempDirectoryPath != null) && (tempDirectoryPath.length() > 0))
		{
			ftp.changeWorkingDirectory(tempDirectoryPath);
		}
		
		// set mode for file transer
		if(mode.equals(Constants.FILEUTILS.FTP_MODE_PASSIVE))
		{
			ftp.enterLocalPassiveMode();
		}
		else if(mode.equals(Constants.FILEUTILS.FTP_MODE_ACTIVE))
		{
			ftp.enterLocalActiveMode();
		}
		
		// set file type
		ftp.setBufferSize(1024000);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
		
		for(int i = 0 ; i < sourcefileFullPathArray.length ; i++)
		{
			File uploadFile = new File(sourcefileFullPathArray[i]);

			FileInputStream fis = new FileInputStream(uploadFile);

			boolean isSuccess = ftp.storeFile(uploadFile.getName(), fis);
			if(!isSuccess)
			{
				fis.close();
				throw new Exception("FTP file uploading was failed : " + sourcefileFullPathArray[i]);
			}

			if (fis != null)
			{
				fis.close();
			}

			try
			{
				isSuccess = ftp.rename(tempDirectoryPath + "/" + uploadFile.getName(), destDirectoryPath + "/" + uploadFile.getName());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			if(!isSuccess)
			{
				ftp.disconnect();
				throw new Exception("FTP file renaming was failed : " + sourcefileFullPathArray[i]);
			}
		}
		
		if (ftp != null && ftp.isConnected())
		{
			ftp.disconnect();
		}
	}

	public static void ftpPut(
			String host,
			String user,
			String password,
			Map<String, String> sourceToDestPathMap,
			String tempDirectoryPath,
			String mode) throws Exception
	{
		FTPClient ftp = null;
		
		ftp = new FTPClient();
		ftp.setControlEncoding("UTF-8");
		
		// separate port from host parameter. Delimiter is ':'.
		if(host.indexOf(":") > 0)
		{
			String[] hostAddrPart = host.split(":");
			ftp.connect(hostAddrPart[0], Integer.valueOf(hostAddrPart[1]));
		}
		else
		{
			ftp.connect(host);
		}
		
		ftp.login(user, password);
		
		if((tempDirectoryPath != null) && (tempDirectoryPath.length() > 0))
		{
			ftp.changeWorkingDirectory(tempDirectoryPath);
		}
		
		// set mode for file transer
		if(mode.equals(Constants.FILEUTILS.FTP_MODE_PASSIVE))
		{
			ftp.enterLocalPassiveMode();
		}
		else if(mode.equals(Constants.FILEUTILS.FTP_MODE_ACTIVE))
		{
			ftp.enterLocalActiveMode();
		}
		
		// set file type
		ftp.setBufferSize(1024000);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.setFileTransferMode(FTP.BINARY_FILE_TYPE);
		
		List<String> sourceFileFullPathList = new ArrayList<String>(sourceToDestPathMap.keySet());
		
		for(int i = 0 ; i < sourceFileFullPathList.size() ; i++)
		{
			File uploadFile = new File(sourceFileFullPathList.get(i));
			String destDirectoryPath = sourceToDestPathMap.get(sourceFileFullPathList.get(i));

			FileInputStream fis = new FileInputStream(uploadFile);

			boolean isSuccess = ftp.storeFile(uploadFile.getName(), fis);
			if(!isSuccess)
			{
				fis.close();
				throw new Exception("FTP file uploading was failed : " + sourceFileFullPathList.get(i));
			}

			if (fis != null)
			{
				fis.close();
			}

			try
			{
				isSuccess = ftp.rename(tempDirectoryPath + "/" + uploadFile.getName(), destDirectoryPath + "/" + uploadFile.getName());
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}

			if(!isSuccess)
			{
				ftp.disconnect();
				throw new Exception("FTP file renaming was failed : " + sourceFileFullPathList.get(i));
			}
		}
		
		if (ftp != null && ftp.isConnected())
		{
			ftp.disconnect();
		}
	}
	
}
