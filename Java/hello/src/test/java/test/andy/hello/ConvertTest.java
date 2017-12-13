package test.andy.hello;

import org.testng.annotations.Test;

public class ConvertTest {

  @Test
  public void convertPalindrome() {
    String input = "sdsdlkjsaoio";
    Convert c = new Convert();
    System.out.println(c.convertPalindrome(input));
  }
}
