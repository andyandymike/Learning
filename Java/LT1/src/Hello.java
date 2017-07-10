import java.util.ArrayList;
import java.util.List;

public class Hello {
	
	public static void main(String[] args) {
		char[] charArray = "Hello I am a student.".toCharArray();
		
		CharArrayFunc charArrayFunc = new CharArrayFunc(charArray);
		System.out.println(charArrayFunc.reverseCharArray());
	}

}
