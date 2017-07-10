package com.feiyangedu.sample;

import java.io.InputStream;
import java.security.MessageDigest;

public class MD5 {

	public static byte[] toMD5(InputStream input) throws Exception {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		byte[] buffer = new byte[1000];
		while(input.read(buffer) != -1) {
			md.update(buffer);
		}
		return md.digest();
	}

	public static void main(String[] args) {
		// TODO: 从传入参数获取文件名，读取文件，计算MD5，打印MD5的十六进制
		// 例如:
		// java com.feiyangedu.sample.MD5 my-test-file.zip
		// f4cbfc0716fe05895bc117fc38cfb54a
	}
}
