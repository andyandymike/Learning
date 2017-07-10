package com.feiyangedu.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws Exception {
		// 输入：
//		Scanner scanner = new Scanner(System.in);
//		System.out.print("Departure date (yyyy-MM-dd): ");
//		String departureDate = scanner.nextLine();
//		System.out.print("Departure time (HH:mm): ");
//		String departureTime = scanner.nextLine();
		String sDepartureDate = "2017-06-03";
		String sDepartureTime = "08:15";
		// TODO: 飞行12小时15分钟，计算到达的当地时间：
		LocalDate arrivalDate = null;
		LocalTime arrivalTime = null;
		
		LocalDate departureDate = LocalDate.parse(sDepartureDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalTime departureTime = LocalTime.parse(sDepartureTime, DateTimeFormatter.ofPattern("HH:mm"));
		LocalDateTime departureDateTime = LocalDateTime.of(departureDate, departureTime);
		ZonedDateTime arrivalDateTime_bj = departureDateTime.plusHours(12).plusMinutes(15).atZone(ZoneId.systemDefault());
		ZonedDateTime arrivalDateTime_ny = arrivalDateTime_bj.withZoneSameInstant(ZoneId.of("America/New_York"));
		arrivalDate = arrivalDateTime_ny.toLocalDate();
		arrivalTime = arrivalDateTime_ny.toLocalTime();
		
		// 输出：
		System.out.println("Arrival date: " + arrivalDate);
		System.out.println("Arrival time: " + arrivalTime);
	}

}
