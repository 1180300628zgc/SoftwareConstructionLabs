package exception;

public class MissingObjectException extends Exception{
	
	private String name;
	
	public MissingObjectException() {
		
	}
	
	public MissingObjectException(String string) {
		this.name = new String(string);
	}
	
	public String GetName() {
		return new String(this.name);
	}
}
