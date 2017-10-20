package test.andy.hello;

import org.testng.annotations.Test;

public class CanPartitionTest {

  @Test
  public void canPartition() {
    int[] nums = {1, 2, 3, 9};
    CanPartition cp = new CanPartition();
    System.out.println(cp.canPartition(nums));
  }
}
