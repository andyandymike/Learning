package test.andy.hello;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ForTest {
	
	private static int cal(int input) {
		input = Math.abs(input) + 2;
		return input;
	}

	public static void main(String[] args) {

		System.out.println(System.currentTimeMillis());
		
		for (int j = 0; j < 1000000; j++) {
			
		}
		
		int result = 0;
		for (int i = 1; i < 1000; i++) {
			result += cal(i);
		}
		result = 0;
		for (int i = 1; i < 1000; i++) {
			result += cal(i);
		}
		result = 0;
		for (int i = 1; i < 1000; i++) {
			result += cal(i);
		}
		result = 0;
		for (int i = 1; i < 1000; i++) {
			result += cal(i);
		}
		result = 0;
		for (int i = 1; i < 1000; i++) {
			result += cal(i);
		}
		
		System.out.println(System.currentTimeMillis());
	}

}
