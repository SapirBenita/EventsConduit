package com.example.eventf;

import android.os.Bundle;

public class Event {

	private String mDate;
	private String mDescription;
	private String mDay;
	private String month;
	private String mlocation;
	private String hours;
	private String title;
	private String year;
	private String imageUrl;
    static final String DATA="Data";
   
	public Event() { }

	public Event(Bundle dataBundle) {
		mDescription = dataBundle.getString("Data");
		hours = dataBundle.getString("Hours");
		mlocation = dataBundle.getString("Place");
		title = dataBundle.getString("Title");
		mDate = dataBundle.getString("Date");
		imageUrl = dataBundle.getString("Image");
	}

	public void setHours(String hours) {

		this.hours = hours;
	}

	public void setDay(String day) {

		this.mDay = day;
	}

	public void setYear(String year) {

		this.year = year;
	}

	public void setDate(String date) {

		this.mDate = date;
	}

	public void setMonth(String month) {

		this.month = month;
	}

	public void setDescription(String description) {

		this.mDescription = description;
	}

	public void setLocation(String place) {

		this.mlocation = place;
	}

	public void setTitle(String title) {

		this.title = title;

	}

	public void setImageUrl(String imageUrl) {

		this.imageUrl = imageUrl;

	}

	/** Setters **/

	public String getHours() {
		return hours;
	}

	public String getDay() {

		return mDay;
	}

	public String getYear() {

		return year;
	}

	public String getDate() {

		return mDate;
	}

	public String getMonth() {

		return month;
	}

	public String getData() {

		return mDescription;
	}

	public String getPlace() {

		return mlocation;
	}

	public String getTitle() {

		return title;

	}

	public String getImageUrl() {

		return imageUrl;

	}

	public Bundle toBundle() {
		Bundle bundle = new Bundle();
		bundle.putString("Data", mDescription);
		bundle.putString("Hours", hours);
		bundle.putString("Place", mlocation);
		bundle.putString("Date", mDate);
		bundle.putString("Image", imageUrl);
		return bundle;
	}

}
