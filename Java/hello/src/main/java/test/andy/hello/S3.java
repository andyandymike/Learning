package test.andy.hello;

public class S3 {
	private volatile static S3 instance;
	
	private S3() { }
	
	public static final S3 getInstance() {
		if (instance == null) {
			synchronized(S3.class) {
				if (instance == null) {
					instance = new S3();
				}
			}
		}
		return instance;
	}
 

}
