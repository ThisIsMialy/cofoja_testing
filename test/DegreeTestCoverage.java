package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import main.Classification;
import main.Degree;
import main.Grade;

class DegreeTestCoverage {


	/*
	 * Testing constructor
	 */
	/**
	 * Testing for when either or both input list is null
	 * @param g1
	 * @param g2
	 */
	@ParameterizedTest
	@MethodSource ("generateNullList")
	@DisplayName("Either list is null")
	void testInvalidConstructorNullInput(List<Grade> g1, List<Grade> g2) {
		assertThrows(IllegalArgumentException.class,
				() -> { new Degree(g1, g2); });
	}

	private static Stream < Arguments > generateNullList() {
		List<Grade> nullList = null;

		List<Grade> notNullList = new ArrayList<Grade>();
		notNullList.add(Grade.fromPercentage(90));
		notNullList.add(Grade.fromPercentage(80));
		notNullList.add(Grade.fromPercentage(70));
		notNullList.add(Grade.fromPercentage(60));

		return Stream.of (
				Arguments.of(nullList, notNullList) ,
				Arguments.of(notNullList, nullList) ,
				Arguments.of(nullList, nullList));
	}
	
	/**
	 * Testing for when either or both input list does not contain exactly 4 grades
	 * BUT neither list is null 
	 * @param g1
	 * @param g2
	 */
	@ParameterizedTest
	@MethodSource ("generateListNotSize4")
	@DisplayName("Either list is not size 4")
	void testInvalidConstructorSizeCheck(List<Grade> g1, List<Grade> g2) {
		
//		assertNotEquals(4, g1.size(), g2.size());
		assertThrows(IllegalArgumentException.class,
				() -> { new Degree(g1, g2); });
	}

	/**
	 * Create a valid and invalid list (not size 4)
	 * Create different combos where Degree receives either one or both
	 * TEST WILL FAIL for the last arguments where BOTH list are size 4
	 * @return List of Grade g1 and g2
	 */
	private static Stream < Arguments > generateListNotSize4() {
		List<Grade> listSize4 = new ArrayList<Grade>();
		listSize4.add(Grade.fromPercentage(90));
		listSize4.add(Grade.fromPercentage(85));
		listSize4.add(Grade.fromPercentage(91));
		listSize4.add(Grade.fromPercentage(84));

		List<Grade> listInvalid = new ArrayList<Grade>();
		listInvalid.add(Grade.fromPercentage(80));

		List<Grade> listEmpty = new ArrayList<Grade>();

		return Stream.of ( 
				Arguments.of(listSize4, listInvalid) ,
				Arguments.of(listInvalid, listSize4) ,
				Arguments.of(listInvalid, listInvalid),
				Arguments.of(listEmpty, listSize4),
				Arguments.of(listSize4, listEmpty),
				Arguments.of(listEmpty, listEmpty),
				Arguments.of(listSize4, listSize4) // Will fail test
				);
	}

	/**
	 * Testing for when either or both input list has a fail Grade
	 * BUT neither list is null NOR empty/of the wrong size
	 * @param g1
	 * @param g2
	 */
	@ParameterizedTest
	@MethodSource ("generateListWithFailGrade")
	@DisplayName("Either list has a Fail grade")
	void testInvalidConstructorFailGrade(List<Grade> g1, List<Grade> g2) {
		assertThrows(IllegalArgumentException.class,
				() -> { new Degree(g1, g2); });
	}

	private static Stream < Arguments > generateListWithFailGrade() {
		List<Grade> listWithFail = new ArrayList<Grade>();
		listWithFail.add(Grade.fromPercentage(39));
		listWithFail.add(Grade.fromPercentage(70));
		listWithFail.add(Grade.fromPercentage(70));
		listWithFail.add(Grade.fromPercentage(80));

		List<Grade> listNoFail = new ArrayList<Grade>();
		listNoFail.add(Grade.fromPercentage(70));
		listNoFail.add(Grade.fromPercentage(70));
		listNoFail.add(Grade.fromPercentage(80));
		listNoFail.add(Grade.fromPercentage(85));

		return Stream.of (
				Arguments.of(listWithFail, listNoFail) ,
				Arguments.of(listNoFail, listWithFail) ,
				Arguments.of(listWithFail, listWithFail));
	} 

