package sbnz.integracija.example.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyLogger {
	
	public void log(String message) {
		DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS");
		LocalDateTime now = LocalDateTime.now();
		String date = now.format(dft);
		System.out.println("[" + date + "] - " + message);
	}

}