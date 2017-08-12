package test.andy.hello;

import java.io.File;

public class SearchFiles {
	public void search(File file, String search, String suffix) {
		if(file.isFile()) {
			if(file.getName().toLowerCase().endsWith('.' + suffix)) {
				System.out.println("now searching file : " + file.getName());
				Runnable sft = new SearchFileThread(file, search);
				new Thread(sft).start();
			}
		}
		
		if(file.isDirectory()) {
			File[] fs = file.listFiles();
			for(File f : fs) {
				System.out.println("now searching file : " + f.getName());
				search(f, search, suffix);
			}
		}
	}

}
