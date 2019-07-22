package physicalObject;

public class Athlete extends PhysicalObject{
	private int number;
	private String nationality;
	private int age;
	private float bestOfTheYear;
	
	// Immutability:
	// all reps are set to private and no method could change them
	// RI:
	// Name is a word. Number and age are two
	// positive integer. Nationality consists
	// of three capital characters. bestOfTheYear
	// is two integers and two decimals
	// AF:
	// e.g. Athlete ::= <Bolt,1,JAM,38,9.88>
	// Safety from rep exposure:
	// set all the rep to private 
	
	public Athlete(String name, int number, String nationality, float bestOfTheYear) {
		this.name = name;
		this.number = number;
		this.nationality = new String(nationality);
		this.bestOfTheYear = bestOfTheYear;
	}
	
	/**
	 * get the best speed of the athlete
	 * @return best speed of the year
	 */
	public float GetSpeed() {
		return bestOfTheYear;
	}
	
	/**
	 * get the age of the athlete
	 * @return age
	 */
	public int GetAge() {
		return age;
	}
	
	/**
	 * get the nationality of the athlete
	 * @return nationality
	 */
	public String GetNationality() {
		return new String(nationality);
	}
	
	/**
	 * get the number of the athlete
	 * @return number
	 */
	public int GetNumber() {
		return number;
	}
}
