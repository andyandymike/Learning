package test.andy.hello;

public class Power {
	
	public static double pow(double base, int exp) throws mathException {
		if (new Double(base).equals(0.0) && exp <= 0) {
			throw new mathException();
		}
		double output = 1.0;
		for (int i = 0; i < Math.abs(exp); i++) {
			output *= base;
		}
		if (exp < 0) {
			output = 1.0 / output;
		}
		return output;
	}

	public static void main(String[] args) {
		try {
			System.out.println(pow(0, -8));
		} catch (mathException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

class mathException extends Exception {
	
}
