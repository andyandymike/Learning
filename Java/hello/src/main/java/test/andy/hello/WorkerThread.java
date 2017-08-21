package test.andy.hello;

public class WorkerThread extends Thread {
	TaskQueue taskQueue;
	
	public WorkerThread(TaskQueue taskQueue) {
		this.taskQueue = taskQueue;
	}
	
	public void run() {
		while(!isInterrupted()) {
			String name;
			try {
				name = taskQueue.getTask();
			} catch (InterruptedException e) {
				break;
			}
			String result = "hello " + name;
			System.out.println(result);
		}
	}

}
