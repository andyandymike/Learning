package test.andy.hello;

import org.testng.annotations.Test;

public class CuttingTest {

  @Test
  public void cutting() {
    int[] prices = {1, 5, 8, 9, 10, 17, 17, 20};
    int n = 8;
    Cutting c = new Cutting();
    System.out.println(c.cutting(prices, n));
  }
}
