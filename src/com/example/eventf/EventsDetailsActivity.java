package com.example.eventf;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.eventf.EventsDetailsFragment.DetailsFragmentDataIf;

public class EventsDetailsActivity extends FragmentActivity implements
		DetailsFragmentDataIf {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events_details_fragment);
	}

	public Event getEvent() {
		return new Event(getIntent().getExtras());
	}
}