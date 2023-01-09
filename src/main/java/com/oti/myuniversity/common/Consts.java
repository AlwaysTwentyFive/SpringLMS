package com.oti.myuniversity.common;

public final class Consts {
	private Consts() {};
	
	public static final int ROWS_PER_PAGE = 10;
	public static final int PAGES_PER_GROUP = 5;
	public static final String DUMMY_PASSWORD = "!";
	public static final String DATE_FORMAT = "yyyy-MM-dd";
	public static final String TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";
	
	//Atomic wrappers are thread-safe and more efficient than synchronized using CAS algorithm
	//public static final AtomicInteger logCount = new AtomicInteger(0);
	public static int logCount = 0;
}