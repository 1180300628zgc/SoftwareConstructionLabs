package debug;

///*
//
//This program is used for removing all the comments in a program code.
//
//Example 1:
//Input: 
//source = ["/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"]
//
//The line by line code is visualized as below:
//
///*Test program */
//int main()
//{ 
//  // variable declaration 
//int a, b, c;
///* This is a test
//   multiline  
//   comment for 
//   testing */
//a = b + c;
//}
//
//Output: ["int main()","{ ","  ","int a, b, c;","a = b + c;","}"]
//
//The line by line code is visualized as below:
//
//int main()
//{ 
//
//int a, b, c;
//a = b + c;
//}
//
//Explanation: 
//The string /* denotes a block comment, including line 1 and lines 6-9. The string // denotes line 4 as comments.
//
//Example 2:
//
//Input: 
//source = ["a/*comment", "line", "more_comment*/b"]
//
//Output: ["ab"]
//
//Explanation: 
//The original source string is "a/*comment\nline\nmore_comment*/b", where we have bolded the newline characters.  
//After deletion, the implicit newline characters are deleted, leaving the string "ab", which when delimited by newline characters becomes ["ab"].
//
//
//Note:
//
//The length of source is in the range [1, 100].
//The length of source[i] is in the range [0, 80].
//Every open block comment is eventually closed.
//There are no single-quote, double-quote, or control characters in the source code.
//
//*/


import java.util.ArrayList;
import java.util.List;

class RemoveComments {
    public List<String> removeComments(String[] source) {
        boolean inBlock = false;
        StringBuilder newline = new StringBuilder();
        // List can not be initialized
        List<String> ans = new ArrayList<String>();
        for (String line: source) {
            int i = 0;
            char[] chars = line.toCharArray();
            if (!inBlock) 
            	newline = new StringBuilder();
            // In case of line of 1 char, which would not run the while loop
            if (line.length() == 1) {
				newline.append(chars[0]);
			}
            // Array index out of bounds exception
            while (i < line.length() - 1) {
            	if (!inBlock && i+1 <= line.length() && chars[i] == '/' && chars[i+1] == '*') {
                    inBlock = true;
                    // System.out.println("First if");
                } else if (inBlock && i+1 <= line.length() && chars[i] == '*' && chars[i+1] == '/') {
                    inBlock = false;
                    // System.out.println("Second if");
                } else if (!inBlock) {
                	if (chars[i] == '/' && chars[i+1] == '/') {
                		i = line.length() - 1;
						continue;
					}
                    newline.append(chars[i]);
                    if (i == line.length() - 2) {
                    	newline.append(chars[i + 1]);
					}
                    // System.out.println("Third if");
                    // System.out.println("Newline " + newline);
                }
                i++;
            }
            // if (inBlock && newline.length() > 0) {
            if (newline.length() > 0) {
                ans.add(new String(newline));
                // System.out.println("ans " + ans);
            }
        }
        System.out.println(ans);
        return ans;
    }
}