package vat.automationtesting_hybrid.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Datetime {
	public static String sdateTime;

	public static void dateTime(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH.mm.ss");
		LocalDateTime now = LocalDateTime.now();
		sdateTime = dtf.format(now);
	}
}
