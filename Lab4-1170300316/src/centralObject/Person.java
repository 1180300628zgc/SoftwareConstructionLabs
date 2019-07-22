package centralObject;

public class Person extends CentralObject{

	private int age;
	private String gender; 
	
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public Person(String string, int age, String gender) {
		this.name = new String(string);
		this.age = age;
		this.gender = gender;
	}
}
