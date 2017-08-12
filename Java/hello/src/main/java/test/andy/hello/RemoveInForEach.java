package test.andy.hello;

import java.util.ArrayList;
import java.util.List;

public class RemoveInForEach {

	public static void main(String[] args) {
		List<String> a = new ArrayList<String>();
		a.add("1");
		a.add("2");

		for(String temp : a) {
			if("2".equals(temp)) {
				a.remove(temp);
			}
		}
		
		for(String temp : a) {
			System.out.println(temp);
		}
	}

}
