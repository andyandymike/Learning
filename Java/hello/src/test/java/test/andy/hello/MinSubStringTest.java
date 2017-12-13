package test.andy.hello;

import static org.testng.Assert.assertTrue;

import java.io.UnsupportedEncodingException;

import org.testng.annotations.Test;

public class MinSubStringTest {

  @Test
  public void findMinSubString() {
    String source = "ADOBECODEBANC";
    String target = "ABC";
    MinSubString mss = new MinSubString(source, target);
    //assertTrue(mss.findMinSubString().equals("BANC"));
    System.out.println(mss.findMinSubString());
  }
}
