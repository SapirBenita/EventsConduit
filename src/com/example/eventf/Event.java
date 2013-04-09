package com.example.eventf;

import android.os.Bundle;

/**
 * A class representing a single event from the events list.
 * 
 * @author Sapir.Benita
 *
 */
public class Event {

	private long mEndTime;
	private long mStartTime;
	private long mGmt;
	private String mDescription;
	private String mlocation;
	private String mTitle;
	private String mImageUrl;
	
	// keys to convert this event to a bundle and vice versa.
	private static final String END_TIME = "EndTime";
	private static final String STRAT_TIME = "StartTime";
	private static final String GMT = "Gmt";
	private static final String DESCRIPTION = "Description";
	private static final String LOCATION = "Location";
	private static final String TITLE = "Title";
	private static final String IMAGE_URL = "ImageUrl";

	public Event() {
	}

	public Event(Bundle dataBundle) {
		
		mDescription = dataBundle.getString(DESCRIPTION);
		mlocation = dataBundle.getString(LOCATION);
		mTitle = dataBundle.getString(TITLE);
		mImageUrl = dataBundle.getString(IMAGE_URL);
		mEndTime = dataBundle.getLong(END_TIME);
		mStartTime = dataBundle.getLong(STRAT_TIME);
		mGmt = dataBundle.getLong(GMT);

	}

	public Bundle toBundle() {

		Bundle bundle = new Bundle();
		bundle.putString(DESCRIPTION, mDescription);
		bundle.putString(LOCATION, mlocation);
		bundle.putString(IMAGE_URL, mImageUrl);
		bundle.putLong(GMT, mGmt);
		bundle.putString(TITLE, mTitle);
		bundle.putLong(STRAT_TIME, mStartTime);
		bundle.putLong(END_TIME, mEndTime);

		return bundle;
	}
	
	/** setters **/

	public void setEndTime(Long endTime) {

		this.mEndTime = endTime;
	}

	public void setStartTime(Long startTime) {

		this.mStartTime = startTime;
	}

	public void setGmt(Long gmt) {

		this.mGmt = gmt;
	}

	public void setDescription(String description) {

		this.mDescription = description;
	}

	public void setLocation(String location) {

		this.mlocation = location;
	}

	public void setTitle(String title) {

		this.mTitle = title;

	}

	public void setImageUrl(String imageUrl) {

		this.mImageUrl = imageUrl;

	}

	/** getters **/

	public String getDescription() {

		return mDescription;
	}

	public String getlocation() {

		return mlocation;
	}

	public String getTitle() {

		return mTitle;

	}

	public String getImageUrl() {

		return mImageUrl;

	}

	public Long getEndTime() {

		return mEndTime;
	}

	public Long getStartTime() {

		return mStartTime;
	}

	public Long getGmt() {

		return mGmt;
	}

}
