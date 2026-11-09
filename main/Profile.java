package main;
import java.util.*;

public class Profile implements Comparable<Profile>{ 
	// Your additions/changes below this line

	private List<Grade> gradeList;
	private int halfListAmount;
	private int quarterListAmount;
	private int firstCount = 0;
	private int upperSecondCount = 0;
	private int lowerSecondCount = 0;
	private int thirdCount = 0;

	public List<Grade> getGrades() {
		return this.gradeList;
	}
	
	public int getProfileSize() {
		return this.gradeList.size();
	}

	public Profile(List<Grade> g) {
		if (g != null) { 
			if (g.isEmpty()) {
				throw new IllegalArgumentException();
			} 
			else {
				for (Grade i: g) {
					if (i.classify() == Classification.Fail)
						throw new IllegalArgumentException();
				}
			}
		}
		else throw new IllegalArgumentException();
		
		this.gradeList = new ArrayList<Grade>();
		this.gradeList = g;
		this.halfListAmount = this.gradeList.size() /2;
		this.quarterListAmount = this.gradeList.size() / 4;
		this.countClassifications();
	}

	/* 
	 * Count the amount of each classification in the profile
	 */
	public void countClassifications() {
		for(Grade grade: gradeList) {
			if (grade.classify() == Classification.First)
				firstCount++;
			if (grade.classify() == Classification.UpperSecond)
				upperSecondCount++;
			if (grade.classify() == Classification.LowerSecond)
				lowerSecondCount++;
			if (grade.classify() == Classification.Third)
				thirdCount++;	
		}
	}

	public Classification classify() {
		Classification profileClassification = null;
		
		int upperSecondAndAbove = upperSecondCount + firstCount;
		int lowerSecondAndAbove = lowerSecondCount + upperSecondAndAbove;
		
		if (firstCount >= halfListAmount) 
			profileClassification = Classification.First;
		else if (upperSecondAndAbove >= halfListAmount)
			profileClassification = Classification.UpperSecond;
		else if (lowerSecondAndAbove >= halfListAmount) 
			profileClassification = Classification.LowerSecond;
		else 
			profileClassification = Classification.Third;
 
		return profileClassification;
	}

	public boolean isClear() {
		boolean clearFlag = true;
		if (this.classify() == Classification.First || this.classify() == Classification.UpperSecond) {
			if (thirdCount > this.quarterListAmount)
				clearFlag = false;
			else clearFlag = true;
		}
		return clearFlag;
	}
	
	/*
	 * Get ordinal from Enum, First is highest (5) to Discretion (0)
	 */
	public int getClassificationOrdinal() {
		return this.classify().ordinal();
	}

	@Override
	public int compareTo(Profile otherProfile) {
		int thisOrdinal = this.classify().ordinal();
		int otherOrdinal = otherProfile.classify().ordinal();
		
		if (thisOrdinal == otherOrdinal) {
			return 0;
		}
		if (thisOrdinal > otherOrdinal) {
			return 1;
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return "Profile [classify()=" + classify()
				+ ", isClear()=" + isClear() + "]";
	}


	
}
