package com.feiyangedu.sample;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Main {

	public static void main(String[] args) throws Exception {
		int n = 12500;
		// 打印Integer.toHexString()的结果:
		System.out.println(Integer.toHexString(n));
		// 比较转换结果是否与JDK的一致:
		System.out.println(toHex(n));
	}

	static String toHex(int n) {
		String resultList = new String();
		Deque<String> stack = new LinkedList<>();
		Integer result = n/16, remainder = n%16;
		for(;result != 0;) {
			stack.push(String.valueOf(digitToChar(remainder)));
			remainder = result%16;
			result = result/16;
		}
		stack.push(String.valueOf(digitToChar(remainder)));
		while(!stack.isEmpty()) {
			resultList = resultList + stack.pop();
		}
		return resultList.toString();
	}

	static char digitToChar(int m) {
		return DIGITS.charAt(m);
	}

	static final String DIGITS = "0123456789abcdef";

}
