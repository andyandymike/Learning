package test.andy.hello;

import java.io.File;

import org.testng.annotations.Test;

public class AppTestNg {
  @Test
  public void testApp() {
	  File file = new File("C:\\Users\\i067382\\Documents\\Learning\\Tests\\search");
	  String search = "andy";
	  String suffix = "txt";
	  SearchFiles sf = new SearchFiles();
	  sf.search(file, search, suffix);
  }
}
