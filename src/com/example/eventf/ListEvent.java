package com.example.eventf;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import android.os.Bundle;

public class ListEvent {

	private Event event;
	private String mDay;
	private String mMonth;
	private String mYear;
	private String mHours;

	public ListEvent() {

	}

	public ListEvent(Bundle dataBundle) {
		event.setDescription(dataBundle.getString("Data"));
		// event.setHours(dataBundle.getString("Hours"));
		event.setLocation(dataBundle.getString("Place"));
		event.setTitle(dataBundle.getString("Title"));
		// event.setDate(dataBundle.getString("Date"));
		event.setImageUrl(dataBundle.getString("Image"));
	}

	public ListEvent(Event e) {
		event = e;
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
				Locale.ENGLISH);
		cal.setTimeInMillis(event.getStartTime() * 1000);
		mDay = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		mMonth = String.format(Locale.US, "%tB", cal).toUpperCase();
		mYear = Integer.toString(cal.get(Calendar.YEAR));

		TimeZone tz = cal.getTimeZone();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mmaaa", Locale.ENGLISH);
		sdf.setTimeZone(tz);
		String localTimeStart = sdf.format(new Date(event.startTime * 1000));
		String localTimeEnd = sdf.format(new Date(event.endTime * 1000));
	     mHours=localTimeStart+"-"+localTimeEnd+" "+"(GMT";

		if (e.gmt < 0)
			mHours = mHours + " " + e.gmt + ")";
		else if (e.gmt > 0)
			mHours = mHours + "+" + e.gmt + ")";
		else
			mHours = mHours + ")";

	}

	public Event getEvent() {
		return event;
	}

	public Bundle toBundle() {
		// Bundle bundle = event.toBundle();

		return new Bundle(event.toBundle());
	}

	// /set///
	public void setHours(String hours) {

		this.mHours = hours;
	}

	public void setDay(String day) {

		this.mDay = day;
	}

	public void setYear(String year) {

		this.mYear = year;
	}

	public void setMonth(String month) {

		this.mMonth = month;
	}

	// get///

	public String getHours() {
		return mHours;
	}

	public String getDay() {

		return mDay;
	}

	public String getYear() {

		return mYear;
	}

	public String getMonth() {

		return mMonth;
	}

}
