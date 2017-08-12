package test.andy.hello;

import org.testng.annotations.Test;

public class MaxEnvelopesTest {

  @Test
  public void maxEnvelopes() {
    int[][] envelopes = new int[4][2];
    envelopes[0][0] = 5;
    envelopes[0][1] = 4;
    envelopes[1][0] = 6;
    envelopes[1][1] = 4;
    envelopes[2][0] = 6;
    envelopes[2][1] = 7;
    envelopes[3][0] = 2;
    envelopes[3][1] = 3;
    MaxEnvelopes me = new MaxEnvelopes();
    System.out.println("max: " + me.maxEnvelopes(envelopes));
  }
}
