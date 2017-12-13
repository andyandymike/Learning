package test.andy.hello;

public class S1 {
	
	private static final S1 instance = new S1();
	
	private S1 () { }
	
	public static final S1 getinstance() {
		return instance;
	}
}
