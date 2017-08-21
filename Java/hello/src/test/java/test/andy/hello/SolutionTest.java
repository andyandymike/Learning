package test.andy.hello;

import org.testng.annotations.Test;

public class SolutionTest {

  @Test
  public void kClosest() {
	  Point[] input = new Point[5];
	  input[0] = new Point(4,6);
	  input[1] = new Point(4,7);
	  input[2] = new Point(4,4);
	  input[3] = new Point(2,5);
	  input[4] = new Point(1,1);
	  Point[] result = new Solution().kClosest(input, new Point(), 3);
	  for(int i = 0; i < result.length; i++) {
		  System.out.println(Integer.toString(result[i].x) + ' ' + Integer.toString(result[i].y));
	  }
  }
}
