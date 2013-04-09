package com.example.eventf;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import android.os.Bundle;

public class EventWrapper {

	private Event event;
	private String mDay;
	private String mMonth;
	private String mYear;
	private String mHours;

	public EventWrapper(Event e) {
		event = e;
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"),
				Locale.ENGLISH);
		cal.setTimeInMillis(event.getStartTime() * 1000);
		mDay = Integer.toString(cal.get(Calendar.DAY_OF_MONTH));
		mMonth = String.format(Locale.US, "%tB", cal).toUpperCase(Locale.US);
		mYear = Integer.toString(cal.get(Calendar.YEAR));
		mHours = EventsTimeDesign.eventsHoursDesign(e.getStartTime(), e.getEndTime(), e.getGmt());	
	}

	
	public Event getEvent() {
		return event;
	}

	public Bundle toBundle() {
	
		return new Bundle(event.toBundle());
	}

	/** setters **/
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

	/** getters **/

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
