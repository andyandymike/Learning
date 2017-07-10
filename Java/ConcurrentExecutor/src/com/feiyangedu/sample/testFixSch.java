package com.feiyangedu.sample;

import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

class FS implements Runnable {
	String name;

	public FS(String name) {
		this.name = name;
	}

	public void run() {
		System.out.println("Hello, " + name + "! It is " + LocalTime.now());
		try {
			Thread.sleep(1000);
			throw new IllegalArgumentException();
		} catch (InterruptedException e) {
		}
		System.out.println("Goodbye, " + name + "! It is " + LocalTime.now());
	}
}

public class testFixSch {

	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
		executor.scheduleAtFixedRate(new FS("Bob"), 2, 4, TimeUnit.SECONDS);
		executor.scheduleAtFixedRate(new FS("Alice"), 2, 2, TimeUnit.SECONDS);
	}

}
