package com.feiyangedu.sample;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String path = ".";
		if (args.length >= 1) {
			path = args[0];
		}
		File dir = new File(path).getCanonicalFile();
		int depth = 0;
		listSubFolders(dir, depth);
	}
	
	public static void listSubFolders(File dir, int depth) {
		if(dir.isDirectory()) {
			for(File subFile: dir.listFiles()) {
				for (int i = 0; i < depth; i++) {
					System.out.print(" ");
				}
				System.out.println(subFile.getName());
				listSubFolders(subFile, depth + 1);
			}
		}
	}
}
