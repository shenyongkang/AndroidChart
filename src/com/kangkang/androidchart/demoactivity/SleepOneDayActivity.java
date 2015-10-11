package com.kangkang.androidchart.demoactivity;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.kangkang.androidchart.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;

public class SleepOneDayActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		setContentView(R.layout.sleep_one_day);
		double[] duration = {100.0,10,0, 20, 50, 100, 50};
		String[] type = { "N" , "A", "D", "S",  "D", "A"};
		HorizontalBarChart myChart = (HorizontalBarChart) findViewById(R.id.sleepOneDay);

		setChart(myChart, duration, type);
	}

	private void setChart(HorizontalBarChart barChart,
			double[] sleepDuration, String[] sleepType) {
		

		int[] colors = new int[5];
		colors[0] = Color.argb(255, 11, 55, 144);
		colors[1] = Color.argb(255, 48, 120, 254);
		colors[2] = Color.argb(255, 123, 179, 254);
		colors[3] = Color.argb(255, 230, 230, 230);
		colors[4] = Color.argb(0, 255, 255, 255);
		ArrayList<ArrayList<Float>> values = new ArrayList<ArrayList<Float>>();
		for(int i=0;i<4;++i){                            //四种类型 SDNA
			values.add(new ArrayList<Float>());
		}
		String[] dataLabels = {"","","",""};
		ArrayList<Integer> colorsList = new ArrayList<Integer>();
		for(int i=0;i<sleepDuration.length;++i){
			if(sleepType.equals("S")){
				values.get(0).add((float) (float) sleepDuration[i]);
				values.get(1).add((float) sleepDuration[i]);
				values.get(2).add(0.0f);
				values.get(3).add(0.0f);
				
				values.get(0).add(0.0f);
				values.get(1).add(0.0f);
				values.get(2).add((float) sleepDuration[i]);
				values.get(3).add((float) sleepDuration[i]);
				colorsList.add(colors[1]);
				colorsList.add(colors[4]);
				
			}
			if(sleepType.equals("D")){
				values.get(0).add((float) sleepDuration[i]);
				values.get(1).add((float) sleepDuration[i]);
				values.get(2).add((float) sleepDuration[i]);
				values.get(3).add(0.0f);
				
				values.get(0).add(0.0f);
				values.get(1).add(0.0f);
				values.get(2).add(0.0f);
				values.get(3).add((float) sleepDuration[i]);
				colorsList.add(colors[0]);
				colorsList.add(colors[4]);
				
			}
			if(sleepType.equals("A")){
				values.get(0).add((float) sleepDuration[i]);
				values.get(1).add(0.0f);
				values.get(2).add(0.0f);
				values.get(3).add(0.0f);
				
				values.get(0).add(0.0f);
				values.get(1).add((float) sleepDuration[i]);
				values.get(2).add((float) sleepDuration[i]);
				values.get(3).add((float) sleepDuration[i]);
				colorsList.add(colors[2]);
				colorsList.add(colors[4]);
				
			}
			if(sleepType.equals("S")){
				values.get(0).add(0.0f);
				values.get(1).add(0.0f);
				values.get(2).add(0.0f);
				values.get(3).add(0.0f);
				
				values.get(0).add((float) sleepDuration[i]);
				values.get(1).add((float) sleepDuration[i]);
				values.get(2).add((float) sleepDuration[i]);
				values.get(3).add((float) sleepDuration[i]);
				colorsList.add(colors[4]);
				colorsList.add(colors[4]);
				
			}
			
		}
		ArrayList<BarEntry> dataEntries = new ArrayList<BarEntry>();

		for (int i = 0; i < values.size(); ++i) {
			float[] fList = new float[values.get(i).size()];
			for(int j=0;i<values.get(j).size();++j){
				fList[j] = values.get(j).get(j);
			}
			dataEntries.add(new BarEntry(fList,i));
		}
		BarDataSet dataSet = new BarDataSet(dataEntries, "");
		dataSet.setBarSpacePercent(0f);
		dataSet.setColors(colorsList);
		dataSet.setDrawValues(false);
		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(dataSet);	
		BarData barData = new BarData(dataLabels, dataSets);
		barChart.setData(barData);
		Log.i("some",dataSet.toString());
		
		YAxis leftAxis = barChart.getAxisLeft();
		leftAxis.setDrawGridLines(true);
		leftAxis.setDrawLabels(false);
		leftAxis.setGridColor(colors[3]);
		leftAxis.setDrawAxisLine(true);
		leftAxis.setAxisLineColor(colors[3]);
		leftAxis.setAxisLineWidth(0.5f);
		YAxis rightAxis = barChart.getAxisRight();
		rightAxis.setEnabled(true);
		rightAxis.setDrawLabels(true);
		rightAxis.setDrawGridLines(false);
		rightAxis.setAxisLineColor(colors[3]);
		rightAxis.setAxisLineWidth(0.5f);

		
		
		XAxis xAxis = barChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawLabels(false);
		xAxis.setDrawGridLines(true);
		xAxis.setGridColor(colors[3]);
		xAxis.setAxisLineWidth(0.5f);
		xAxis.setAxisLineColor(colors[3]);
		barChart.setBackgroundColor(colors[4]);
		barChart.setGridBackgroundColor(colors[4]);
		barChart.getLegend().setEnabled(false);
		barChart.setScaleEnabled(false);
		barChart.animateXY(2000, 2000);
		barChart.notifyDataSetChanged();

	}


		


}
