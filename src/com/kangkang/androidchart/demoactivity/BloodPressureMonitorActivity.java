package com.kangkang.androidchart.demoactivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.kangkang.androidchart.R;

public class BloodPressureMonitorActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blood_pressure_monitor);
		
		double[][] values = {{141.0, 134.0, 126.0, 133.0, 122.0, 136.0, 134.0},
		                      {111.0, 94.0, 116.0, 103.0, 112.0, 126.0, 114.0}, 
		                      {72.0, 64.0, 86.0, 73.0, 92.0, 76, 60}};
		String[] labels = {"周日" , "周一","周二", "周三",  "周四", "周五", "周日"};
		LineChart myChart = (LineChart) findViewById(R.id.bloodPressureAnalysisMonitor);
		
		setChart(myChart, values, labels);

		
	}
	
	
	public class MyYAxisValueFormatter implements YAxisValueFormatter{

	    private DecimalFormat mFormat;

	    public MyYAxisValueFormatter () {
	        mFormat = new DecimalFormat("#"); // use one decimal
	    }

	    @Override
	    public String getFormattedValue(float value, YAxis yAxis) {
	        // write your logic here
	        // access the YAxis object to get more information
	        return mFormat.format(value); // e.g. append a dollar-sign
	    }

	}

	private void setChart(LineChart myChart, double[][] values, String[] labels) {

		int[] colors = new int[8];
		colors[0] = Color.argb(255, 61, 180, 195);
		colors[1] = Color.argb(255, 243, 153, 34);
		colors[2] = Color.argb(255, 154, 108, 176);
		colors[3] = Color.argb(205, 30, 250, 30);
		colors[4] = Color.argb(205, 250, 30, 30);
		colors[5] = Color.argb(255, 3, 30, 30);
		colors[6] = Color.argb(255, 244, 170, 103);
		colors[7] = Color.argb(0, 255, 255, 255);
		ArrayList<String> dataLabels = new ArrayList<String>();
		for(int i=0;i<labels.length;++i){
			dataLabels.add("");
			dataLabels.add(labels[i]);
		}
//		ArrayList<ArrayList<Integer>> xValues = new ArrayList<ArrayList<Integer>>();
//		for(int i=0;i<values.length;++i){
//			ArrayList<Integer> tempXValues = new ArrayList<Integer>();
//			for(int j=0;j<values[i].length;++j){
//				tempXValues.add(Integer.valueOf(2*j+1));
//			}
//			xValues.add(tempXValues);
//		}
//		
		ArrayList<ArrayList<Entry>> dataEntries = new ArrayList<ArrayList<Entry>>();
		for(int i=0;i<values.length;++i){
			ArrayList<Entry> entries = new ArrayList<Entry>();
			for(int j=0;j<values[i].length;++j){
				entries.add(new Entry((float) values[i][j], 2*j+1));
			}
			dataEntries.add(entries);
		}
		
		ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		dataSets.add(new LineDataSet(dataEntries.get(0), "收缩压"));
		dataSets.add(new LineDataSet(dataEntries.get(1), "舒张压"));
		dataSets.add(new LineDataSet(dataEntries.get(2), "心率"));
		for(int i=0;i<dataSets.size();++i){
			dataSets.get(i).setColor(colors[i]);
			dataSets.get(i).setDrawCircleHole(false);
			dataSets.get(i).setCircleColor(colors[i]);
			dataSets.get(i).setCircleSize((float) 3.5);
			dataSets.get(i).setDrawValues(false);
			dataSets.get(i).setLineWidth(1);
		}
		
		LineData lineData = new LineData(dataLabels, dataSets);
		
		myChart.setData(lineData);
		XAxis xAxis = myChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);
		xAxis.setDrawAxisLine(false);
		xAxis.setTextColor(colors[5]);
		xAxis.setSpaceBetweenLabels(1);
		xAxis.setAvoidFirstLastClipping(true);
		xAxis.setLabelsToSkip(0);
		myChart.getAxisRight().setEnabled(false);
		YAxis leftAxis = myChart.getAxisLeft();
		leftAxis.setStartAtZero(false);
		leftAxis.setDrawGridLines(false);
		leftAxis.setAxisMinValue(40);
		leftAxis.setAxisMaxValue(220);
		leftAxis.setDrawAxisLine(false);
		leftAxis.setTextSize(14);
		leftAxis.setTextColor(colors[6]);
		leftAxis.setLabelCount(7, true);
		leftAxis.setValueFormatter(new MyYAxisValueFormatter());
		//target line
		LimitLine targetLine90 = new LimitLine(90,"90");
		LimitLine targetLine140 = new LimitLine(140,"140");
		targetLine90.setLineColor(colors[3]);
		targetLine90.setLineWidth((float) 0.5);
		targetLine90.setTextColor(colors[6]);
		targetLine90.setTextSize(12);
		targetLine140.setLineColor(colors[4]);
		targetLine140.setLineWidth((float) 0.5);
		targetLine140.setTextColor(colors[4]);
		targetLine140.setTextSize(12);
		leftAxis.addLimitLine(targetLine140);
		leftAxis.addLimitLine(targetLine90);
		myChart.setDescription("");
		myChart.setScaleEnabled(false);
		
		
		
		
		
		myChart.notifyDataSetChanged();
	
	
	
	}

}
