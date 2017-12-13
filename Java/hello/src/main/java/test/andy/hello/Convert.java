package test.andy.hello;

public class Convert {
	
	private char[] istr;
	
	private boolean isPalindrome(int start, int end) {
		for (int i = start, j = end; i <= j; i++, j--) {
			if (istr[i] != istr[j]) {
				return false;
			}
		}
		return true;
	}
	
    public String convertPalindrome(String str) {
        StringBuilder output = new StringBuilder();
        int length = str.length();
        int start = 1;
        istr = str.toCharArray();
        
        if (isPalindrome(0, length - 1)) {
        	return str;
        }
        
        for (int i = length - 1; i > 0; i--) {
        	if (isPalindrome(0, i)) {
        		start = i + 1;
        		break;
        	}
        	start = i;
        }

        for (int i = start; i < length; i++) {
        	output.insert(0, istr[i]);
        }
        output.append(str);
        
        return output.toString();
    }

}
