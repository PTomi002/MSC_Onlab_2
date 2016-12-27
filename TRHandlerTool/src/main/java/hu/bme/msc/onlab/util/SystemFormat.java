package hu.bme.msc.onlab.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class SystemFormat {
	private static final ThreadLocal<DateFormat> DATE_FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

	public static String parseDate(Date date){
		return DATE_FORMAT.get().format(date);
	}
	
	public static Date formatDate(String date) throws ParseException{
		return DATE_FORMAT.get().parse(date);
	}
}
