import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class validParentheses {

	public static void main(String[] args) throws IOException {
		//read input file
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String strLine;
		
		//read the first line
		strLine = br.readLine();
		
		
		
		while((strLine = br.readLine()) != null) {
			int len = strLine.length();
			char [] myStack = new char [len];	//simulate a stack
			int top = -1;						//stack top
			boolean flag = true;
			
			//read string character by character
			for(int i=0; i<len; ++i) {
				if(strLine.charAt(i) == '(' || strLine.charAt(i) == '[' || strLine.charAt(i) == '{') {
					//push stack
					top++;
					myStack[top] = strLine.charAt(i);
				}else if(strLine.charAt(i) == ')' || strLine.charAt(i) == ']' || strLine.charAt(i) == '}'){
					//check the top of stack
					if(strLine.charAt(i) == ')' && top>=0 && myStack[top] == '(') {
						//pop stack
						myStack[top] = ' ';
						top--;
					}else if(strLine.charAt(i) == ']' && top>=0 && myStack[top] == '[') {
						//pop stack
						myStack[top] = ' ';
						top--;
					}else if(strLine.charAt(i) == '}' && top>=0 && myStack[top] == '{') {
						//pop stack
						myStack[top] = ' ';
						top--;
					}else {
						//not a matching parenthesis, check the next line
						flag = false;
						break;
					}
				}
			}
			
			if(flag == true && top == -1)
				flag = true;
			else
				flag = false;
			
			if(flag)
				System.out.println("TRUE");
			else
				System.out.println("FALSE");
			
		}
	}

}
