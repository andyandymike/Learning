package com.feiyangedu.sample;

public class QQ {

	/**
	 * TODO: 判断输入的字符串是否是合法的QQ号（5～10位数字构成）
	 */
	public static boolean isValidQQ(String s) {
		if(s == null) {
			return false;
		}
		return s.matches("\\d{5,10}");
	}

}
