package exception;

public class IllegalNumberException extends Exception {

	/*
	 * Stellar System
	 * Function 1: Degree should be in the range [0, 360)
	 * Function 2: If number is larger than 10000, use scientific number
	 * Function 3: If number is smaller than 10000, don't use scientific number
	 * Function 4: Velocity should be above 0
	 * Function 5: Planet radius should be smaller than radius
	 */
	
	/*
	 * Atom Structure
	 * Function 1: Total track number should be above 0 and int
	 * Function 2: Track number should not be larger than total track
	 * Function 3: Electron number should be above 0 and int
	 * Function 4: Track number should be int
	 * Function 5: Empty track should not exist
	 */
	
	/*
	 * Social Network Circle
	 * Function 1: Central user Age should be above 0 and int
	 * Function 2: Social Tile should be in range (0, 1]
	 * Function 3: Friend Age should be above 0 and int
	 * Function 4: Social Tile should not has more than 3 digits
	 * Function 5: 
	 */
	
	private double number;
	private int intnumber;

	public IllegalNumberException() {

	}

	public IllegalNumberException(double number) {
		this.number = number;
	}

	public IllegalNumberException(int number) {
		this.intnumber = number;
	}
	
	public double GetNumber() {
		return this.number;
	}
	
	public int GetIntNumber() {
		return this.intnumber;
	}
	
	@Override
	public String getMessage() {
		return "The number " + GetNumber() + " is illegal.";
	}
}
