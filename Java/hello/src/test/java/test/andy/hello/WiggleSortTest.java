package test.andy.hello;

import java.util.Arrays;

import org.testng.annotations.Test;

public class WiggleSortTest {

  @Test
  public void wiggleSort() {
    int[] input = {3, 5, 2, 1, 6, 4};
    WiggleSort ws = new WiggleSort();
    ws.wiggleSort(input);
    System.out.println(Arrays.toString(input));
  }
}
