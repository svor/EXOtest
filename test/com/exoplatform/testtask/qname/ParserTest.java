package com.exoplatform.testtask.qname;

import static org.junit.Assert.*;

import org.junit.Test;

public class ParserTest {

	Parser tester = new Parser();
	
	@Test
	public void testIsNameVersion1() {
		assertTrue(tester.isName("name"));
		assertTrue(tester.isName("prefix:name"));
		assertTrue(tester.isName("prefix:na me"));
	}

	@Test
	public void testIsNameVersion2() {
		assertTrue(tester.isName("n:uyuh6768hiu"));
		assertTrue(tester.isName("pref_1x:naїїїьу"));
	}
	
	@Test
	public void testIsNameVersion3() {
		assertFalse(tester.isName(""));
		assertFalse(tester.isName(":name"));
		assertFalse(tester.isName("."));
		assertFalse(tester.isName(".."));
		assertFalse(tester.isName("prefix:"));
		
	}
	
	@Test
	public void testIsNameVersion4() {
		assertFalse(tester.isName(" name"));
		assertFalse(tester.isName(" prefix:name"));
		assertFalse(tester.isName("prefix:name "));
		assertFalse(tester.isName("pre fix:name"));
		assertFalse(tester.isName("name/name"));
		assertFalse(tester.isName("name[name"));
		assertFalse(tester.isName("prefix:name:name"));
		assertFalse(tester.isName("1nїme:name"));
		
	}

}
