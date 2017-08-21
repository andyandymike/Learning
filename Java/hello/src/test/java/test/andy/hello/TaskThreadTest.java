package test.andy.hello;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

public class TaskThreadTest {

  @Test
  public void TaskThread() throws InterruptedException {
	  ScheduledExecutorService executor = Executors.newScheduledThreadPool(3);
	  executor.scheduleAtFixedRate(new TaskThread("Andy"), 0, 2, TimeUnit.SECONDS);
	  //executor.scheduleWithFixedDelay(new TaskThread("Zoe"), 2, 3, TimeUnit.SECONDS);
	  //executor.submit(new TaskThread("Andy"));
	  Thread.sleep(100000);
	  executor.shutdown();
  }
}
