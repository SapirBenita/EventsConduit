package com.example.eventf;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidquery.AQuery;

public class EventsDetailsFragment extends Fragment {

	private DetailsFragmentDataIf mDataIf;
	AQuery detailsFragViewAq;
	View detailsFragView;

	// Context context;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Event event = mDataIf.getEvent();
        String mHours;
        String Date;        
		detailsFragView = inflater.inflate(R.layout.events_detail_activity, container, false);
		detailsFragViewAq = new AQuery(getActivity(), detailsFragView);
		
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"),Locale.ENGLISH);
		cal.setTimeInMillis(event.getStartTime() * 1000);
		

		TimeZone tz = cal.getTimeZone();
		SimpleDateFormat sdf = new SimpleDateFormat("h:mmaaa", Locale.ENGLISH);
		sdf.setTimeZone(tz);
		
		String localTimeStart = sdf.format(new Date(event.startTime * 1000));
		String localTimeEnd = sdf.format(new Date(event.endTime * 1000));
	     mHours=localTimeStart+"-"+localTimeEnd+" "+"(GMT";

		if (event.gmt < 0)
			mHours = mHours + " " + event.gmt + ")";
		else if (event.gmt > 0)
			mHours = mHours + "+" + event.gmt + ")";
		else
			mHours = mHours + ")";
	
		SimpleDateFormat sdf2 = new SimpleDateFormat( "EEEEEEEE ,MMMMM d , yyyy",Locale.ENGLISH);
		sdf2.setTimeZone(tz);
				  

					 String local = sdf2.format(new Date(event.startTime *1000)); 

		detailsFragViewAq.find(R.id.hours).text(mHours);
		detailsFragViewAq.find(R.id.date).text(local);
		detailsFragViewAq.find(R.id.title).text(event.getTitle());
		detailsFragViewAq.find(R.id.location).text(event.getlocation());

		if (!(event.getDescription().equals("null"))) {
			detailsFragViewAq.find(R.id.data).getTextView()
					.setHint(event.getDescription() + "\n");
			detailsFragViewAq.find(R.id.background).image(event.getImageUrl());
		}

		return detailsFragView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mDataIf = (DetailsFragmentDataIf) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement DetailsFragmentDataIf");
		}
	}

	public interface DetailsFragmentDataIf {

		public Event getEvent();

	}

}