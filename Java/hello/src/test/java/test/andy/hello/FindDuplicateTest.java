package test.andy.hello;

import org.testng.annotations.Test;

public class FindDuplicateTest {

  @Test
  public void findDuplicate() {
    FindDuplicate fd = new FindDuplicate();
    int[] nums = {5,5,4,3,2,1};
    System.out.println(fd.findDuplicate(nums));
  }
}
