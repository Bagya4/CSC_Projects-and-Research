package edu.ncsu.csc216.stp.model.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestResultTest {

	@Test
	public void testTestResult() {
		assertDoesNotThrow(() -> new TestResult(true, "course added (Lab 5)"));
		assertThrows(IllegalArgumentException.class, () -> new TestResult(true, null));
		assertThrows(IllegalArgumentException.class, () -> new TestResult(true, ""));
	}
	
	@Test
	public void testIsPassing() {
		TestResult testResult = assertDoesNotThrow(() -> new TestResult(true, "course added (Lab 5)"));
		TestResult testResult2 = assertDoesNotThrow(() -> new TestResult(false, "courses are listed"));

		assertTrue(testResult.isPassing());
		assertFalse(testResult2.isPassing());

	}
	
	@Test
	public void testGetActualResults() {
		TestResult testResult = assertDoesNotThrow(() -> new TestResult(true, "course added (Lab 5)"));
		TestResult testResult2 = assertDoesNotThrow(() -> new TestResult(false, "courses are listed"));

		assertEquals("course added (Lab 5)", testResult.getActualResults());
		assertEquals("courses are listed", testResult2.getActualResults());

	}
	
	@Test
	public void testToString() {
		TestResult testResult = assertDoesNotThrow(() -> new TestResult(true, "course added (Lab 5)"));
		TestResult testResult2 = assertDoesNotThrow(() -> new TestResult(false, "courses are listed"));

		assertEquals("PASS: course added (Lab 5)", testResult.toString());
		assertEquals("FAIL: courses are listed", testResult2.toString());

	}
}
