package test.andy.hello;

public class Fib {
	
	public static int noN(int n) {
		int one = 0;
		int two = 1;
		int result = Integer.MIN_VALUE;
		for (int i = 2; i < n; i++) {
			result = one + two;
			one = two;
			two = result;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(noN(5));

	}

}
