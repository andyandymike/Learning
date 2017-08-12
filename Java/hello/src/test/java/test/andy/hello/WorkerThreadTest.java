package test.andy.hello;

import org.testng.annotations.Test;

public class WorkerThreadTest {

  @Test
  public void WorkerThread() throws InterruptedException {
    TaskQueue taskQueue = new TaskQueue();
    WorkerThread worker = new WorkerThread(taskQueue);
    worker.start();
    taskQueue.addTask("Andy");
    Thread.sleep(1000);
    taskQueue.addTask("Bob");
    Thread.sleep(1000);
    taskQueue.addTask("Cathy");
    Thread.sleep(1000);
    taskQueue.addTask("Daniel");
    Thread.sleep(1000);
    taskQueue.addTask("Echo");
    worker.interrupt();
    worker.join();
    System.out.println("End");
  }
}
