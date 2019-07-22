package exception;

public class FalseDependencyException extends Exception{

	/*
	 * Atom Structure Prevent electrons being add to a track that does not exist
	 */

	/*
	 * Social Network Circle Prevent adding a friend to a social tile before
	 * declaring it in friends
	 */

	private String name;

	public FalseDependencyException() {

	}

	public FalseDependencyException(String name) {
		this.name = new String(name);
	}
	
	public String GetName() {
		return new String(this.name);
	}
	
	@Override
	public String getMessage() {
		return "The object " + GetName() + " has a false dependency relation.";
	}
}
