package com.feiyangedu.sample;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Template {

	final String template;
	final Pattern pattern = Pattern.compile("\\$\\{(\\w+)\\}");

	public Template(String template) {
		this.template = template;
	}

	public String render(Map<String, Object> data) {
		Matcher m = pattern.matcher(template);
		// TODO:
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, data.get(m.group(1)).toString());
		}
		m.appendTail(sb);
		return sb.toString();
	}

	public static void main(String[] args) {
		Template t = new Template("Hello, ${name}! You are learning ${lang}!");
		Map<String, Object> data = new HashMap<>();
		data.put("name", "Bob");
		data.put("lang", "Java");
		// 打印出：
		// Hello, Bob! You are learning Java!
		System.out.println(t.render(data));
	}
}
