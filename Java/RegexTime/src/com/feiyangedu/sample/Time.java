package com.feiyangedu.sample;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Time {

	/**
	 * TODO: 提取合法时间字符串的时，分，秒
	 * 
	 * @param str
	 *            Time字符串
	 * @return Time对象，包含时，分，秒，或者当字符串不合法时返回null
	 */
	public static Time parseTime(String str) {
		int h = 0;
		int m = 0;
		int s = 0;
		// FIXME:
		Matcher mt = Pattern.compile("^(\\d|[0-1]\\d|2[0-3])\\:(\\d|[0-5]\\d)\\:(\\d|[0-5]\\d)$").matcher(str);
		if (mt.matches()) {
			h = Integer.parseInt(mt.group(1));
			m = Integer.parseInt(mt.group(2));
			s = Integer.parseInt(mt.group(3));
			return new Time(h, m, s);
		}
		return null;
	}

	private final int hour;
	private final int minute;
	private final int second;

	public Time(int hour, int minute, int second) {
		this.hour = hour;
		this.minute = minute;
		this.second = second;
	}

	public int getHour() {
		return hour;
	}

	public int getMinute() {
		return minute;
	}

	public int getSecond() {
		return second;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (o instanceof Time) {
			Time t = (Time) o;
			return t.hour == this.hour && t.minute == this.minute && t.second == this.second;
		}
		return false;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hour, minute, second);
	}

	@Override
	public String toString() {
		return String.format("%02d:%02d:%02d", hour, minute, second);
	}
}
