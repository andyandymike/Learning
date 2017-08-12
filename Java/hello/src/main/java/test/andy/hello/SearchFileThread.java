package test.andy.hello;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SearchFileThread implements Runnable{
	private File file;
	private String search;
	
	SearchFileThread(File file, String search) {
		this.file = file;
		this.search = search;
	}
	
	@Override
	public void run() {
		String fileContent = readFileContent(file);
		if(fileContent.contains(search)) {
			System.out.printf("Find String %s in File: %s%n",search,file);
		}
	}
	
	public String readFileContent(File file) {
		try {
			FileReader fr = new FileReader(file);
			char[] all = new char[(int) file.length()];
			fr.read(all);
			return new String(all);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
