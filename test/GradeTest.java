package test;



import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import main.Classification;
import main.Grade;

class GradeTest {
	/**
	 * 2 tests for input below and above the valid range for the constructor
	 */
	@Test
	@DisplayName("Input Below valid range")
	void testBelowValidRangeConstructor() {
		assertThrows(IllegalArgumentException.class,
				() -> { new Grade(0); });
	}

	@Test
	@DisplayName("Input Above valid range")
	void testAboveValidRangeConstructor() {
		assertThrows(IllegalArgumentException.class,
				() -> { new Grade(21); });
	}
	
	/**
	 * 1 test for a valid input, checking that getPoints returns the right value
	 */
	
	@Test
	@DisplayName("getPoints returns right value")
	void testGetPoints() {
		Grade g = new Grade(10);
		assertEquals(g.getPoints(), 10);
	}

	/**
	 * 5 tests for classify, using Classifications as equivalence classes
	 */
	
	@Test
	@DisplayName("classify as First")
	void testClassifyFirst() {
		Grade g = new Grade(2);
		assertEquals(g.classify(), Classification.First);
		
	}
	
	@Test
	@DisplayName("classify as Upper Second")
	void testClassifyUpperSecond() {
		Grade g = new Grade(5);
		assertEquals(g.classify(), Classification.UpperSecond);
	}
	
	@Test
	@DisplayName("classify as Lower Second")
	void testClassifyLowerSecond() {
		Grade g = new Grade(10);
		assertEquals(g.classify(), Classification.LowerSecond);
		
	}
	
	@Test
	@DisplayName("classify as Third")
	void testClassifyThird() {
		Grade g = new Grade(16);
		assertEquals(g.classify(), Classification.Third);
		
	}
	
	@Test
	@DisplayName("classify as Fail")
	void testClassifyFail() {
		Grade g = Grade.fromPercentage(34);
		assertEquals(g.classify(), Classification.Fail);
		
	}
	
	/**
	 * 2 tests for inputs below and above the valid range for fromPercentage
	 */
	
	@Test
	@DisplayName("Percentage Input Below valid range")
	void testBelowFromPercentage() {
		assertThrows(IllegalArgumentException.class,
				() -> { Grade.fromPercentage(-2);});
	}
	
	@Test
	@DisplayName("Percentage Input Above valid range")
	void testAboveFromPercentage() {
		assertThrows(IllegalArgumentException.class,
				() -> { Grade.fromPercentage(101);});
	}
	
	/**
	 * 20 tests for fromPercentage, using each point in the 20-point scale as an equivalence class
	 */
	
	@Test
	@DisplayName("fromPercentage to Grade 1")
	void testFromPercentage1() {
		Grade g = Grade.fromPercentage(79);
		assertEquals(g.getPoints(), 1);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 2")
	void testFromPercentage2() {
		Grade g = Grade.fromPercentage(77);
		assertEquals(g.getPoints(), 2);
	}
	

	@Test
	@DisplayName("fromPercentage to Grade 3")
	void testFromPercentage3() {
		Grade g = Grade.fromPercentage(75);
		assertEquals(g.getPoints(), 3);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 4")
	void testFromPercentage4() {
		Grade g = Grade.fromPercentage(71);
		assertEquals(g.getPoints(), 4);
	}
	
	
	@Test 
	@DisplayName("fromPercentage to Grade 5")
	void testFromPercentage5() {
		Grade g = Grade.fromPercentage(69);
		assertEquals(g.getPoints(), 5);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 6")
	void testFromPercentage6() {
		Grade g = Grade.fromPercentage(66);
		assertEquals(g.getPoints(), 6);
	}
	
	
	@Test
	@DisplayName("fromPercentage to Grade 7")
	void testFromPercentage7() {
		Grade g = Grade.fromPercentage(62);
		assertEquals(g.getPoints(), 7);
		
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 8")
	void testFromPercentage8() {
		Grade g = Grade.fromPercentage(60);
		assertEquals(g.getPoints(), 8);
		
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 9")
	void testFromPercentage9() {
		Grade g = Grade.fromPercentage(57);
		assertEquals(g.getPoints(), 9);
		
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 10")
	void testFromPercentage10() {
		Grade g = Grade.fromPercentage(56);
		assertEquals(g.getPoints(), 10);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 11")
	void testFromPercentage11() {
		Grade g = Grade.fromPercentage(54);
		assertEquals(g.getPoints(), 11);
		
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 12")
	void testFromPercentage12() {
		Grade g = Grade.fromPercentage(50);
		assertEquals(g.getPoints(), 12);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 13")
	void testFromPercentage13() {
		Grade g = Grade.fromPercentage(48);
		assertEquals(g.getPoints(), 13);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 14")
	void testFromPercentage14() {
		Grade g = Grade.fromPercentage(46);
		assertEquals(g.getPoints(), 14);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 15")
	void testFromPercentage15() {
		Grade g = Grade.fromPercentage(43);
		assertEquals(g.getPoints(), 15);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 16")
	void testFromPercentage16() {
		Grade g = Grade.fromPercentage(41);
		assertEquals(g.getPoints(), 16);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 17")
	void testFromPercentage17() {
		Grade g = Grade.fromPercentage(38);
		assertEquals(g.getPoints(), 17);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 18")
	void testFromPercentage18() {
		Grade g = Grade.fromPercentage(32);
		assertEquals(g.getPoints(), 18);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 19")
	void testFromPercentage19() {
		Grade g = Grade.fromPercentage(25);
		assertEquals(g.getPoints(), 19);
	}
	
	@Test
	@DisplayName("fromPercentage to Grade 20")
	void testFromPercentage20() {
		Grade g = Grade.fromPercentage(-1);
		assertEquals(g.getPoints(), 20);
	}
	



}
