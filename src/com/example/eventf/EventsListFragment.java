package com.example.eventf;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EventsListFragment extends ListFragment {
	OnEventSelectedListener mSelectIf;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new GetEventsAsync().execute();
	}

	private class GetEventsAsync extends
			AsyncTask<Void, Integer, ArrayList<Event>> {

		@Override
		protected ArrayList<Event> doInBackground(Void... params) {
			return getEvents();
		}

		@Override
		protected void onPostExecute(ArrayList<Event> events) {
			UserItemAdapter adapter = new UserItemAdapter(getActivity(),
					R.layout.events_list_activitiy, events);
			getListView().setDivider(null);
			setListAdapter(adapter);

		}

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		UserItemAdapter adapter = (UserItemAdapter) getListAdapter();
		Event clickedEvent = adapter.getItem(position);
		mSelectIf.onEventSelected(clickedEvent);
	}

	public class UserItemAdapter extends ArrayAdapter<Event> {

		public UserItemAdapter(Context context, int textViewResourceId,
				ArrayList<Event> events) {
			super(context, textViewResourceId, events);
		}
		
		

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			EventItemViewHolder viewHolder = null;
			if (convertView == null) {
				// if null we need to create
				LayoutInflater vi = (LayoutInflater) getContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				convertView = vi.inflate(R.layout.events_list_activitiy, null);
				viewHolder = new EventItemViewHolder();
				viewHolder.day = (TextView) convertView.findViewById(R.id.day);
				viewHolder.place = (TextView) convertView
						.findViewById(R.id.place);
				viewHolder.hours = (TextView) convertView
						.findViewById(R.id.hours);
				viewHolder.title = (TextView) convertView
						.findViewById(R.id.title);
				viewHolder.month = (TextView) convertView
						.findViewById(R.id.month);
				viewHolder.year = (TextView) convertView
						.findViewById(R.id.year);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (EventItemViewHolder) convertView.getTag();
			}

			Event event = getItem(position);

			if (event != null) {

				viewHolder.year.setHint(event.getYear());
				viewHolder.title.setText(event.getTitle());
				viewHolder.month.setText(event.getMonth().toUpperCase());
				viewHolder.day.setText(event.getDay());
				viewHolder.place.setHint(event.getPlace());
				viewHolder.hours.setHint(event.getHours());
			}
			return convertView;
		}

	}

	private static class EventItemViewHolder {
		private TextView day;
		private TextView place;
		private TextView hours;
		private TextView title;
		private TextView month;
		private TextView year;

	}

	public ArrayList<Event> getEvents() {

		String searchUrl = "http://cms.mobile.conduit-services.com/calendar/2/?id=8592gjltnhrujne0m08i4jgp04%40group.calendar.google.com&max-results=25&start-index=0&since=%24date&until=%24date%2B6m&params=%7B%22order%22%3A%22asc%22%7D";
		ArrayList<Event> events = new ArrayList<Event>();

		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(searchUrl);
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = null;
		try {// get data
			responseBody = client.execute(get, responseHandler);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		JSONObject jsonObject = null;

		try {
			jsonObject = new JSONObject(responseBody);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		JSONArray jsonArray = null;
		JSONArray jsonArrayTimes = null;

		try {
			jsonObject = jsonObject.getJSONObject("result");

			jsonArray = jsonObject.getJSONArray("items");

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String date, dayString, day, hours, month, location, title;
		String year;
		String imageUrl;
		long startTime;
		long endTime;
		long gmt;
		int i;
		JSONObject eventDetailsJsonObj;
		JSONObject jsonObjectTimes;
		
		for (i = 0; i < jsonArray.length(); i++)
			try {
				Event event = new Event();
				
				eventDetailsJsonObj=jsonArray.getJSONObject(i);
				event.setDescription(eventDetailsJsonObj.optString("description"));
				
				
				location = (eventDetailsJsonObj).optString("location");
				title = (eventDetailsJsonObj).optString("title");
				imageUrl = (eventDetailsJsonObj).optString("imageUrl");

				jsonArrayTimes = (eventDetailsJsonObj).getJSONArray("times");
				jsonObjectTimes=jsonArrayTimes.getJSONObject(0);
				endTime = jsonObjectTimes.getJSONObject("end").optLong("localTime");
				startTime = jsonObjectTimes.getJSONObject("start").optLong("localTime");
				gmt = jsonObjectTimes.getJSONObject("start").optLong("timeZoneOffset");

				Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"), Locale.ENGLISH);
				
				cal.setTimeInMillis(startTime * 1000);
				int dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);
				String monthStr = String.format(Locale.US,"%tB", cal);
                
				TimeZone tz = cal.getTimeZone();
				SimpleDateFormat sdf = new SimpleDateFormat(
						"yyyy,MMMMM,d,h:mmaaa,EEEEEEEE", Locale.ENGLISH);
				sdf.setTimeZone(tz);

				Date startDate = new Date(startTime * 1000);
				
				
				String localTimeStart = sdf.format(new Date(startTime * 1000));
				String localTimeEnd = sdf.format(new Date(endTime * 1000));
				String[] partsEnd = localTimeEnd.split(",");
				String[] partsStart = localTimeStart.split(",");

				year = partsStart[0];
				month = partsStart[1];
				day = partsStart[2];
				dayString = partsStart[4];
				hours = partsStart[3] + " - " + partsEnd[3] + " (GMT";

				if (gmt < 0)
					hours = hours + " " + gmt + ")";
				else if (gmt > 0)
					hours = hours + "+" + gmt + ")";
				else
					hours = hours + ")";
				date = dayString + ",  " + month + " " + day + ", " + year;

				location = location + "\n";

				event.setImageUrl(imageUrl);
				event.setLocation(location);
				event.setTitle(title);
				event.setHours(hours);
				event.setDay(day);
				event.setMonth(month);
				event.setYear(year);
				event.setDate(date); 
				events.add(event);

			}

			catch (JSONException e) {
				// do nothing - just do not add this event to the list.
			}

		return events;

	}

	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mSelectIf = (OnEventSelectedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement DetailsFragmentDataIf");
		}
	}

	public interface OnEventSelectedListener {
		public void onEventSelected(Event event);
	}

}
