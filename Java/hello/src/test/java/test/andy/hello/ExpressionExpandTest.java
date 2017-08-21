package test.andy.hello;

import org.testng.annotations.Test;

public class ExpressionExpandTest {

  @Test
  public void expressionExpand() {
    String input = "5[10[abcd]Ac20[abcde]]";
    System.out.println(ExpressionExpand.expressionExpand(input));
  }
}
