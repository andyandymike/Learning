package multiplethread;

import java.io.File;

import exercise.SearchContent;

public class SearchContentThread implements Runnable {
	
	private File folder;
	private String search;
	
	public SearchContentThread(File folder, String search) {
		this.folder = folder;
		this.search = search;
	}

	@Override
	public void run() {
		SearchContent.search(folder, search);
	}

}
