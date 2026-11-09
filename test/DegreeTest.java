package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import main.Classification;
import main.Degree;
import main.Grade;

class DegreeTest {

	/**
	 * 3  tests for the constructor, one for each distinct way input can be invalid
	 */
	@Test
	@DisplayName("Input has a null list")
	void testInvalidConstructor1() {
		List<Grade> g1 = null;

		List<Grade> g2 = new ArrayList<Grade>();
		g2.add(Grade.fromPercentage(90));
		g2.add(Grade.fromPercentage(80));
		g2.add(Grade.fromPercentage(70));
		g2.add(Grade.fromPercentage(60));

		assertAll("Null List in input",
				() -> assertNull(g1),
				() -> assertThrows(IllegalArgumentException.class,
						() -> { new Degree(g1, g2); })
				);
	}

	@Test
	@DisplayName("Input list has less than 4 grades")
	void testInvalidConstructor3() {
		List<Grade> g1 = new ArrayList<Grade>();
		g1.add(Grade.fromPercentage(80));
		g1.add(Grade.fromPercentage(70));
		g1.add(Grade.fromPercentage(60));
		

		List<Grade> g2 = new ArrayList<Grade>();
		g2.add(Grade.fromPercentage(90));
		g2.add(Grade.fromPercentage(80));
		g2.add(Grade.fromPercentage(70));
		g2.add(Grade.fromPercentage(60));

		assertThrows(IllegalArgumentException.class,
				() -> { new Degree(g1, g2); });
	}
	
	@Test
	@DisplayName("Input has a fail grade")
	void testInvalidConstructor2() {
		List<Grade> g1 = new ArrayList<Grade>();
		g1.add(Grade.fromPercentage(34));
		g1.add(Grade.fromPercentage(80));
		g1.add(Grade.fromPercentage(70));
		g1.add(Grade.fromPercentage(60));

		List<Grade> g2 = new ArrayList<Grade>();
		g2.add(Grade.fromPercentage(90));
		g2.add(Grade.fromPercentage(80));
		g2.add(Grade.fromPercentage(70));
		g2.add(Grade.fromPercentage(60));

		assertThrows(IllegalArgumentException.class,
				() -> { new Degree(g1, g2); });
	}
	
	
	/**
	 * 5 tests, using Classifications as equivalence classes
	 */
	@Test
	@DisplayName("Classify correctly as First")
	void testClassifyDegreeFirst() {
		List<Grade> gradesYr2 = new ArrayList<Grade>();
		gradesYr2.add(Grade.fromPercentage(80));
		gradesYr2.add(Grade.fromPercentage(75));
		gradesYr2.add(Grade.fromPercentage(75));
		gradesYr2.add(Grade.fromPercentage(75));

		List<Grade> gradesYr3 = new ArrayList<Grade>();
		gradesYr3.add(Grade.fromPercentage(65));
		gradesYr3.add(Grade.fromPercentage(64));
		gradesYr3.add(Grade.fromPercentage(63));
		gradesYr3.add(Grade.fromPercentage(62));

		Degree d = new Degree(gradesYr2, gradesYr3);
		assertEquals(Classification.First, d.classify());

	}

	@Test
	@DisplayName("Classify correctly as Upper Second")
	void testClassifyDegreeUpperSecond() {
		List<Grade> gradesYr2 = new ArrayList<Grade>();
		gradesYr2.add(Grade.fromPercentage(67));
		gradesYr2.add(Grade.fromPercentage(65));
		gradesYr2.add(Grade.fromPercentage(64));
		gradesYr2.add(Grade.fromPercentage(63));

		List<Grade> gradesYr3 = new ArrayList<Grade>();
		gradesYr3.add(Grade.fromPercentage(59));
		gradesYr3.add(Grade.fromPercentage(57));
		gradesYr3.add(Grade.fromPercentage(57));
		gradesYr3.add(Grade.fromPercentage(57));

		Degree d = new Degree(gradesYr2, gradesYr3);
		assertEquals(Classification.UpperSecond, d.classify());
	}

	@Test
	@DisplayName("Classify correctly as Lower Second")
	void testClassifyDegreeLowerSecond() {
		List<Grade> gradesYr2 = new ArrayList<Grade>();
		gradesYr2.add(Grade.fromPercentage(59));
		gradesYr2.add(Grade.fromPercentage(56));
		gradesYr2.add(Grade.fromPercentage(54));
		gradesYr2.add(Grade.fromPercentage(52));

		List<Grade> gradesYr3 = new ArrayList<Grade>();
		gradesYr3.add(Grade.fromPercentage(49));
		gradesYr3.add(Grade.fromPercentage(48));
		gradesYr3.add(Grade.fromPercentage(47));
		gradesYr3.add(Grade.fromPercentage(46));

		Degree d = new Degree(gradesYr2, gradesYr3);
		assertEquals(Classification.LowerSecond, d.classify());
	}

	@Test
	@DisplayName("Classify correctly as Third")
	void testClassifyDegreeThird() {
		List<Grade> gradesYr2 = new ArrayList<Grade>();
		gradesYr2.add(Grade.fromPercentage(49));
		gradesYr2.add(Grade.fromPercentage(48));
		gradesYr2.add(Grade.fromPercentage(47));
		gradesYr2.add(Grade.fromPercentage(47));

		List<Grade> gradesYr3 = new ArrayList<Grade>();
		gradesYr3.add(Grade.fromPercentage(49));
		gradesYr3.add(Grade.fromPercentage(43));
		gradesYr3.add(Grade.fromPercentage(45));
		gradesYr3.add(Grade.fromPercentage(46));

		Degree d = new Degree(gradesYr2, gradesYr3);
		assertEquals(Classification.Third, d.classify());

	}

	@Test
	@DisplayName("Classify correctly as Discretion")
	void testClassifyDegreeDiscretion() {
		List<Grade> gradesYr2 = new ArrayList<Grade>();
		gradesYr2.add(Grade.fromPercentage(80));
		gradesYr2.add(Grade.fromPercentage(70));
		gradesYr2.add(Grade.fromPercentage(90));
		gradesYr2.add(Grade.fromPercentage(90));

		List<Grade> gradesYr3 = new ArrayList<Grade>();
		gradesYr3.add(Grade.fromPercentage(40));
		gradesYr3.add(Grade.fromPercentage(40));
		gradesYr3.add(Grade.fromPercentage(40));
		gradesYr3.add(Grade.fromPercentage(40));

		Degree d = new Degree(gradesYr2, gradesYr3);
		assertEquals(Classification.Discretion, d.classify());

	}


}
