package com.android_s14.rvh_sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.android_s14.rvh.DataModel;
import com.android_s14.rvh.RecyclerViewBuilder;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

	private class ActualData implements DataModel {

		private final String firstName;
		private final String lastName;

		public ActualData(String firstName, String lastName) {
			this.firstName = firstName;
			this.lastName = lastName;
		}

		@Override
		public String getTextField(int i) {
			switch (i) {
				case 0:
					return firstName;
				case 1:
					return lastName;
				default:
					return firstName;
			}
		}

		@Override
		public int getDataFieldsNumber() {
			return 2;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View layout = getLayoutInflater().inflate(R.layout.activity_main, null);
		List<DataModel> data = getData();
		((RelativeLayout) layout).addView(new RecyclerViewBuilder(this).setData(data).build(),
		                                  ViewGroup.LayoutParams.MATCH_PARENT,
		                                  ViewGroup.LayoutParams.MATCH_PARENT);
		setContentView(layout);
	}

	private List<DataModel> getData() {
		List<DataModel> data = new ArrayList<>();
		data.add(new ActualData("Joe", "Black"));
		data.add(new ActualData("Peter", "Brown"));
		return data;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
