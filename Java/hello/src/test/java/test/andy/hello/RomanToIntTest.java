package test.andy.hello;

import org.testng.annotations.Test;

public class RomanToIntTest {

  @Test
  public void romanToInt() {
    String input = "DCXXI";
    RomanToInt rti = new RomanToInt();
    System.out.println(rti.romanToInt(input));
  }
}
