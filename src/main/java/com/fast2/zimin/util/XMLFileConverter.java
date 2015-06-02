package com.fast2.zimin.util;

import org.springframework.oxm.Marshaller;
import org.springframework.oxm.Unmarshaller;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class XMLFileConverter {
	private Marshaller marshaller;
	private Unmarshaller unmarshaller;

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public void convertObjectToXML(Object object, String filepath)
			throws IOException {

		FileOutputStream os = null;
		try {
			os = new FileOutputStream(filepath);
			getMarshaller().marshal(object, new StreamResult(os));
		} finally {
			if (os != null) {
				os.close();
			}
		}
	}

	public Object convertXMLToObject(String xmlfile) throws IOException {

		FileInputStream is = null;
		try {
			is = new FileInputStream(xmlfile);
			return getUnmarshaller().unmarshal(new StreamSource(is));
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}
/*
	public void sendXmlByFtp(
			com.fast2.zimin.deploy.model.adi3i02xsd.ADIContainerType bean,
			String xmlFileName, String host, int port, String user,
			String password, String targetDir, String tempDir) throws Exception {
		// create a temporary file.
		File tempFile = File.createTempFile("zimin", ".tmp");

		String tempFilePath = String.format("%s/%s", tempDir, xmlFileName);
		String targetFilePath = String.format("%s/%s", targetDir, xmlFileName);
		FTPClient ftpClient = new FTPClient();
		try {
			convertObjectToXML(bean, tempFile.getPath());

			ftpClient.setControlEncoding("UTF-8");
			ftpClient.connect(host, port);
			if (!ftpClient.login(user, password)) {
				throw new Exception("FTP login failed.");
			}
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			if (!ftpClient.changeWorkingDirectory(tempDir)) {
				throw new Exception("FTP change temp directory failed.");
			}

			InputStream inputStream = new FileInputStream(tempFile);
			ftpClient.storeFile(xmlFileName, inputStream);

			IOUtils.closeQuietly(inputStream);

			if (!ftpClient.rename(tempFilePath, targetFilePath)) {
				throw new Exception(String.format("FTP ADI(%s) send Error.",
						targetFilePath));
			}

			// if (!ftpClient.completePendingCommand()) {
			// throw new Exception(String.format("FTP ADI(%s) send Error.",
			// xmlFileName));
			// }
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException e) {
				TheLogger.debug(e.getMessage());
			}

			if (tempFile.exists()) {
				FileUtil.deleteQuietly(tempFile);
			}
		}
	}

	public void sendXmlByFtp(ADIContainerType bean, String xmlFileName,
			String host, int port, String user, String password,
			String targetDir, String tempDir) throws Exception {
		// create a temporary file.
		File tempFile = File.createTempFile("zimin", ".tmp");

		String tempFilePath = String.format("%s/%s", tempDir, xmlFileName);
		String targetFilePath = String.format("%s/%s", targetDir, xmlFileName);
		FTPClient ftpClient = new FTPClient();
		try {
			convertObjectToXML(bean, tempFile.getPath());

			ftpClient.setControlEncoding("UTF-8");
			ftpClient.connect(host, port);
			if (!ftpClient.login(user, password)) {
				throw new Exception("FTP login failed.");
			}
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			if (!ftpClient.changeWorkingDirectory(tempDir)) {
				throw new Exception("FTP change temp directory failed.");
			}

			InputStream inputStream = new FileInputStream(tempFile);
			ftpClient.storeFile(xmlFileName, inputStream);

			IOUtils.closeQuietly(inputStream);

			if (!ftpClient.rename(tempFilePath, targetFilePath)) {
				throw new Exception(String.format("FTP ADI(%s) send Error.",
						targetFilePath));
			}

			// if (!ftpClient.completePendingCommand()) {
			// throw new Exception(String.format("FTP ADI(%s) send Error.",
			// xmlFileName));
			// }
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ftpClient.isConnected()) {
					ftpClient.logout();
					ftpClient.disconnect();
				}
			} catch (IOException e) {
				TheLogger.debug(e.getMessage());
			}

			if (tempFile.exists()) {
				FileUtil.deleteQuietly(tempFile);
			}
		}
	}*/
}
