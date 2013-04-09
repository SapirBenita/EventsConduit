package com.example.eventf;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class EventsTimeDesign {
	
	public static String eventsHoursDesign(Long startTime, Long endTime,Long gmt)
	{
		String mHours;
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
				Locale.ENGLISH);
		cal.setTimeInMillis(startTime * 1000);
		TimeZone tz = cal.getTimeZone();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mmaaa", Locale.ENGLISH);
		sdf.setTimeZone(tz);
		String localTimeStart = sdf.format(cal.getTime());
		String localTimeEnd = sdf.format(new Date(endTime * 1000));
	     mHours=localTimeStart+"-"+localTimeEnd+" "+"(GMT";

		if (gmt < 0) {
			mHours = mHours + " " + gmt + ")";
		}
		else if (gmt > 0) {
			mHours = mHours + "+" + gmt + ")";
		}
		else {
			mHours = mHours + ")";
		}
		return mHours;
	}
	
	
	public static String eventsDateDesign(Long Time){
		String mDate;
		

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
				Locale.ENGLISH);
		cal.setTimeInMillis(Time * 1000); 
		TimeZone tz = cal.getTimeZone();
		
		SimpleDateFormat sdf2 = new SimpleDateFormat(
				"EEEEEEEE ,MMMMM d , yyyy", Locale.ENGLISH);
		sdf2.setTimeZone(tz);

		mDate = sdf2.format(cal.getTime());
		
		return mDate;
	}
	
	

}
