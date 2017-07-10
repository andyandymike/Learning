package com.feiyangedu.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class QQTest {

	@Test
	public void testIsValidQQ() {
		assertTrue(QQ.isValidQQ("10000"));
		assertTrue(QQ.isValidQQ("10001"));
		assertTrue(QQ.isValidQQ("99999"));
		assertTrue(QQ.isValidQQ("123456"));
		assertTrue(QQ.isValidQQ("1234567"));
		assertTrue(QQ.isValidQQ("12345678"));
		assertTrue(QQ.isValidQQ("123456789"));
		assertTrue(QQ.isValidQQ("1234567890"));
		assertTrue(QQ.isValidQQ("9999999999"));

		assertFalse(QQ.isValidQQ(null));
		assertFalse(QQ.isValidQQ(""));
		assertFalse(QQ.isValidQQ(" "));
		assertFalse(QQ.isValidQQ("xxxxx"));
		assertFalse(QQ.isValidQQ("9999"));
		assertFalse(QQ.isValidQQ("9999x"));
		assertFalse(QQ.isValidQQ("0x9999"));
		assertFalse(QQ.isValidQQ("x99999"));
		assertFalse(QQ.isValidQQ("99999111110"));
	}

}
