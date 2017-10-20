package test.andy.hello;

public class RomanToInt {
	
	private int dict(char input) {
		switch(input) {
		case 'I':
			return 1;
		case 'X':
			return 10;
		case 'C':
			return 100;
		case 'M':
			return 1000;
		case 'V':
			return 5;
		case 'L':
			return 50;
		case 'D':
			return 500;
		default:
			return 0;
		}	
	}
	
    public int romanToInt(String s) {
    	char[] input = s.toCharArray();
    	int length = input.length;
    	int output = dict(input[length - 1]);
    	
    	if (length == 1) {
    		return output;
    	}
    	
    	for (int i = length - 1; i > 0; i--) {
    		if (dict(input[i]) <= dict(input[i - 1])) {
    			output += dict(input[i - 1]);
    		} else {
    			output -= dict(input[i - 1]);
    		}
    	}
    	
        return output;
    }

}
