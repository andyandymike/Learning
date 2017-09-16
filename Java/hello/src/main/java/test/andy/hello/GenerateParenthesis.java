package test.andy.hello;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GenerateParenthesis {
	
	private static void removeDepulicate(List input) {
		HashSet hs = new HashSet(input);
		input.clear();
		input.addAll(hs);
	}
	
    public List<String> generateParenthesis(int n) {
    	List<String> output = new ArrayList<String>();
    	if (n == 1) {
    		output.add("()");
    		return output;
    	}
    	for ( String item : generateParenthesis(n - 1)) {
    		output.add("(" + item + ")");
    		if ( "()" + item == item + "()") {
    			output.add("()" + item);
    		} 
    		else {
    			output.add("()" + item);
    			output.add(item + "()");
    		}
    	}
    	removeDepulicate(output);
        return output;
    }
}
