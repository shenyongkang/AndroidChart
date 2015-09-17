package com.kangkang.androidchart;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis.AxisDependency;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//	    LineChart mLinechart = new LineChart(this);
//
//	    RelativeLayout rl = (RelativeLayout) findViewById(R.id.relativeLayout);
//	    rl.addView(mLinechart);
	    
//	    rl.add(mLinechart);
	    
		LineChart mLinechart = (LineChart) findViewById(R.id.chart);
		LineData data = setData();


		mLinechart.setData(data);
//		mLinechart.setBackgroundColor(Color.BLUE);
		mLinechart.invalidate();

	}

	private LineData setData() {
		ArrayList<Entry> valsComp1 = new ArrayList<Entry>();
		ArrayList<Entry> valsComp2 = new ArrayList<Entry>();
		
	    Entry c1e1 = new Entry(100.000f, 0); // 0 == quarter 1
	    valsComp1.add(c1e1);
	    Entry c1e2 = new Entry(50.000f, 1); // 1 == quarter 2 ...
	    valsComp1.add(c1e2);
	    // and so on ...

	    Entry c2e1 = new Entry(120.000f, 0); // 0 == quarter 1
	    valsComp2.add(c2e1);
	    Entry c2e2 = new Entry(110.000f, 1); // 1 == quarter 2 ...
	    valsComp2.add(c2e2);
	    LineDataSet setComp1 = new LineDataSet(valsComp1, "Company 1");
	    setComp1.setAxisDependency(AxisDependency.LEFT);
	    LineDataSet setComp2 = new LineDataSet(valsComp2, "Company 2");
	    setComp2.setAxisDependency(AxisDependency.LEFT);
	    ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
	    dataSets.add(setComp1);
	    dataSets.add(setComp2);

	    ArrayList<String> xVals = new ArrayList<String>();
	    xVals.add("1.Q"); xVals.add("2.Q"); xVals.add("3.Q"); xVals.add("4.Q"); 
	    LineData data = new LineData(xVals, dataSets);
	    return data;
	
		
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
}
