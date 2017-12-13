package test.andy.hello;

import java.util.Random;

public class Birth {
	
	public static int maleCount = 0;
	public static int femaleCount = 0;
	
	public static void birthMale() {
		maleCount++;
	}
	
	public static void birthFemale() {
		femaleCount++;
	}
	
	public static void main(String[] args) { 
		Random r = new Random();
		for (int i = 0; i < 10000; i++) {
			boolean male = r.nextBoolean();
			while (male) {
				birthMale();
				male = r.nextBoolean();
			}
			birthFemale();
		}
		System.out.println(maleCount);
		System.out.println(femaleCount);
	}
	
}
