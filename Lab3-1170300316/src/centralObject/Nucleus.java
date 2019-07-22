package centralObject;

public class Nucleus extends CentralObject {
	
	public Nucleus(String string) {
		this.name = new String(string);
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
