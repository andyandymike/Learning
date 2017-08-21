package test.andy.hello;

import java.util.Random;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Long> {
	
	static final int THRESHOLD = 100;
	long[] array;
	int start;
	int end;
	
	SumTask(long[] array, int start, int end) {
		this.array = array;
		this.start = start;
		this.end = end;
	}

	@Override
	protected Long compute() {
		if(end - start < THRESHOLD) {
			long sum = 0;
			for(int i = start; i < end; i++) {
				sum += array[i];
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
			System.out.println(String.format("compute %d~%d = %d", start, end, sum));
			return sum;
		}
		
		int middle = (end + start)/2;
		System.out.println(String.format("split %d~%d ==> %d~%d, %d~%d", start, end, start, middle, middle, end));
		SumTask subtask1 = new SumTask(array, start, middle);
		SumTask subtask2 = new SumTask(array, middle, end);
		invokeAll(subtask1, subtask2);
		Long subresult1 = subtask1.join();
		Long subresult2 = subtask2.join();
		Long result = subresult1 + subresult2;
		System.out.println("result = " + subresult1 + " + " + subresult2 + " ==> " + result);
		return result;
	}
	
	static public void fillRandom(long[] array) {
		Random r = new Random();
		for(int i = 0; i < array.length; i++) {
			array[i] = r.nextLong();
		}
	}

}
