package com.android_s14.rvh_sample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android_s14.rvh.DataModel;
import com.android_s14.rvh.RecyclerViewBuilder;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View layout = getLayoutInflater().inflate(R.layout.activity_main, null);
		List<DataModel> data = getData();
		List<DataModel> imageData = getImageData();
		View.OnClickListener listener = new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this,
				               "Item clicked",
				               Toast.LENGTH_SHORT).show();
			}
		};
		//textual recyclerview example
		RecyclerView textRecyclerView = new RecyclerViewBuilder(this).setData(data)
		                                                             .setLayoutManager(
				                                                             new GridLayoutManager
						                                                             (this,
						                                                              2))
		                                                             .setRowLayout(R.layout
				                                                                           .text_row_layout)
		                                                             .setListener(listener)
		                                                             .build();
		//example of recyclerview containing pictures
		RecyclerView imageRecyclerView = new RecyclerViewBuilder(this).setData(imageData)
		                                                              .setRowLayout(R.layout
				                                                                            .image_row_layout)
		                                                              .build();
		((RelativeLayout) layout).addView(textRecyclerView,
		                                  ViewGroup.LayoutParams.MATCH_PARENT,
		                                  ViewGroup.LayoutParams.MATCH_PARENT);
		setContentView(layout);
	}

	private List<DataModel> getImageData() {
		List<DataModel> data = new ArrayList<>();
		data.add(new ImageData("Joe",
		                       "Black",
		                       getResources().getDrawable(R.drawable.ic_launcher)));
		data.add(new ImageData("Peter",
		                       "Brown",
		                       getResources().getDrawable(R.drawable.ic_launcher)));
		data.add(new ImageData("Andrew",
		                       "Red",
		                       getResources().getDrawable(R.drawable.ic_launcher)));
		return data;
	}

	private List<DataModel> getData() {
		List<DataModel> data = new ArrayList<>();
		data.add(new TextData("Joe", "Black"));
		data.add(new TextData("Peter", "Brown"));
		data.add(new TextData("Joe", "Black"));
		data.add(new TextData("Peter", "Brown"));
		data.add(new TextData("Andrew", "Red"));
		data.add(new TextData("Peter", "Brown"));
		data.add(new TextData("Andrew", "Red"));
		data.add(new TextData("Peter", "Brown"));
		data.add(new TextData("Andrew", "Red"));
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
