package test.andy.hello;

import java.util.Stack;

public class ExpressionExpand {

	public static String expressionExpand(String s) {
		
		Stack<String> stringStack = new Stack<String>();
		Stack<Integer> intStack = new Stack<Integer>();
		
		int arraylength = s.length();
		String output = "";
		String num = "";
		
		for(int i = 0; i < arraylength; i++) {
			String sc = String.valueOf(s.charAt(i));
			try {
				Integer tempnum = Integer.parseInt(sc);
				num = num + tempnum;
			} catch(NumberFormatException e) {
				if(!num.equals("")) {
					intStack.push(Integer.parseInt(num));
					num = "";
				}
				if(sc.equals("]")) {
					String popString = stringStack.pop();
					String tempString = "";
					while(!popString.equals("[")) {
						tempString = popString + tempString;
						popString = stringStack.pop();
					}
					Integer count = intStack.pop();
					for(int j = 0; j < count; j++) {
						stringStack.push(tempString);
					}
					continue;
				}
				
				stringStack.push(sc);
			}
		}
		
		while(!stringStack.isEmpty()) {
			output = stringStack.pop() + output;
		}
		
		return output;
    }

}