	/*
	 * Testing classify()
	 */

	@Test
	@DisplayName("Classification of both profiles are the same")
	void testEqualClassification() {
		List<Grade> gradesYr2 = new ArrayList<Grade>();
		gradesYr2.add(Grade.fromPercentage(80));
		gradesYr2.add(Grade.fromPercentage(75));
		gradesYr2.add(Grade.fromPercentage(75));
		gradesYr2.add(Grade.fromPercentage(75));

		List<Grade> gradesYr3 = new ArrayList<Grade>();
		gradesYr3.add(Grade.fromPercentage(80));
		gradesYr3.add(Grade.fromPercentage(75));
		gradesYr3.add(Grade.fromPercentage(75));
		gradesYr3.add(Grade.fromPercentage(75));

		Degree d = new Degree(gradesYr2, gradesYr3);
		assertEquals(4, gradesYr2.size(), gradesYr3.size());
		assertSame(d.getLvl5Profile().classify(), d.getLvl6Profile().classify());
		assertEquals(Classification.First, d.classify());
	}


	/**
	 * Testing where level 5 profile is better than level 6 profile
	 * Level 5 Profile has both year 2 and year 3 grades
	 */
	
	/*
	 * Level 5 is better, is clear and no more than 1 class above level 6
	 */
	@ParameterizedTest
	@MethodSource ("generateBetterLevel5Profile")
	@DisplayName("Level 5 Profile is Better")
	void testLevel5BetterThanLevel6(List<Grade> year2, List<Grade> year3) {
		Degree d = new Degree(year2, year3);
		assertAll("Better Level 5 conditions", 
				() -> assertEquals(d.getLvl5Profile().compareTo(d.getLvl6Profile()), 1),
				() -> assertTrue(d.getLvl5Profile().isClear()),
				() -> assertSame(d.getLvl5Profile().classify(), d.classify()),
				() -> assertEquals(d.getLvl5Profile().classify().ordinal(), d.getLvl6Profile().classify().ordinal() + 1)	
		);

	}

	private static Stream < Arguments > generateBetterLevel5Profile() {
		/* Level 5 is First */
		List<Grade> year2 = new ArrayList<Grade>();
		year2.add(Grade.fromPercentage(85)); // First
		year2.add(Grade.fromPercentage(80)); // First
		year2.add(Grade.fromPercentage(75)); // First
		year2.add(Grade.fromPercentage(72)); // First
		
		List<Grade> year3 = new ArrayList<Grade>();
		year3.add(Grade.fromPercentage(70)); // First
		year3.add(Grade.fromPercentage(69)); // Upper Second
		year3.add(Grade.fromPercentage(65)); // Upper Second
		year3.add(Grade.fromPercentage(64)); // Upper Second
		
		
		/* Level 5 is UpperSecond */
		List<Grade> year2version2 = new ArrayList<Grade>();
		year2version2.add(Grade.fromPercentage(69)); // Upper Second
		year2version2.add(Grade.fromPercentage(65)); // Upper Second
		year2version2.add(Grade.fromPercentage(64)); // Upper Second
		year2version2.add(Grade.fromPercentage(62)); // Upper Second

		List<Grade> year3version2 = new ArrayList<Grade>();
		year3version2.add(Grade.fromPercentage(64)); // Upper Second
		year3version2.add(Grade.fromPercentage(55)); // Lower Second
		year3version2.add(Grade.fromPercentage(54)); // Lower Second
		year3version2.add(Grade.fromPercentage(53)); // Lower Second
		
		

		return Stream.of (
				Arguments.of(year2, year3),
				Arguments.of(year2version2, year3version2)
				);
	} 

	
	/*
	 * Level 5 is better, NOT clear and no more than 1 class above level 6
	 * More than 2 grades are Third
	 */
//	@ParameterizedTest
//	@MethodSource ("generateBetterLevel5ProfileBorderline")
//	@DisplayName("Level 5 Profile is Better but Borderline")
//	void testLevel5BetterThanLevel6Borderline(List<Grade> year2, List<Grade> year3) {
//		Degree d = new Degree(year2, year3);
//		assertEquals(d.classify(), Classification.U);
////		assertAll("Better Level 5 conditions", 
////				() -> assertEquals(d.getLvl5Profile().compareTo(d.getLvl5Profile()), 1),
////				() -> assertFalse(d.getLvl5Profile().isClear()),
////				() -> assertEquals(d.getLvl5Profile().classify().ordinal(), d.getLvl6Profile().classify().ordinal() + 1),
////				() -> assertEquals(d.classify(), Classification.Discretion)
////		);
//	} 
//
//	private static Stream < Arguments > generateBetterLevel5ProfileBorderline() {
//		/* Level 5 is First */
//		List<Grade> year2 = new ArrayList<Grade>();
//		year2.add(Grade.fromPercentage(43)); // Third
//		year2.add(Grade.fromPercentage(85)); // First
//		year2.add(Grade.fromPercentage(80)); // First
//		year2.add(Grade.fromPercentage(78)); // First
//
//		List<Grade> year3 = new ArrayList<Grade>();
//		year3.add(Grade.fromPercentage(45)); // Third
//		year3.add(Grade.fromPercentage(43)); // Third
//		year3.add(Grade.fromPercentage(80)); // First
//		year3.add(Grade.fromPercentage(85)); // First
//		
//		/* Level 5 is UpperSecond */
//		List<Grade> year2version2 = new ArrayList<Grade>();
//		year2version2.add(Grade.fromPercentage(43)); // Third
//		year2version2.add(Grade.fromPercentage(65)); // Upper Second
//		year2version2.add(Grade.fromPercentage(64)); // Upper Second
//		year2version2.add(Grade.fromPercentage(65)); // Upper Second
//
//		List<Grade> year3version2 = new ArrayList<Grade>();
//		year3version2.add(Grade.fromPercentage(44)); // Third 
//		year3version2.add(Grade.fromPercentage(43)); // Third
//		year3version2.add(Grade.fromPercentage(65)); // Upper Second
//		year3version2.add(Grade.fromPercentage(64)); // Upper Second
//
//		return Stream.of (
//				Arguments.of(year2, year3),
//				Arguments.of(year2version2, year3version2)
//				);
//	} 
	
