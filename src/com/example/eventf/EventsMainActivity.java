package com.example.eventf;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.eventf.EventsListFragment.OnEventSelectedListener;

public class EventsMainActivity extends FragmentActivity implements OnEventSelectedListener {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.events_list_fragment);

		// background to avoid redraw.
		getWindow().setBackgroundDrawable(null);

	}

	public void onEventSelected(Event eventDetail) {
		Intent intent = new Intent(this, EventsDetailsActivity.class);
		intent.putExtras(eventDetail.toBundle());
		startActivity(intent);
	}
}
