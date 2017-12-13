package test.andy.hello;

import org.testng.annotations.Test;

public class SortTest {

  @Test
  public void quickSort() {
    int[] input = {6,1,5,4,8,3,9,12,51,11,15,14,13,25,69,47,56,74,26,78};
    Sort sort = new Sort(input);
    sort.selectSort();
    sort.printArray();
  }
}
