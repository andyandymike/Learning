package com.feiyangedu.sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;

public class Main {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.out.println("Usage: java com.feiyangedu.sample.Main <gbk-file.txt> <utf8-file.txt>");
			return;
		}
		String src = args[0];
		String dest = args[1];
		copyGbkToUtf8(src, dest);
	}

	static void copyGbkToUtf8(String src, String dest) throws IOException {
		InputStream input = new FileInputStream(src);
		Reader reader = new InputStreamReader(input, "GBK");
		OutputStream output = new FileOutputStream(dest);
		Writer writer = new OutputStreamWriter(output, "UTF-8");
		int n = 0;
		while((n = reader.read()) != -1) {
			writer.write(n);
		}
		reader.close();
		writer.close();
	}

}
