package edu.ncsu.csc216.stp.model.manager;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;

import org.junit.jupiter.api.Test;

import edu.ncsu.csc216.stp.model.tests.TestCase;

class TestPlanManagerTest {

	/**
	 * Tests TestPlanManager constructor
	 */
	@Test
	public void testTestPlanManagerConstructor() {
		TestPlanManager t = assertDoesNotThrow(() -> new TestPlanManager(), "Should not throw exception.");
		assertEquals("Failing Tests", t.getCurrentTestPlan().getTestPlanName());
		assertFalse(t.isChanged());
	
	}
	
	@Test
	public void testAddEditSavePlan() {
		TestPlanManager t = assertDoesNotThrow(() -> new TestPlanManager(), "Should not throw exception.");
		try {
			t.addTestPlan("failing tests");
			fail();
		}
		catch(IllegalArgumentException i) {
			assertEquals("Invalid name.", i.getMessage());
		}
		
		t.addTestPlan("Backlog");
		assertEquals("Backlog", t.getCurrentTestPlan().getTestPlanName());
		assertTrue(t.isChanged());
		try {
			t.addTestPlan("backlog");
			fail();
		}
		catch(IllegalArgumentException i) {
			assertEquals("Invalid name.", i.getMessage());
		}
		
		//test array of plan names		
		String[] names = t.getTestPlanNames();
		assertEquals("Failing Tests", names[0]);
		assertEquals("Backlog", names[1]);
		
		//test edit plan		
		try {
			t.editTestPlan("Backlog");
			fail();
		}
		catch(IllegalArgumentException i) {
			assertEquals("Invalid name.", i.getMessage());
		}
		
		t.addTestPlan("WolfScheduler");
		assertEquals("WolfScheduler", t.getCurrentTestPlan().getTestPlanName());
		try {
			t.editTestPlan("Backlog");
			fail();
		}
		catch(IllegalArgumentException i) {
			assertEquals("Invalid name.", i.getMessage());
		}
		try {
			t.editTestPlan("fAILING tests");
			fail();
		}
		catch(IllegalArgumentException i) {
			assertEquals("Invalid name.", i.getMessage());
		}
		
		//save to file
		File f = new File("test-files/test_TPWriter.txt");		
		t.saveTestPlans(f);
		assertFalse(t.isChanged());
		
		//testing remove
		t.removeTestPlan();
		assertEquals("Failing Tests", t.getCurrentTestPlan().getTestPlanName());
		
		
	}
	
	@Test
	public void testAddTestCase() {
		TestPlanManager t = assertDoesNotThrow(() -> new TestPlanManager(), "Should not throw exception.");
		t.addTestPlan("Backlog");
		TestCase tc1 = new TestCase("A Test Case 1", "requirements", "the best test ever", "Pop up that says hello");
		t.addTestCase(tc1);
		t.addTestResult(0, true, "actual results!!");
		//tc1.addTestResult(false, "actual results");
		assertTrue(t.isChanged());
		
		String[][] arrayOfTC = t.getCurrentTestPlan().getTestCasesAsArray();
		assertEquals(1, arrayOfTC.length);
		
		
		
		
	}

}
