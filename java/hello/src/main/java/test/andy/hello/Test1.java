package test.andy.hello;

import java.util.Deque;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Test1 {
	public static void main(String[] args) {
		Father f = new Son(){
			public void testover() {
				return;
			}
		};
		
		float f1 = (float) 3.4;
		String aaa = "a,b,c,d";
		aaa.split(",");
		Deque<String> aaal = new LinkedList<String>();
		
		try {
			Class.forName("Test1").newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
