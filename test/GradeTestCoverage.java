package test;
import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import main.Classification;
import main.Grade;

class GradeTestCoverage {

	/**
	 * Testing constructor
	 */
	@Test
	void testConstuctor() {
		assertThrows(IllegalArgumentException.class,
				() -> { new Grade(0); });
	}
	
	@ParameterizedTest
	@ValueSource(ints = {Integer.MIN_VALUE , 0, -5, 21, 101, 2023, Integer.MAX_VALUE })
	void testConstuctorIllegal(int point){
		assertThrows(IllegalArgumentException.class,
				() -> { new Grade(point); });
	};

	/**
	 * Testing classify()
	 */
	
	@ParameterizedTest
	@DisplayName("Classify as First")
	@ValueSource(ints = {1, 2, 3, 4})
	void testClassificationFirst(int grade){
		Grade g = new Grade(grade);
		assertEquals(g.classify(), Classification.First);
	};
	
	@ParameterizedTest
	@DisplayName("Classify as UpperSecond")
	@ValueSource(ints = {5, 6, 7, 8})
	void testClassificationUpperSecond(int grade){
		Grade g = new Grade(grade);
		assertEquals(g.classify(), Classification.UpperSecond);
	};
	
	@ParameterizedTest
	@DisplayName("Classify as LowerSecond")
	@ValueSource(ints = {9, 10, 11, 12})
	void testClassificationLowerSecond(int grade){
		Grade g = new Grade(grade);
		assertEquals(g.classify(), Classification.LowerSecond);
	};
	
	
	@ParameterizedTest
	@DisplayName("Classify as Third")
	@ValueSource(ints = {13, 14, 15, 16})
	void testClassificationThird(int grade){
		Grade g = new Grade(grade);
		assertEquals(g.classify(), Classification.Third);
	};
	
	@ParameterizedTest
	@DisplayName("Classify as Fail")
	@ValueSource(ints = {17, 18, 19, 20})
	void testClassificationFail(int grade){
		Grade g = new Grade(grade);
		assertEquals(g.classify(), Classification.Fail);
	};

	
	
	
	
	/**
	 * Testing fromPercentage()
	 */
	
	@ParameterizedTest
	@ValueSource(ints = {Integer.MIN_VALUE , -2, -5, - 3434, 101, 2023, Integer.MAX_VALUE })
	void testFromPercentageToPointIllegal(int perc){
		assertThrows(IllegalArgumentException.class,
				() -> { Grade.fromPercentage(perc); });
	};
	
	
	@ParameterizedTest
	@ValueSource(ints = {79, 80, 85, 91, 96, 100})
	void testFromPercentageToPoint1(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 1);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {76, 77, 78})
	void testFromPercentageToPoint2(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 2);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {73, 74, 75})
	void testFromPercentageToPoint3(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 3);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {70, 71, 72})
	void testFromPercentageToPoint4(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 4);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {69, 68, 67})
	void testFromPercentageToPoint5(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 5);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {66, 65})
	void testFromPercentageToPoint6(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 6);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {64, 63, 62})
	void testFromPercentageToPoint7(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 7);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {61, 60})
	void testFromPercentageToPoint8(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 8);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {59, 58, 57})
	void testFromPercentageToPoint9(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 9);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {56, 55})
	void testFromPercentageToPoint10(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 10);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {54, 53, 52})
	void testFromPercentageToPoint11(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 11);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {51, 50})
	void testFromPercentageToPoint12(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 12);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {49, 48, 47})
	void testFromPercentageToPoint13(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 13);
	};
	@ParameterizedTest
	@ValueSource(ints = {46, 45})
	void testFromPercentageToPoint14(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 14);
	};
	@ParameterizedTest
	@ValueSource(ints = {44, 43, 42})
	void testFromPercentageToPoint15(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 15);
	};
	@ParameterizedTest
	@ValueSource(ints = {41, 40})
	void testFromPercentageToPoint16(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 16);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {39, 38, 37, 36, 35})
	void testFromPercentageToPoint17(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 17);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {34, 33, 32, 31, 30})
	void testFromPercentageToPoint18(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 18);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {29, 28, 25, 23, 20, 17, 14, 9, 6, 2, 0})
	void testFromPercentageToPoint19(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 19);
	};
	
	@ParameterizedTest
	@ValueSource(ints = {-1})
	void testFromPercentageToPoint20(int perc){
		Grade g = Grade.fromPercentage(perc);
		assertEquals(g.getPoints(), 20);
	};

}
