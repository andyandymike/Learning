package test.andy.hello;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.testng.annotations.Test;

public class DownLoadTaskTest {

  @Test
  public void DownLoadTask() throws InterruptedException, ExecutionException {
    String url = "https://www.bilibili.com/index.html";
    ExecutorService executor = Executors.newFixedThreadPool(3);
    DownLoadTask download = new DownLoadTask(url);
    Future<String> future = executor.submit(download);
    String html = future.get();
    System.out.println(html);
    executor.shutdown();
  }
}
