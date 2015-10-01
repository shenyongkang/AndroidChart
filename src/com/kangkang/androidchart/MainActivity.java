package com.kangkang.androidchart;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.utils.Utils;
import com.kangkang.androidchart.demoactivity.BloodPressureAnalysisCurveActivity;
import com.kangkang.androidchart.demoactivity.BloodPressureAnalysisDiagramActivity;
import com.kangkang.androidchart.demoactivity.BloodPressureMonitorActivity;
import com.kangkang.androidchart.demoactivity.BloodSugerAnalysisCurveActivity;
import com.kangkang.androidchart.demoactivity.BloodSugerAnalysisDiagramActivity;
import com.kangkang.androidchart.demoactivity.BloodSugerMonitorActivity;
import com.kangkang.androidchart.demoactivity.BodyFatAnalysisCurveActivity;
import com.kangkang.androidchart.demoactivity.BodyFatAnalysisDiagramActivity;
import com.kangkang.androidchart.demoactivity.BodyFatMonitorActivity;
import com.kangkang.androidchart.demoactivity.BodyHeightActivity;
import com.kangkang.androidchart.demoactivity.BodyHeightAnalysisActivity;
import com.kangkang.androidchart.demoactivity.BodyWeightActivity;
import com.kangkang.androidchart.demoactivity.HomePageChartActivity;
import com.kangkang.androidchart.demoactivity.TemperatureActivity;
import com.kangkang.androidchart.demoactivity.TemperatureAnalysisActivity;


public class MainActivity extends Activity implements OnItemClickListener {

	RelativeLayout mainLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// initialize the utilities
		Utils.init(this);

		ArrayList<ContentItem> objects = new ArrayList<ContentItem>();
		
		
		

		objects.add(new ContentItem("1 HomePageChartActivity",
				""));
		objects.add(new ContentItem("2 BloodPressureAnalysisCurveActivity)",
				""));
		objects.add(new ContentItem("3 BloodPressureAnalysisDiaramActivity",
				""));
		objects.add(new ContentItem("4 BloodPressureMonitorActivity",
				""));
		objects.add(new ContentItem("5 BloodSugerAnalysisCurveActivity",
				""));
		objects.add(new ContentItem("6 BloodSugerAnalysisDiagramActivity",
				""));
		objects.add(new ContentItem("7 BloodSugerMonitorActivity",
				""));
		objects.add(new ContentItem("8 BodyFatAnalysisCurveActivity",
				""));
		objects.add(new ContentItem("9 BodyFatAnalysisDiagramActivity",
				""));
		objects.add(new ContentItem("10 BodyFatMonitorActivity",
				""));
		objects.add(new ContentItem("11 BodyHeightActivity",
				""));
		objects.add(new ContentItem("12 BodyHeightAnalysisActivity",
				""));
		objects.add(new ContentItem("13 BodyWeightActivity",
				""));
		objects.add(new ContentItem("14 TemperatureActivity",
				""));
		objects.add(new ContentItem("15 TemperatureAnalysisActivity",
				""));


		MyAdapter adapter = new MyAdapter(this, objects);

		ListView lv = (ListView) findViewById(R.id.listView1);
		lv.setAdapter(adapter);

		lv.setOnItemClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
		Intent i;

		switch (pos) {
		
		case 0:
			i = new Intent(this, HomePageChartActivity.class);
			startActivity(i);
			break;
		case 1:
			i = new Intent(this, BloodPressureAnalysisCurveActivity.class);
			startActivity(i);
			break;
		case 2:
			i = new Intent(this, BloodPressureAnalysisDiagramActivity.class);
			startActivity(i);
			break;
		case 3:
			i = new Intent(this, BloodPressureMonitorActivity.class);
			startActivity(i);
			break;
		case 4:
			i = new Intent(this, BloodSugerAnalysisCurveActivity.class);
			startActivity(i);
			break;
		case 5:
			i = new Intent(this, BloodSugerAnalysisDiagramActivity.class);
			startActivity(i);
			break;
		case 6:
			i = new Intent(this, BloodSugerMonitorActivity.class);
			startActivity(i);
			break;
		case 7:
			i = new Intent(this, BodyFatAnalysisCurveActivity.class);
			startActivity(i);
			break;
		case 8:
			i = new Intent(this, BodyFatAnalysisDiagramActivity.class);
			startActivity(i);
			break;
		case 9:
			i = new Intent(this, BodyFatMonitorActivity.class);
			startActivity(i);
			break;
		case 10:
			i = new Intent(this, BodyHeightActivity.class);
			startActivity(i);
			break;
		case 11:
			i = new Intent(this, BodyHeightAnalysisActivity.class);
			startActivity(i);
			break;
		case 12:
			i = new Intent(this, BodyWeightActivity.class);
			startActivity(i);
			break;
		case 13:
			i = new Intent(this, TemperatureActivity.class);
			startActivity(i);
			break;
		case 14:
			i = new Intent(this, TemperatureAnalysisActivity.class);
			startActivity(i);
			break;
			
		}
//        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);

	}

	private class ContentItem {
		String name;
		String desc;

		public ContentItem(String n, String d) {
			name = n;
			desc = d;
		}
	}

	private class MyAdapter extends ArrayAdapter<ContentItem> {

		public MyAdapter(Context context, List<ContentItem> objects) {
			super(context, 0, objects);
		}

		@SuppressLint("InflateParams")
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ContentItem c = getItem(position);

			ViewHolder holder = null;

			if (convertView == null) {

				holder = new ViewHolder();

				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.list_item, null);
				holder.tvName = (TextView) convertView
						.findViewById(R.id.tvName);
				holder.tvDesc = (TextView) convertView
						.findViewById(R.id.tvDesc);

				convertView.setTag(holder);

			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			holder.tvName.setText(c.name);
			holder.tvDesc.setText(c.desc);

			return convertView;
		}

		private class ViewHolder {

			TextView tvName, tvDesc;
		}
	}
}
