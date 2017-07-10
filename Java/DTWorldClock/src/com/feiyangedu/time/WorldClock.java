package com.feiyangedu.time;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class WorldClock {

	public static String getCurrentDateTime(String zoneId) {
		long currentEpochTime = System.currentTimeMillis();
		Instant ins = Instant.ofEpochMilli(currentEpochTime);
		DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		return f.format(ZonedDateTime.ofInstant(ins, ZoneId.of(zoneId)));
	}

	public static void main(String[] args) throws Exception {
		System.out.println(getCurrentDateTime("Asia/Shanghai"));
		System.out.println(getCurrentDateTime("GMT+09:00"));
		System.out.println(getCurrentDateTime("Z"));
		System.out.println(getCurrentDateTime("America/New_York"));
	}

}
