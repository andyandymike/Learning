package com.feiyangedu.sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: java com.feiyangedu.sample.Main <src-file> <dest-file>");
			return;
		}
		String src = args[0];
		String dest = args[1];
		copy(src, dest);
	}

	static void copy(String src, String dest) throws FileNotFoundException, IOException {
		try(FileInputStream is = new FileInputStream(src)) {
			try(FileOutputStream os = new FileOutputStream(dest)) {
				int n = 0;
				while((n = is.read()) != -1) {
					os.write(n);
				}
			}
		}
	}

}
