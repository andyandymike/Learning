package test.andy.hello;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SchedulePool {

	public static void main(String[] args) throws InterruptedException {
		  ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
		  executor.scheduleAtFixedRate(new TaskThread("Andy"), 0, 10, TimeUnit.SECONDS);
		  //executor.scheduleWithFixedDelay(new TaskThread("Zoe"), 2, 3, TimeUnit.SECONDS);
		  //executor.submit(new TaskThread("Andy"));
		  Thread.sleep(10000);
		  executor.shutdown();
	}

}
