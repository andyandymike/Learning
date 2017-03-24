package test1;

public class testrun {
	public static void main(String[] args) {  
		mythread A = new mythread("A");
		new Thread(A).start();
		new Thread(A).start();
		new Thread(A).start();
	}
}
