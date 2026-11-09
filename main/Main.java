package main;

import java.util.*;


public class Main {
	
	static List<Grade> yr2Grades;
	static List<Grade> yr3Grades;
	
	public static void clearGrades() {
		yr2Grades = new ArrayList<Grade>();
		yr3Grades = new ArrayList<Grade>();
	}

	public static void main(String[] args) {

		/*Student 1
		 * Lvl5 = UpperSecond , clear
		 * Lvl6 = UpperSecond , borderline
		 * Expect Degree = UpperSecond
		 */
		int [] year2Student1 = {89, 67, 90, 71};
		int [] year3Student1 = {45, 44, 65, 67};
 
		clearGrades();

		for (int percentage: year2Student1) {
			yr2Grades.add(Grade.fromPercentage(percentage));
		}
 
		for (int percentage: year3Student1) {
			yr3Grades.add(Grade.fromPercentage(percentage));
		}

		Degree student1 = new Degree(yr2Grades, yr3Grades);
		
		System.out.println("Student 1:");
		System.out.println(student1);
		System.out.println("Degree Classification: "+ student1.classify());
		System.out.println(" ");
		
		
		/*Student 2
		 * Lvl5 = UpperSecond , borderline
		 * Lvl6 = Third 
		 * Expect Degree = Discretion*/
		int [] year2Student2 = {89, 67, 90, 71};
		int [] year3Student2 = {45, 44, 43, 67};
 
		clearGrades();
		
		for (int percentage: year2Student2) {
			yr2Grades.add(Grade.fromPercentage(percentage));
		}
 
		for (int percentage: year3Student2) {
			yr3Grades.add(Grade.fromPercentage(percentage));
		}

		Degree student2 = new Degree(yr2Grades, yr3Grades);
		
		System.out.println("Student 2:");
		System.out.println(student2);
		System.out.println("Degree Classification: "+ student2.classify());
		System.out.println(" ");
		
		
		/*Student 3
		 * Lvl5 = LowerSecond , clear
		 * Lvl6 = LowerSecond , clear
		 * Expect Degree = LowerSecond*/
		int [] year2Student3 = {57, 55, 53, 50};
		int [] year3Student3 = {59, 56, 54, 51};
 
		clearGrades();
		
		for (int percentage: year2Student3) {
			yr2Grades.add(Grade.fromPercentage(percentage));
		}
 
		for (int percentage: year3Student3) {
			yr3Grades.add(Grade.fromPercentage(percentage));
		}

		Degree student3 = new Degree(yr2Grades, yr3Grades);
		
		System.out.println("Student 3:");
		System.out.println(student3);
		System.out.println("Degree Classification: "+ student3.classify());
		System.out.println(" ");
		
		/*Student 4
		 * Lvl5 = Third , clear
		 * Lvl6 = Third , clear
		 * Expect Degree = Third*/
		int [] year2Student4 = {47, 45, 43, 40};
		int [] year3Student4 = {49, 46, 44, 41};
 
		clearGrades();
		
		for (int percentage: year2Student4) {
			yr2Grades.add(Grade.fromPercentage(percentage));
		}
 
		for (int percentage: year3Student4) {
			yr3Grades.add(Grade.fromPercentage(percentage));
		}

		Degree student4 = new Degree(yr2Grades, yr3Grades);
		
		System.out.println("Student 4:");
		System.out.println(student4);
		System.out.println("Degree Classification: "+ student4.classify());
		System.out.println(" ");
		
		
		/*Student 5
		 * Performs better in year 3
		 * Lvl5 = UpperSecond , clear
		 * Lvl6 = First , clear
		 * Expect Degree = First
		 * */
		int [] year2Student5 = {69, 65, 64, 63}; // All UpperSecond
		int [] year3Student5 = {75, 70, 80, 65}; // All First
 
		clearGrades();
		
		for (int percentage: year2Student5) {
			yr2Grades.add(Grade.fromPercentage(percentage));
		}
 
		for (int percentage: year3Student5) {
			yr3Grades.add(Grade.fromPercentage(percentage));
		}

		Degree student5 = new Degree(yr2Grades, yr3Grades);
		
		System.out.println("Student 5:");
		System.out.println(student5);
		System.out.println("Is lvl6 better? "+ student5.getLvl6Profile().compareTo(student5.getLvl5Profile()));
		System.out.println("Degree Classification: "+ student5.classify());
		System.out.println(" "); 

		/*Student 6
		 * Performs better in year 2
		 * Lvl5 = First , clear
		 * Lvl6 = UpperSecond , clear
		 * Expect Degree = First
		 * */ 
		int [] year2Student6 = {79, 90, 85, 72}; // All First
		int [] year3Student6 = {64, 67, 65, 61}; // All UpperSecond
 
		clearGrades();
		
		for (int percentage: year2Student6) {
			yr2Grades.add(Grade.fromPercentage(percentage));
		}
 
		for (int percentage: year3Student6) {
			yr3Grades.add(Grade.fromPercentage(percentage));
		}

		Degree student6 = new Degree(yr2Grades, yr3Grades);
		
		System.out.println("Student 6:");
		System.out.println(student6);
		System.out.println("Degree Classification: "+ student6.classify());
		System.out.println(" ");
		
		/*Student 7
		 * Discretion example - 2 classes difference
		 * * Lvl5 = First , clear
		 * Lvl6 = LowerSecond , clear
		 * Expect Degree = Discretion
		 * */ 
		int [] year2Student7 = {90, 83, 80, 78}; 
		int [] year3Student7 = {55, 54, 53, 53};
 
		clearGrades();
		
		for (int percentage: year2Student7) {
			yr2Grades.add(Grade.fromPercentage(percentage));
		}
 
		for (int percentage: year3Student7) {
			yr3Grades.add(Grade.fromPercentage(percentage));
		}

		Degree student7 = new Degree(yr2Grades, yr3Grades);
		
		System.out.println("Student 7:");
		System.out.println(student7);
		System.out.println("Degree Classification: "+ student7.classify());
		System.out.println(" ");
		
		/*Student 8
		 * Discretion example - Higher profile is borderline
		 * Lvl5 = UpperSecond , borderline
		 * Lvl6 = Third , clear
		 * Expect Degree = Discretion
		 * 
		 * */ 
		int [] year2Student8 = {69, 68, 67, 65}; 
		int [] year3Student8 = {66, 40, 45, 43};
 
		clearGrades();
		
		for (int percentage: year2Student8) {
			yr2Grades.add(Grade.fromPercentage(percentage));
		}
 
		for (int percentage: year3Student8) {
			yr3Grades.add(Grade.fromPercentage(percentage));
		}

		Degree student8 = new Degree(yr2Grades, yr3Grades);
		
		System.out.println("Student 8:");
		System.out.println(student8);
		System.out.println("Degree Classification: "+ student8.classify());
		System.out.println(" ");
		
 
	}

}
