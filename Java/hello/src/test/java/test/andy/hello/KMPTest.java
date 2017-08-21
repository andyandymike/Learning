package test.andy.hello;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

public class KMPTest {

  @Test
  public void KMP() {
	char[] target = new String("abcdabcdabcde").toCharArray();
	char[] partten = new String("abcde").toCharArray();
    KMP kmp = new KMP(target, partten);
    int match = kmp.match().get(0);
    assertEquals(match, 8);
  }
}
