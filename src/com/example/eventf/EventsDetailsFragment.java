package com.example.eventf;

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

		detailsFragView = inflater.inflate(R.layout.events_detail_activity, container, false);
		detailsFragViewAq = new AQuery(getActivity(), detailsFragView);

		detailsFragViewAq.find(R.id.hours).text(event.getHours());
		detailsFragViewAq.find(R.id.date).text(event.getDate());
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