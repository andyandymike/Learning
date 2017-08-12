package test.andy.hello;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaskQueue {
	final Lock lock = new ReentrantLock();
	final Condition notEmpty = lock.newCondition();
	final Queue<String> queue = new LinkedList<>();
	
	public String getTask() throws InterruptedException {
		lock.lock();
		try {
			while(this.queue.isEmpty()) {
				notEmpty.await();
			}
			return queue.remove();
		} finally {
			lock.unlock();
		}
	}
	
	public void addTask(String name) {
		lock.lock();
		try {
			this.queue.add(name);
			notEmpty.signalAll();
		} finally {
			lock.unlock();
		}
	}

}
