package com.feiyangedu.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class TimeTest {

	@Test
	public void testParseTime() {
		assertEquals(new Time(0, 0, 0), Time.parseTime("0:0:0"));
		assertEquals(new Time(0, 0, 59), Time.parseTime("0:0:59"));
		assertEquals(new Time(0, 59, 0), Time.parseTime("0:59:0"));
		assertEquals(new Time(5, 0, 9), Time.parseTime("05:00:9"));
		assertEquals(new Time(10, 2, 9), Time.parseTime("10:02:9"));
		assertEquals(new Time(10, 2, 9), Time.parseTime("10:2:09"));
		assertEquals(new Time(23, 0, 1), Time.parseTime("23:0:01"));
		assertEquals(new Time(23, 59, 59), Time.parseTime("23:59:59"));

		assertNull(Time.parseTime("000:00:00"));
		assertNull(Time.parseTime("23:59:60"));
		assertNull(Time.parseTime("23:60:59"));
		assertNull(Time.parseTime("24:00:00"));
		assertNull(Time.parseTime("0:0:A"));
		assertNull(Time.parseTime("11-12-12"));
	}
}
