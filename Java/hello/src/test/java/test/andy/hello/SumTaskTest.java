package test.andy.hello;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

import org.testng.annotations.Test;

public class SumTaskTest {

  @Test
  public void SumTask() {
    long[] array = new long[400];
    SumTask.fillRandom(array);
    ForkJoinPool fjp = new ForkJoinPool(4);
    ForkJoinTask<Long> task = new SumTask(array, 0, array.length);
    long startTime = System.currentTimeMillis();
    Long result = fjp.invoke(task);
    long endTime = System.currentTimeMillis();
    System.out.println("Fork/join sum: " + result + " in " + (endTime - startTime) + " ms.");
  }
}
