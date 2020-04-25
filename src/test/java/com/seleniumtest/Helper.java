package com.seleniumtest;

import java.io.InterruptedIOException;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Helper {
	public static void pause(long s) throws InterruptedException {
		Thread.sleep(s);
	}

	public static void printCurentTime() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		LocalDateTime timeNow = LocalDateTime.now();
		System.out.println(dtf.format(timeNow));
	}
}
