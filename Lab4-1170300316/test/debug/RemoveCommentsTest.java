package debug;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RemoveCommentsTest {

	@Test
	void ExampleTest() {
		RemoveComments rc = new RemoveComments();
		String[] input = new String[] {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
		String[] output = new String[] {"int main()","{ ","  ","int a, b, c;","a = b + c;","}"};
		List<String> op = new ArrayList<String>();
		for (int i = 0; i < output.length; i++) {
			op.add(output[i]);
		}
		System.out.println(op.size() + " " + rc.removeComments(input).size());
		for (int i = 0; i < output.length; i++) {
			assertTrue(op.get(i).equals(rc.removeComments(input).get(i)));
		}
		
	}
}
