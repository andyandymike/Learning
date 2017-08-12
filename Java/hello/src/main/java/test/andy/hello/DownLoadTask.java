package test.andy.hello;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.Callable;

public class DownLoadTask implements Callable<String>{
	
	private final String url;
	
	public DownLoadTask(String url) {
		this.url = url;
	}

	@Override
	public String call() throws Exception {
		System.out.println("Start Download " + url);
		URLConnection conn = new URL(this.url).openConnection();
		conn.connect();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"))) {
			String s = null;
			StringBuilder sb = new StringBuilder();
			while ((s = reader.readLine()) != null) {
				sb.append(s).append("\n");
			}
			return sb.toString();
		}
	}

}
