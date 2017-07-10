package com.feiyangedu.sample;

import static org.junit.Assert.*;

import org.junit.Test;

public class Is1900sTest {

	@Test
	public void testIs19xx() {
		assertTrue(Is1900s.is19xx("1900"));
		assertTrue(Is1900s.is19xx("1901"));
		assertTrue(Is1900s.is19xx("1911"));
		assertTrue(Is1900s.is19xx("1932"));
		assertTrue(Is1900s.is19xx("1949"));
		assertTrue(Is1900s.is19xx("1998"));
		assertTrue(Is1900s.is19xx("1999"));

		assertFalse(Is1900s.is19xx(null));
		assertFalse(Is1900s.is19xx(""));
		assertFalse(Is1900s.is19xx(" "));
		assertFalse(Is1900s.is19xx("19"));
		assertFalse(Is1900s.is19xx("190A"));
		assertFalse(Is1900s.is19xx("19001"));
		assertFalse(Is1900s.is19xx("1900s"));
		assertFalse(Is1900s.is19xx("2900"));
		assertFalse(Is1900s.is19xx("A900"));
	}

}
