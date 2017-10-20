package test.andy.hello;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class GenerateParenthesisTest {

	@Test
	public void generateParenthesis() {
		int input = 3;
		GenerateParenthesis gp = new GenerateParenthesis();
		List<String> output = gp.generateParenthesis(input);
		System.out.println(Arrays.toString(output.toArray()));
	}
}
