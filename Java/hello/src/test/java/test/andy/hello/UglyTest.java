package test.andy.hello;

import org.testng.annotations.Test;

public class UglyTest {

  @Test
  public void isUgly() {
    Ugly ugly = new Ugly();
    int num = 0;
    System.out.println(ugly.isUgly(num));
  }
}
