package com.fast2.zimin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


//import org.apache.commons.codec.binary.Base64;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fast2.zimin.util.Constants;

public class StringEncryption {
	
	public static String encryptString(int encryptionType, String orgString)
	{
		if(orgString == null)
		{
			return null;
		}
		
		switch(encryptionType)
		{
			case Constants.ENCRYPTION.ENCRYPTION_TYPE_SHA256:
				return encryptSHA256(orgString);
				
			case Constants.ENCRYPTION.ENCRYPTION_TYPE_BCRYPT:
				return encryptBcrypt(orgString);
			
			default: return null;
		}
		
	}

	private static String encryptBcrypt(String orgString)
	{
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encryptString = passwordEncoder.encode(orgString);
		return encryptString;
	}

	private static String encryptSHA256(String str)
	{
		String SHA = ""; 
		try{
			byte[] plainText = null; // 평문
			byte[] hashValue = null; // 해쉬값
			plainText = str.getBytes();

			MessageDigest md = MessageDigest.getInstance("SHA-256");
			hashValue = md.digest(plainText);

			/*
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(hashValue);
			*/
			return new String(Base64.encode(hashValue));
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}
	
}
