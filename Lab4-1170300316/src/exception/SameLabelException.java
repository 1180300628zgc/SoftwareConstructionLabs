package exception;

public class SameLabelException extends Exception {

	/*
	 * Stellar System
	 * No two planets share the same label (name)
	 */
	
	/*
	 * Social Network Circle
	 * No social tile connects two same friends (name)
	 */
	
	private String string;

	public SameLabelException() {

	}

	public SameLabelException(String string) {
		this.string = new String(string);
	}

	public String GetLabel() {
		return new String(this.string);
	}
	
	@Override
	public String getMessage() {
		return "The label " + GetLabel() + " have been recorded.";
	}
}
