package test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.stream.Stream;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import main.Classification;

import main.Grade;
import main.Profile;

class ProfileTestCoverage {
	
	/**
	 * Constructor
	 * 
	 */
	@ParameterizedTest
	@MethodSource ("generateInvalidList")
	@DisplayName("Constructor Input Empty or Null")
	void testConstructor(List<Grade> g) {
		assertThrows(IllegalArgumentException.class,
				() -> { new Profile(g);}
				);

	}
	
	private static Stream < Arguments > generateInvalidList() {
		List<Grade> nullList = null;
		List<Grade> emptyList = new ArrayList<Grade>();
		
		return Stream.of (
				Arguments.of(nullList) , 
				Arguments.of(emptyList));
	}
	
	
	@ParameterizedTest
	@MethodSource ("generateListWithFailGrades")
	@DisplayName("Constructor Input has Fail grades")
	void testConstructorFailGrades(List<Grade> g) {
		assertAll("Fail Grade", 
				() -> assertNotNull(g),
				() -> assertThrows(IllegalArgumentException.class,
						() -> { new Profile(g); })
				);

	}
	
	private static Stream < Arguments > generateListWithFailGrades() {
		List<Grade> listFailPercentage1 = new ArrayList<Grade>();
		listFailPercentage1.add(Grade.fromPercentage(39));
		
		List<Grade> listFailPercentage2 = new ArrayList<Grade>();
		listFailPercentage2.add(Grade.fromPercentage(34));
		
		List<Grade> listFailPoint1= new ArrayList<Grade>();
		listFailPoint1.add(new Grade(17));
		
		List<Grade> listFailPoint2= new ArrayList<Grade>();
		listFailPoint2.add(new Grade(18));
		
		List<Grade> listFailPoint3= new ArrayList<Grade>();
		listFailPoint3.add(new Grade(19));
		
		List<Grade> listFailPoint4= new ArrayList<Grade>();
		listFailPoint4.add(new Grade(20));
		
		return Stream.of (
				Arguments.of(listFailPercentage1) , 
				Arguments.of(listFailPercentage2),
				Arguments.of(listFailPoint1),
				Arguments.of(listFailPoint2),
				Arguments.of(listFailPoint3),
				Arguments.of(listFailPoint4)
				);
	}
	
	/** 
	 * Testing First Clear
	 * Conditions: 50% of grades are First and no Third class
	 * @param g - list of grades
	 */
	@ParameterizedTest
	@MethodSource ("generateFirstClearProfile")
	@DisplayName("Profile is First CLEAR")
	void testProfileAsFirstClear(List<Grade> g) {
		Profile p = new Profile(g);
		assertAll("First Borderline",
				() -> assertEquals(Classification.First, p.classify()), 
				() -> assertTrue(p.isClear())
				); 
	} 
	
	private static Stream < Arguments > generateFirstClearProfile() {
		List<Grade> AllFirst = new ArrayList<Grade>();
		AllFirst.add(Grade.fromPercentage(90)); // First
		AllFirst.add(Grade.fromPercentage(87)); // First
		AllFirst.add(Grade.fromPercentage(83)); // First
		AllFirst.add(Grade.fromPercentage(79)); // First
		
		List<Grade> FirstAndBelow = new ArrayList<Grade>();
		FirstAndBelow.add(Grade.fromPercentage(69)); // Upper Second
		FirstAndBelow.add(Grade.fromPercentage(87)); // First
		FirstAndBelow.add(Grade.fromPercentage(83)); // First
		FirstAndBelow.add(Grade.fromPercentage(79)); // First
 
		
		List<Grade> mergedList = new ArrayList<Grade>();
		mergedList.addAll(AllFirst);
		mergedList.addAll(FirstAndBelow);
 
		return Stream.of (
				Arguments.of(AllFirst),
				Arguments.of(FirstAndBelow),
				Arguments.of(mergedList));
	}
	
	/** 
	 * Testing First Borderline
	 * Conditions: more than 25% of grades in the list are Third
	 * First List - has 4 grades where 2 Third class grades and 2 First
	 * Second List - has 8 grades where 3 Third class grades and 5 First
	 * @param g - list of grades
	 */
	@ParameterizedTest
	@MethodSource ("generateFirstBorderlineProfile")
	@DisplayName("Profile is First BORDERLINE")
	void testProfileAsFirstBorderline(List<Grade> g) {
		Profile p = new Profile(g);
		assertAll("First Borderline",
				() -> assertEquals(Classification.First, p.classify()), 
				() -> assertFalse(p.isClear())
				);
	} 
	