	/*
	 * Level 5 is better,clear and BUT more than 1 class above level 6
	 */
	@ParameterizedTest
	@MethodSource ("generateBetterLevel5ProfileMoreThanOneClass")
	@DisplayName("Level 5 Profile is Better but More than one class above")
	void testLevel5BetterThanLevel6ClassDifference(List<Grade> year2, List<Grade> year3) {
		Degree d = new Degree(year2, year3);
		assertAll("Better Level 5 conditions", 
				() -> assertEquals(d.getLvl5Profile().compareTo(d.getLvl6Profile()), 1),
				() -> assertTrue(d.getLvl5Profile().isClear()),
				() -> assertNotEquals(d.getLvl5Profile().classify().ordinal(), d.getLvl6Profile().classify().ordinal() + 1),
				() -> assertEquals(d.classify(), Classification.Discretion)
		);

	} 

	private static Stream < Arguments > generateBetterLevel5ProfileMoreThanOneClass() {
		/* Level 5 is First */
		List<Grade> year2 = new ArrayList<Grade>();
		year2.add(Grade.fromPercentage(85)); // First
		year2.add(Grade.fromPercentage(80)); // First
		year2.add(Grade.fromPercentage(75)); // First
		year2.add(Grade.fromPercentage(70)); // First
		
		List<Grade> year3 = new ArrayList<Grade>();
		year3.add(Grade.fromPercentage(59)); // Lower Second
		year3.add(Grade.fromPercentage(55)); // Lower Second
		year3.add(Grade.fromPercentage(54)); // Lower Second
		year3.add(Grade.fromPercentage(53)); // Lower Second


		return Stream.of (
				Arguments.of(year2, year3)
				);
	} 



