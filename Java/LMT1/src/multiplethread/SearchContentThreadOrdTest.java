package multiplethread;

import java.io.File;

import exercise.SearchContent;

public class SearchContentThreadOrdTest {

	public static void main(String[] args) {
		File testFolder = new File("C:\\Users\\i067382\\Documents\\Learning\\Java\\LMT1\\test");
		SearchContentThread sct = new SearchContentThread(testFolder, "andy");
		new Thread(sct).start();
	}

}
