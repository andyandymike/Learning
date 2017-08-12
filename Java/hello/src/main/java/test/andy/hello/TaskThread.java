package test.andy.hello;

import java.time.LocalTime;

public class TaskThread extends Thread {
	final String name;
	
	public TaskThread(String name) {
		this.name = name;
	}
	
	public void run() {
		for(int i = 0; i < 5; i++) {
			System.out.println("First hello " + name + ' ' + LocalTime.now());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.println("Seconde hello " + name + ' ' + LocalTime.now());
		}
	}


}
