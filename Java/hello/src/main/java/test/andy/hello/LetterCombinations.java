package test.andy.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinations {
	
	private Map<String, String[]> digitsTable;
	
	private String[] toStringArray(String input) {
		char[] tInput = input.toCharArray();
		String[] output = new String[tInput.length];
		for (int i = 0; i < tInput.length; i++) {
    		output[i] = String.valueOf(tInput[i]);
    	}
		return output;
	}
	
	public LetterCombinations() {
		digitsTable = new HashMap<String, String[]>();
		digitsTable.put("2", toStringArray("abc"));
		digitsTable.put("3", toStringArray("def"));
		digitsTable.put("4", toStringArray("ghi"));
		digitsTable.put("5", toStringArray("jkl"));
		digitsTable.put("6", toStringArray("mno"));
		digitsTable.put("7", toStringArray("pqrs"));
		digitsTable.put("8", toStringArray("tuv"));
		digitsTable.put("9", toStringArray("wxyz"));
	}
	
    public List<String> letterCombinations(String digits) {
    	int length = digits.length();
    	
        List<String> output = new ArrayList<String>();
        
        if (length == 0) {
        	return output;
        }
        
        List<String>[] temp = new List[length];
        
        String[] value = digitsTable.get(String.valueOf(digits.charAt(0)));
        temp[0] = new ArrayList<String>();
    	for (String item : value) {
    		temp[0].add(item);
    	}
        
        if (length == 1) {
        	return temp[0];
        }
        
        for (int i = 1; i < length; i++) {
        	String[] tableValues = digitsTable.get(String.valueOf(digits.charAt(i)));
        	temp[i] = new ArrayList<String>();
        	for(String tableValue : tableValues) {
        		for (String item : temp[i - 1]) {
            		temp[i].add(item + tableValue);
            	}
        	}
        }
        
        return temp[length - 1];
    }

}
