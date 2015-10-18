package com.kangkang.androidchart.demoactivity;

import java.util.ArrayList;

import com.github.mikephil.charting.charts.BarChart;
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

public class SleepOneWeekActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleep_one_week);
		// values 为每天深度睡眠，浅睡眠 以及活动占比
		// labels 为X轴坐标对应labels
		double[][] values = { { 141.0, 134.0, 126.0 }, { 81.0, 94.0, 1,},
				{ 72.0, 64.0, 86.0 }, { 141.0, 134.0, 126.0 },
				{ 81.0, 94.0, 116.0 }, { 72.0, 64.0, 86.0 } };
		String[] labels = { "周一", "周二", "周三", "周四", "周五", "周六" };
		BarChart myChart = (BarChart) findViewById(R.id.sleepOneWeek);

		setChart(myChart, values, labels);

	}

	private void setChart(BarChart barChart, double[][] values,
			String[] labels) {

		int[] colors = new int[5];
		colors[0] = Color.argb(255, 11, 55, 144);
		colors[1] = Color.argb(255, 48, 120, 254);
		colors[2] = Color.argb(255, 123, 179, 254);
		colors[3] = Color.argb(255, 230, 230, 230);
		colors[4] = Color.argb(0, 255, 255, 255);
		
		ArrayList<BarEntry> dataEntries = new ArrayList<BarEntry>();
		

		for (int i = 0; i < values.length; ++i) {
			float[] fList = new float[values[i].length];
			
			for(int j=0;j<values[i].length;++j){
				fList[j] = (float) values[i][j];
			}

			dataEntries.add(new BarEntry(fList,i));
		}
		BarDataSet dataSet = new BarDataSet(dataEntries, "");
		dataSet.setBarSpacePercent(2f);
		dataSet.setStackLabels(new String[]{"浅睡眠","深睡眠","活动"});
		dataSet.setColors(new int[]{colors[0],colors[1],colors[2]});
				
		dataSet.setDrawValues(false);
		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		dataSets.add(dataSet);	
		BarData barData = new BarData(labels, dataSets);
		barChart.setData(barData);
		
		YAxis leftAxis = barChart.getAxisLeft();
		leftAxis.setDrawGridLines(true);
		leftAxis.setDrawLabels(false);
		leftAxis.setGridColor(colors[3]);
		leftAxis.setDrawAxisLine(true);
		leftAxis.setAxisLineColor(colors[3]);
		leftAxis.setAxisLineWidth(0.5f);
		YAxis rightAxis = barChart.getAxisRight();
		rightAxis.setEnabled(true);
		rightAxis.setDrawLabels(false);
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
		barChart.setDescription("");
		barChart.animateXY(2000, 2000);
		barChart.notifyDataSetChanged();


		
	}

}
