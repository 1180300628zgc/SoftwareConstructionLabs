import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParseTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = new String("Idontknow<what>");
		Pattern regex = Pattern.compile("<.*?>");
		Matcher m = regex.matcher(string);
		System.out.println(m.find());
	}

}