	private static Stream < Arguments > generateFirstBorderlineProfile() {
		List<Grade> quarterThird = new ArrayList<Grade>();
		quarterThird.add(Grade.fromPercentage(49)); // Third
		quarterThird.add(Grade.fromPercentage(77)); // First 
		quarterThird.add(Grade.fromPercentage(76)); // First
		quarterThird.add(Grade.fromPercentage(79)); // First
		
		List<Grade> halfThird = new ArrayList<Grade>();
		halfThird.add(Grade.fromPercentage(49)); // Third
		halfThird.add(Grade.fromPercentage(42)); // Third
		halfThird.add(Grade.fromPercentage(76)); // First
		halfThird.add(Grade.fromPercentage(79)); // First
		
		List<Grade> mergedList = new ArrayList<Grade>();
		mergedList.addAll(quarterThird);
		mergedList.addAll(halfThird);

		return Stream.of (
				Arguments.of(halfThird) ,
				Arguments.of(mergedList));
	}
	
	/** 
	 * Testing Upper Second Clear
	 * Conditions: 50% of grades are Upper Second or above and no Third Class
	 * @param g - list of grades
	 */
	@ParameterizedTest
	@MethodSource ("generateUpperSecondClearProfile")
	@DisplayName("Profile is Upper Second CLEAR")
	void testProfileAsUpperSecondClear(List<Grade> g) {
		Profile p = new Profile(g);
		assertAll("UpperSecond Borderline",
				() -> assertEquals(Classification.UpperSecond, p.classify()), 
				() -> assertTrue(p.isClear())
				);
	}
	
	private static Stream < Arguments > generateUpperSecondClearProfile() {
		List<Grade> UpperSecondAndAbove = new ArrayList<Grade>();
		UpperSecondAndAbove.add(Grade.fromPercentage(75)); // First
		UpperSecondAndAbove.add(Grade.fromPercentage(67)); // Upper Second
		UpperSecondAndAbove.add(Grade.fromPercentage(66)); // Upper Second
		UpperSecondAndAbove.add(Grade.fromPercentage(69)); // Upper Second
		
		List<Grade> AllUpperSecond = new ArrayList<Grade>();
		AllUpperSecond.add(Grade.fromPercentage(66)); // Upper Second
		AllUpperSecond.add(Grade.fromPercentage(67)); // Upper Second
		AllUpperSecond.add(Grade.fromPercentage(68)); // Upper Second
		AllUpperSecond.add(Grade.fromPercentage(69)); // Upper Second
		
		List<Grade> UpperSecondAndBelow = new ArrayList<Grade>();
		UpperSecondAndBelow.add(Grade.fromPercentage(59)); // Lower Second
		UpperSecondAndBelow.add(Grade.fromPercentage(67)); // Upper Second
		UpperSecondAndBelow.add(Grade.fromPercentage(66)); // Upper Second
		UpperSecondAndBelow.add(Grade.fromPercentage(69)); // Upper Second
		
		List<Grade> mergedList = new ArrayList<Grade>();
		mergedList.addAll(UpperSecondAndBelow);
		mergedList.addAll(AllUpperSecond);

		return Stream.of (
				Arguments.of(UpperSecondAndAbove) , 
				Arguments.of(AllUpperSecond),
				Arguments.of(UpperSecondAndBelow),
				Arguments.of(mergedList));
	}
	
	/** 
	 * Testing Upper Second Borderline
	 * Conditions: more than 25% of grades in the list are Third
	 * First List - has 4 grades where 2 Third class grades and 2 Upper Second
	 * Second List - has 8 grades where 3 Third class grades and 5 Upper Second
	 * @param g - list of grades
	 */
	@ParameterizedTest
	@MethodSource ("generateUpperSecondBorderlineProfile")
	@DisplayName("Profile is Upper Second BORDERLINE")
	void testProfileAsUpperSecondBorderline(List<Grade> g) {
		Profile p = new Profile(g);
		assertAll("UpperSecond Borderline",
				() -> assertEquals(Classification.UpperSecond, p.classify()), 
				() -> assertFalse(p.isClear())
				);
	}
	
	private static Stream < Arguments > generateUpperSecondBorderlineProfile() {
		List<Grade> quarterThird = new ArrayList<Grade>();
		quarterThird.add(Grade.fromPercentage(49)); // Third
		quarterThird.add(Grade.fromPercentage(67)); // Upper Second
		quarterThird.add(Grade.fromPercentage(66)); // Upper Second
		quarterThird.add(Grade.fromPercentage(69)); // Upper Second
		
		List<Grade> halfThird = new ArrayList<Grade>();
		halfThird.add(Grade.fromPercentage(49)); // Third
		halfThird.add(Grade.fromPercentage(42)); // Third
		halfThird.add(Grade.fromPercentage(66)); // Upper Second
		halfThird.add(Grade.fromPercentage(69)); // Upper Second
		
		List<Grade> mergedList = new ArrayList<Grade>();
		mergedList.addAll(quarterThird);
		mergedList.addAll(halfThird);

		return Stream.of (
				Arguments.of(halfThird) , 
				Arguments.of(mergedList));
	}
	
