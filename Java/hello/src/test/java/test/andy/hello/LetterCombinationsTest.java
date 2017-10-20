package test.andy.hello;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class LetterCombinationsTest {

  @Test
  public void letterCombinations() {
    String input = "234";
    LetterCombinations lc = new LetterCombinations();
    List<String> output = lc.letterCombinations(input);
    System.out.println(Arrays.toString(output.toArray()));
  }
}
