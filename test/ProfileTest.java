package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Classification;
import main.Grade;
import main.Profile;

class ProfileTest {

	/**
	 * 3  tests for the constructor, one for each distinct way input can be invalid
	 */
	@Test
	@DisplayName("Input has Fail grades")
	void testInvalidConstructor1() {
		List<Grade> g = new ArrayList<Grade>();
		g.add(Grade.fromPercentage(34));
		assertAll("Fail Grade", 
				() -> assertNotNull(g),
				() -> assertThrows(IllegalArgumentException.class,
						() -> { new Profile(g); })
				);
	}

	
	@Test
	@DisplayName("Input is an Empty List")
	void testInvalidConstructor2() {
		List<Grade> g = new ArrayList<Grade>();
		assertAll("Empty List not Null",
				() -> assertNotNull(g),
				() -> assertThrows(IllegalArgumentException.class,
						() -> { new Profile(g); })
				);
	}

	@Test
	@DisplayName("Input is Null")
	void testInvalidConstructor3() {
		List<Grade> g = null;
		assertAll("Null Input",
				() -> assertNull(g),
				() -> assertThrows(IllegalArgumentException.class,
						() -> { new Profile(g);}));
	}

	/**
	 * 6 tests, one for each possible combination of Classification
	 *  and truth value (whether the profile is clear or not) 
	 *  as an equivalence class
	 */

	@Test
	@DisplayName("Classify First CLEAR")
	void testClassifyFirstClear() {
		List<Grade> g = new ArrayList<Grade>();
		g.add(Grade.fromPercentage(90));
		g.add(Grade.fromPercentage(78));
		g.add(Grade.fromPercentage(81));
		g.add(Grade.fromPercentage(85));
		Profile p = new Profile(g);
		assertAll("Clear Classification", 
				() -> assertEquals(Classification.First, p.classify()),
				() -> assertTrue(p.isClear()));
	}
	
	@Test
	@DisplayName("Classify First CLEAR")
	void testClassifyFirstClear2() {
		List<Grade> g = new ArrayList<Grade>();
		g.add(Grade.fromPercentage(90));
		g.add(Grade.fromPercentage(78));
		g.add(Grade.fromPercentage(81));
		g.add(Grade.fromPercentage(85));
		
		g.add(Grade.fromPercentage(69));
		g.add(Grade.fromPercentage(67));
		g.add(Grade.fromPercentage(66));
		g.add(Grade.fromPercentage(65));
		Profile p = new Profile(g);
		assertAll("Clear Classification", 
				() -> assertEquals(Classification.First, p.classify()),
				() -> assertTrue(p.isClear()));
	}

	@Test
	@DisplayName("Classify First BORDERLINE")
	void testClassifyFirstBorderline() {
		List<Grade> g = new ArrayList<Grade>();
		g.add(Grade.fromPercentage(90));
		g.add(Grade.fromPercentage(78));
		g.add(Grade.fromPercentage(45));
		g.add(Grade.fromPercentage(41));
		Profile p = new Profile(g);
		assertAll("Borderline Classification",
				() -> assertEquals(Classification.First, p.classify()), 
				() -> assertFalse(p.isClear())
				);
	}

	@Test
	@DisplayName("Classify UpperSecond CLEAR")
	void testClassifyUpperSecondClear() {
		List<Grade> g = new ArrayList<Grade>();
		g.add(Grade.fromPercentage(69));
		g.add(Grade.fromPercentage(66));
		g.add(Grade.fromPercentage(62));
		g.add(Grade.fromPercentage(41)); 
		Profile p = new Profile(g);
		assertAll("Clear Classification",
				() -> assertEquals(Classification.UpperSecond, p.classify()), 
				() -> assertTrue(p.isClear())
				);

	}

	@Test
	@DisplayName("Classify UpperSecond BORDERLINE")
	void testClassifyUpperSecondBorderline() {
		List<Grade> g = new ArrayList<Grade>();
		g.add(Grade.fromPercentage(68));
		g.add(Grade.fromPercentage(62));
		g.add(Grade.fromPercentage(45));
		g.add(Grade.fromPercentage(41));
		Profile p = new Profile(g);
		assertAll("Borderline Classification",
				() -> assertEquals(Classification.UpperSecond, p.classify()), 
				() -> assertFalse(p.isClear())
				);
	}

	@Test
	@DisplayName("Classify LowerSecond CLEAR")
	void testClassifyLowerSecondClear() {
		List<Grade> g = new ArrayList<Grade>();
		g.add(Grade.fromPercentage(58));
		g.add(Grade.fromPercentage(54));
		g.add(Grade.fromPercentage(52));
		g.add(Grade.fromPercentage(41));
		Profile p = new Profile(g);
		assertAll("Clear Classification",
				() -> assertEquals(Classification.LowerSecond, p.classify()), 
				() -> assertTrue(p.isClear())
				);
	}

	@Test
	@DisplayName("Classify Third CLEAR")
	void testClassifyThirdClear() {
		List<Grade> g = new ArrayList<Grade>();
		g.add(Grade.fromPercentage(49));
		g.add(Grade.fromPercentage(45));
		g.add(Grade.fromPercentage(44));
		g.add(Grade.fromPercentage(41));
		Profile p = new Profile(g);

		assertAll("Borderline Classification",
				() -> assertEquals(Classification.Third, p.classify()), 
				() -> assertTrue(p.isClear())
				);
	}
}