	/** 
	 * Testing Lower Second (always Clear)
	 * Conditions: 50% of grades are Lower Second or above and no Third Class
	 * @param g - list of grades
	 */
	@ParameterizedTest
	@MethodSource ("generateLowerSecondProfile")
	@DisplayName("Profile is Lower Second")
	void testProfileAsLowerSecond(List<Grade> g) {
		Profile p = new Profile(g);
		assertAll("LowerSecond",
				() -> assertEquals(Classification.LowerSecond, p.classify()), 
				() -> assertTrue(p.isClear())
				);
	}
	
	private static Stream < Arguments > generateLowerSecondProfile() {
		List<Grade> LowerSecondAndAbove = new ArrayList<Grade>();
		LowerSecondAndAbove.add(Grade.fromPercentage(69)); // Upper Second
		LowerSecondAndAbove.add(Grade.fromPercentage(59)); // Lower Second
		LowerSecondAndAbove.add(Grade.fromPercentage(58)); // Lower Second
		LowerSecondAndAbove.add(Grade.fromPercentage(57)); // Lower Second
		
		List<Grade> LowerSecondAndFirst = new ArrayList<Grade>();
		LowerSecondAndFirst.add(Grade.fromPercentage(78)); // First
		LowerSecondAndFirst.add(Grade.fromPercentage(59)); // Lower Second
		LowerSecondAndFirst.add(Grade.fromPercentage(58)); // Lower Second
		LowerSecondAndFirst.add(Grade.fromPercentage(57)); // Lower Second
		
		List<Grade> AllLowerSecond = new ArrayList<Grade>();
		AllLowerSecond.add(Grade.fromPercentage(59)); // Lower Second
		AllLowerSecond.add(Grade.fromPercentage(58)); // Lower Second
		AllLowerSecond.add(Grade.fromPercentage(57)); // Lower Second
		AllLowerSecond.add(Grade.fromPercentage(56)); // Lower Second
		
		List<Grade> LowerSecondAndBelow = new ArrayList<Grade>();
		LowerSecondAndBelow.add(Grade.fromPercentage(49)); // Third
		LowerSecondAndBelow.add(Grade.fromPercentage(57)); // Lower Second
		LowerSecondAndBelow.add(Grade.fromPercentage(56)); // Lower Second
		LowerSecondAndBelow.add(Grade.fromPercentage(55)); // Lower Second
		
		List<Grade> mergedList = new ArrayList<Grade>();
		mergedList.addAll(LowerSecondAndBelow);
		mergedList.addAll(LowerSecondAndAbove);

		return Stream.of (
				Arguments.of(LowerSecondAndAbove), 
				Arguments.of(LowerSecondAndFirst),
				Arguments.of(AllLowerSecond),
				Arguments.of(LowerSecondAndBelow),
				Arguments.of(mergedList));
	}
	
	
	/** 
	 * Testing Third (always Clear)
	 * Conditions: More than 50% of grades are Third Class
	 * @param g - list of grades
	 */
	@ParameterizedTest
	@MethodSource ("generateThirdProfile")
	@DisplayName("Profile is third")
	void testProfileAsthird(List<Grade> g) {
		Profile p = new Profile(g);
		assertAll("Third",
				() -> assertEquals(Classification.Third, p.classify()), 
				() -> assertTrue(p.isClear())
				);
	}
	
	private static Stream < Arguments > generateThirdProfile() {
		List<Grade> ThirdAndAbove = new ArrayList<Grade>();
		ThirdAndAbove.add(Grade.fromPercentage(69)); // Upper Second
		ThirdAndAbove.add(Grade.fromPercentage(49)); // Third
		ThirdAndAbove.add(Grade.fromPercentage(48)); // Third
		ThirdAndAbove.add(Grade.fromPercentage(47)); // Third
		
		
		List<Grade> AllThird = new ArrayList<Grade>();
		AllThird.add(Grade.fromPercentage(49)); // Third
		AllThird.add(Grade.fromPercentage(48)); // Third
		AllThird.add(Grade.fromPercentage(47)); // Third
		AllThird.add(Grade.fromPercentage(46)); // Third
		
		
		List<Grade> mergedList = new ArrayList<Grade>();
		mergedList.addAll(AllThird);
		mergedList.addAll(ThirdAndAbove);

		return Stream.of (
				Arguments.of(ThirdAndAbove) , 
				Arguments.of(AllThird),
				Arguments.of(mergedList));
	}
	

}
