package test.andy.hello;

import java.util.Date;

import org.testng.annotations.Test;

public class TwoSumTest {

  @Test
  public void twoSum() {
	  int[] input = new int[]{0,0,3,4};
	  TwoSum ts = new TwoSum();
	  int[] output = ts.twoSum(input, 0);
	  long lstart = System.currentTimeMillis();
	  Date start = new Date(lstart);
	  System.out.println(start);
	  System.out.println(String.valueOf(output[0]) + ", " + String.valueOf(output[1]));
	  long lend = System.currentTimeMillis();
	  Date end = new Date(lend);
	  System.out.println(end);
	  System.out.println(lend - lstart);
  }
}
