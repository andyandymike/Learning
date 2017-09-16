package test.andy.hello;

import org.testng.annotations.Test;

public class MinCostTest {

  @Test
  public void minCost() {
    int[][] input = {{14,2,11}, {11,14,5}, {14,3,10}};
    MinCost mc = new MinCost();
    System.out.println(mc.minCostII(input));
  }
}
