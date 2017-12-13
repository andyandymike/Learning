package test.andy.hello;

import org.testng.annotations.Test;

public class MaxDiffTest {

  @Test
  public void maxDiff() {
	int[][] input = {{1,2,3}, {4,5}, {1,2,3}};
    MaxDiff md = new MaxDiff();
    System.out.println(md.maxDiff(input));
  }
}
