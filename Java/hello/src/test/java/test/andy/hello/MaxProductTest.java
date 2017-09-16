package test.andy.hello;

import org.testng.annotations.Test;

public class MaxProductTest {

  @Test
  public void maxProduct() {
    int[] nums = {-4,-3};
    MaxProduct mp = new MaxProduct();
    System.out.println(mp.maxProduct(nums));
  }
}