	//	@Test
	//	@DisplayName("lvl5Profile is better, clear and only 1 class above")
	//	void testLevel5Better() {
	//		List<Grade> gradesYr2 = new ArrayList<Grade>();
	//		gradesYr2.add(Grade.fromPercentage(80));
	//		gradesYr2.add(Grade.fromPercentage(75));
	//		gradesYr2.add(Grade.fromPercentage(75));
	//		gradesYr2.add(Grade.fromPercentage(75));
	//
	//		List<Grade> gradesYr3 = new ArrayList<Grade>();
	//		gradesYr3.add(Grade.fromPercentage(80));
	//		gradesYr3.add(Grade.fromPercentage(68));
	//		gradesYr3.add(Grade.fromPercentage(67));
	//		gradesYr3.add(Grade.fromPercentage(66));
	//
	//		Degree d = new Degree(gradesYr2, gradesYr3);
	//		 
	//		assertAll("Level 5 conditions",
	//				() -> assertEquals(d.getLvl5Profile().compareTo(d.getLvl6Profile()), 1),
	//				() -> assertTrue(d.getLvl5Profile().isClear()),
	//				() -> assertSame(d.getLvl5Profile().classify(), d.classify())
	//		);
	//		assertEquals(Classification.First, d.classify());
	//	}

	
	/**
	 * Testing where level 6 profile is better than level 5 profile
	 * Level 6 only has 4 grades from year 3
	 */
	
	/*
	 * Level 6 is better, is clear and no more than 1 class above level 5
	 */
	@ParameterizedTest
	@MethodSource ("generateBetterLevel6Profile")
	@DisplayName("Level 6 Profile is Better")
	void testLevel6BetterThanLevel5(List<Grade> year2, List<Grade> year3) {
		Degree d = new Degree(year2, year3);
		assertAll("Better Level 6 conditions", 
				() -> assertEquals(d.getLvl6Profile().compareTo(d.getLvl5Profile()), 1),
				() -> assertTrue(d.getLvl6Profile().isClear()),
				() -> assertSame(d.getLvl6Profile().classify(), d.classify()),
				() -> assertEquals(d.getLvl5Profile().classify().ordinal(), d.getLvl6Profile().classify().ordinal() - 1)	
		);

	}

	private static Stream < Arguments > generateBetterLevel6Profile() {
		/* Level 6 is First */
		List<Grade> year2 = new ArrayList<Grade>();
		year2.add(Grade.fromPercentage(69)); // Upper Second
		year2.add(Grade.fromPercentage(65)); // Upper Second
		year2.add(Grade.fromPercentage(64)); // Upper Second
		year2.add(Grade.fromPercentage(63)); // Upper Second

		List<Grade> year3 = new ArrayList<Grade>();
		year3.add(Grade.fromPercentage(65)); // Upper Second
		year3.add(Grade.fromPercentage(70)); // First
		year3.add(Grade.fromPercentage(80)); // First
		year3.add(Grade.fromPercentage(85)); // First
		
		/* Level 6 is UpperSecond */
		List<Grade> year2version2 = new ArrayList<Grade>();
		year2version2.add(Grade.fromPercentage(59)); // Lower Second
		year2version2.add(Grade.fromPercentage(55)); // Lower Second
		year2version2.add(Grade.fromPercentage(54)); // Lower Second
		year2version2.add(Grade.fromPercentage(53)); // Lower Second

		List<Grade> year3version2 = new ArrayList<Grade>();
		year3version2.add(Grade.fromPercentage(55)); // Lower Second
		year3version2.add(Grade.fromPercentage(69)); // Upper Second
		year3version2.add(Grade.fromPercentage(65)); // Upper Second
		year3version2.add(Grade.fromPercentage(64)); // Upper Second

		return Stream.of (
				Arguments.of(year2, year3),
				Arguments.of(year2version2, year3version2)
				);
	} 

	
	/*
	 * Level 6 is better, NOT clear and no more than 1 class above level 5
	 */
	@ParameterizedTest
	@MethodSource ("generateBetterLevel6ProfileBorderline")
	@DisplayName("Level 6 Profile is Better but Borderline")
	void testLevel6BetterThanLevel5Borderline(List<Grade> year2, List<Grade> year3) {
		Degree d = new Degree(year2, year3);
		assertAll("Better Level 6 conditions", 
				() -> assertEquals(d.getLvl6Profile().compareTo(d.getLvl5Profile()), 1),
				() -> assertFalse(d.getLvl6Profile().isClear()),
				() -> assertEquals(d.getLvl5Profile().classify().ordinal(), d.getLvl6Profile().classify().ordinal() - 1),
				() -> assertEquals(d.classify(), Classification.Discretion)
		);

	} 

