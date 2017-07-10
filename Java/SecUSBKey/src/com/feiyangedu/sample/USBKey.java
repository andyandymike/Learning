package com.feiyangedu.sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

public class USBKey {

	static final String CIPHER_NAME = "PBEwithSHA1and128bitAES-CBC-BC"; // "PBEwithSHA1andDESede";

	private byte[] salt;

	public USBKey(String filename) throws IOException {
		// TODO: 从文件中读取salt:
		InputStream input = new FileInputStream(filename);
		input.read(salt);
	}

	USBKey(byte[] salt) {
		this.salt = salt;
	}

	public static USBKey createUSBKey(String filename) throws NoSuchAlgorithmException, IOException {
		// TODO: 生成随机Salt并保存到文件:
		OutputStream output = new FileOutputStream(filename);
		byte[] salt = SecureRandom.getInstanceStrong().generateSeed(16);
		output.write(salt);
		return new USBKey(salt);
	}

	// 加密:
	public byte[] encrypt(String password, byte[] input) throws GeneralSecurityException {
		// TODO: 根据password和salt加密input:
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory skeyFactory = SecretKeyFactory.getInstance(CIPHER_NAME);
		SecretKey skey = skeyFactory.generateSecret(keySpec);
		PBEParameterSpec pbeps = new PBEParameterSpec(salt, 1000);
		Cipher cipher = Cipher.getInstance(CIPHER_NAME);
		cipher.init(Cipher.ENCRYPT_MODE, skey, pbeps);
		byte[] encrypted = cipher.doFinal(input);
		return encrypted;
	}

	// 解密:
	public byte[] decrypt(String password, byte[] encrypted) throws GeneralSecurityException {
		// TODO: 根据password和salt解密encrypted:
		PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
		SecretKeyFactory skeyFactory = SecretKeyFactory.getInstance(CIPHER_NAME);
		SecretKey skey = skeyFactory.generateSecret(keySpec);
		PBEParameterSpec pbeps = new PBEParameterSpec(salt, 1000);
		Cipher cipher = Cipher.getInstance(CIPHER_NAME);
		cipher.init(Cipher.DECRYPT_MODE, skey, pbeps);
		byte[] decrypted = cipher.doFinal(encrypted);
		return decrypted;
	}

}
