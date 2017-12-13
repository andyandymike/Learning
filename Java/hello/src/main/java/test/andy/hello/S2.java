package test.andy.hello;

public class S2 {
	
	private S2() { }
	
	public static final S2 getinstance() {
		return Holder.instance;
	}
 	
	private static class Holder {
		private static S2 instance = new S2();
	}

}
