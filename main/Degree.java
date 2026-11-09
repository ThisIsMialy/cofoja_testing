package main;
import java.util.*;
import java.lang.Math;

public class Degree {
	// Your additions/changes below this line

	private Profile lvl5Profile; // has 8 grades
	private Profile lvl6Profile; // has 4 grades

	
	public Profile getLvl5Profile() {
		return lvl5Profile;
	}

	public Profile getLvl6Profile() {
		return lvl6Profile;
	}

	public Degree(List<Grade> year2, List<Grade> year3) {
		if (year2 != null) {
			if (year3 != null) {
				if (year2.size() != 4) {
					throw new IllegalArgumentException();
				}
				else if (year3.size() != 4 ) {
					throw new IllegalArgumentException(); 
				}
				else {
					checkForFailGrades(year2);
					checkForFailGrades(year3);
				}
			} else throw new IllegalArgumentException();
		} else throw new IllegalArgumentException();


		List<Grade> mergedGrades = new ArrayList<Grade>();
		mergedGrades.addAll(year2);
		mergedGrades.addAll(year3);
		this.lvl6Profile = new Profile(year3);
		this.lvl5Profile = new Profile(mergedGrades);

	}

	private void checkForFailGrades(List<Grade> grades) {
		for (Grade i: grades) {
			if (i.classify() == Classification.Fail) {
				throw new IllegalArgumentException();
			}
		}	
	}

	public Classification classify() { 
		Classification degreeClass = null;

		int ordinalDiff = Math.abs(lvl5Profile.getClassificationOrdinal() 
				- lvl6Profile.getClassificationOrdinal());

		// if 0 then equals
		if (lvl5Profile.compareTo(lvl6Profile) == 0) {
			degreeClass = lvl5Profile.classify();
		}
		// if level5 is better 
		if (lvl5Profile.compareTo(lvl6Profile) == 1) {
			if (lvl5Profile.isClear() && ordinalDiff < 2) {
				degreeClass = lvl5Profile.classify();
			}
			else degreeClass = Classification.Discretion;
		} 
		// if level 6 is better 
		if (lvl6Profile.compareTo(lvl5Profile) == 1) {
			if (lvl6Profile.isClear() && ordinalDiff < 2) {
				degreeClass = lvl6Profile.classify();
			}
			else degreeClass = Classification.Discretion;
		}

		return degreeClass;
	}
	
	@Override
	public String toString() {
		return "Degree [lvl5Profile=" + getLvl5Profile() + ", lvl6Profile=" + getLvl6Profile() + ", classify()=" + classify() + "]";
	}
}
