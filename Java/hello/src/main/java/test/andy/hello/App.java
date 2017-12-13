package test.andy.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Hello world!
 *
 */
public class App {
	static {
		System.out.println("Top");
	}

	public static void main(String[] args) {
		List<String> testVect = new Vector<String>();
		testVect.add("a");
		Map<String, String> map = new ConcurrentHashMap<String, String>();
		Set<String> set = new HashSet<String>();
		Vector<String> vector = new Vector<String>();
		
		App.class.getDeclaredMethods();
	}

	static {
		System.out.println("Bottom");
	}
}