	private static Stream < Arguments > generateBetterLevel6ProfileBorderline() {
		/* Level 6 is First */
		List<Grade> year2 = new ArrayList<Grade>();
		year2.add(Grade.fromPercentage(69)); // Upper Second
		year2.add(Grade.fromPercentage(65)); // Upper Second
		year2.add(Grade.fromPercentage(64)); // Upper Second
		year2.add(Grade.fromPercentage(63)); // Upper Second

		List<Grade> year3 = new ArrayList<Grade>();
		year3.add(Grade.fromPercentage(45)); // Third
		year3.add(Grade.fromPercentage(43)); // Third
		year3.add(Grade.fromPercentage(80)); // First
		year3.add(Grade.fromPercentage(85)); // First
		
		/* Level 6 is UpperSecond */
		List<Grade> year2version2 = new ArrayList<Grade>();
		year2version2.add(Grade.fromPercentage(59)); // Lower Second
		year2version2.add(Grade.fromPercentage(55)); // Lower Second
		year2version2.add(Grade.fromPercentage(54)); // Lower Second
		year2version2.add(Grade.fromPercentage(53)); // Lower Second

		List<Grade> year3version2 = new ArrayList<Grade>();
		year3version2.add(Grade.fromPercentage(44)); // Third 
		year3version2.add(Grade.fromPercentage(43)); // Third
		year3version2.add(Grade.fromPercentage(65)); // Upper Second
		year3version2.add(Grade.fromPercentage(64)); // Upper Second

		return Stream.of (
				Arguments.of(year2, year3),
				Arguments.of(year2version2, year3version2)
				);
	} 
	
	/*
	 * Level 6 is better,clear and BUT more than 1 class above level 5
	 */
	@ParameterizedTest
	@MethodSource ("generateBetterLevel6ProfileMoreThanOneClass")
	@DisplayName("Level 6 Profile is Better but Borderline")
	void testLevel6BetterThanLevel5ClassDifference(List<Grade> year2, List<Grade> year3) {
		Degree d = new Degree(year2, year3);
		assertAll("Better Level 6 conditions", 
				() -> assertEquals(d.getLvl6Profile().compareTo(d.getLvl5Profile()), 1),
				() -> assertTrue(d.getLvl6Profile().isClear()),
				() -> assertNotEquals(d.getLvl5Profile().classify().ordinal(), d.getLvl6Profile().classify().ordinal() - 1),
				() -> assertEquals(d.classify(), Classification.Discretion)
		);

	} 

	private static Stream < Arguments > generateBetterLevel6ProfileMoreThanOneClass() {
		/* Level 6 is First */
		List<Grade> year2 = new ArrayList<Grade>();
		year2.add(Grade.fromPercentage(59)); // Lower Second
		year2.add(Grade.fromPercentage(55)); // Lower Second
		year2.add(Grade.fromPercentage(54)); // Lower Second
		year2.add(Grade.fromPercentage(53)); // Lower Second

		List<Grade> year3 = new ArrayList<Grade>();
		year3.add(Grade.fromPercentage(59)); // Lower Second
		year3.add(Grade.fromPercentage(70)); // First
		year3.add(Grade.fromPercentage(80)); // First
		year3.add(Grade.fromPercentage(85)); // First
		
		/* Level 6 is UpperSecond */
		List<Grade> year2version2 = new ArrayList<Grade>();
		year2version2.add(Grade.fromPercentage(49)); // Third
		year2version2.add(Grade.fromPercentage(45)); // Third
		year2version2.add(Grade.fromPercentage(44)); // Third
		year2version2.add(Grade.fromPercentage(43)); // Third

		List<Grade> year3version2 = new ArrayList<Grade>();
		year3version2.add(Grade.fromPercentage(44)); // Third 
		year3version2.add(Grade.fromPercentage(69)); // Upper Second
		year3version2.add(Grade.fromPercentage(65)); // Upper Second
		year3version2.add(Grade.fromPercentage(64)); // Upper Second

		return Stream.of (
				Arguments.of(year2, year3),
				Arguments.of(year2version2, year3version2)
				);
	} 



}
