package test.andy.hello;

public interface TestInterface {
	
	public void test1(int input);
	
	default public void test(int input) {
		System.out.println(input);
	}
	default public void test2(int input) {
		System.out.println(input);
	}

}
