package com.feiyangedu.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class TelTest {

	@Test
	public void testIsValidTel() {
		assertEquals(new Tel("010", "123456"), Tel.parse("010-123456"));
		assertEquals(new Tel("0123", "12345678"), Tel.parse("0123-12345678"));

		assertNull(Tel.parse("123-12345678"));
		assertNull(Tel.parse("010-0123456"));
		assertNull(Tel.parse("010#12345678"));
	}

}
