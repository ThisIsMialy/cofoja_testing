package main;
public class Grade {
	private final int points;

	public int getPoints() {
		return points;
	}

	public Grade(int p) throws IllegalArgumentException {
		if(p<1 || p>20) 
			throw new IllegalArgumentException();
		points = p;
	}
	
	// Your additions/changes below this line

	
	/*
	 * Returns a Classification of the current grade object
	 * that is stored the points field 
	 */
	public Classification classify() {
		Classification classification = null;
		
		// store the 20 point-scale in a 2D array 
		int [][] grades = new int [][]{
			{1, 2, 3, 4}, 
			{5, 6, 7, 8},
			{9, 10, 11, 12},
			{13, 14, 15, 16},
			{17, 18, 19, 20}
		};
		
		
		for (int i = 0; i < grades.length; i++) {
			for (int j = 0; j < grades[i].length; j++) {
				if (this.getPoints() == grades[i][j]){
					if (i == 0) {
						classification = Classification.First;
					}
					if (i == 1) {
						classification = Classification.UpperSecond;
					}
					if (i == 2) {
						classification = Classification.LowerSecond;
					}
					if (i == 3) {
						classification = Classification.Third;
					}
					if (i == 4) {
						classification = Classification.Fail;
					}
				}
			}
		}
		return classification;
	}
	
	public static Grade fromPercentage(int g) throws IllegalArgumentException {
		int point = 0;
		
		if (g<-1 || g>100) {
			throw new IllegalArgumentException();
		}
		if (g == -1){
			point = 20; 
		}
		if (g > 78){
			point = 1;
		}
		if (g > 75 && g < 79){
			point = 2;
		}
		if (g > 72 && g < 76){
			point = 3;
		}
		if (g > 69 && g < 73){
			point = 4;
		}
		if (g > 66 && g < 70){
			point = 5;
		}
		if (g > 64 && g < 67){
			point = 6;
		}
		if (g > 61 && g < 65){
			point = 7;
		}
		if (g > 59 && g < 62){
			point = 8;
		}
		if (g > 56 && g < 60){
			point = 9;
		}
		if (g > 54 && g < 57){
			point = 10;
		}
		if (g > 51 && g < 55){
			point = 11;
		}
		if (g > 49 && g < 52){
			point = 12;
		}
		if (g > 46 && g < 50){
			point = 13;
		}
		if (g > 44 && g < 47){
			point = 14;
		}
		if (g > 41 && g < 45){
			point = 15;
		}
		if (g > 39 && g < 42){
			point = 16;
		}
		if (g > 34 && g < 40){
			point = 17;
		}
		if (g > 29 && g < 35){
			point = 18;
		}
		if (g > -1 && g < 30){
			point = 19;
		}
		return new Grade(point);
		
	}
}
