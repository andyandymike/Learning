package test.andy.hello;

public class Sin1 {
	
	
	
	private Sin1() {}
	
	public static final Sin1 getInstance() {
		return InstanceHolder.instance;
	}
	
	private static class InstanceHolder {
		public static Sin1 instance = new Sin1();
	}

}
