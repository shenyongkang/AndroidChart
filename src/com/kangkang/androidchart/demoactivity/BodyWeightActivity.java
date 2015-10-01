package com.kangkang.androidchart.demoactivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.kangkang.androidchart.R;
import com.kangkang.androidchart.demoactivity.BodyFatMonitorActivity.MyYAxisValueFormatter;

public class BodyWeightActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_weight);
		double[][] values = { { 71.0, 74.0, 66.0, 83.0, 42.0, 76.0, 74.0 },
				 { 22.0, 24.0, 23.0, 23.0, 22.0, 24.0, 26.60 } };
		String[] labels = { "6" , "7","8", "9",  "10", "11", "12" };
		LineChart lineChart = (LineChart) findViewById(R.id.bodyWeight);

		setChart(lineChart, values, labels);
	}

	private void setChart(LineChart myChart, double[][] values, String[] labels) {

		int[] colors = new int[8];
		colors[0] = Color.argb(255, 11, 209, 80);
		colors[1] = Color.argb(255, 75, 189, 210);
		colors[2] = Color.argb(255, 249, 153, 38);
		colors[3] = Color.argb(205, 30, 250, 30);
		colors[4] = Color.argb(205, 250, 30, 30);
		colors[5] = Color.argb(255, 30, 30, 30);
		colors[6] = Color.argb(255, 244, 170, 103);
		colors[7] = Color.argb(0, 255, 255, 255);
		ArrayList<String> dataLabels = new ArrayList<String>();
		for (int i = 0; i < labels.length; ++i) {
			dataLabels.add("");
			dataLabels.add(labels[i]);
		}
		dataLabels.add("");

		ArrayList<ArrayList<Entry>> dataEntries = new ArrayList<ArrayList<Entry>>();
		for (int i = 0; i < values.length; ++i) {
			ArrayList<Entry> entries = new ArrayList<Entry>();
			for (int j = 0; j < values[i].length; ++j) {
				entries.add(new Entry((float) values[i][j], 2 * j + 1));
			}
			dataEntries.add(entries);
		}

		ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		dataSets.add(new LineDataSet(dataEntries.get(0), "ÌåÖØ"));
		dataSets.add(new LineDataSet(dataEntries.get(1), "BMI"));
		for (int i = 0; i < dataSets.size(); ++i) {
			dataSets.get(i).setColor(colors[i]);
			dataSets.get(i).setDrawCircleHole(false);
			dataSets.get(i).setCircleColor(colors[i]);
			dataSets.get(i).setCircleSize(3f);
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
		xAxis.setTextSize(15f);
		xAxis.setSpaceBetweenLabels(1);
		myChart.getAxisRight().setEnabled(false);
		
		YAxis leftAxis = myChart.getAxisLeft();
		leftAxis.setStartAtZero(false);
		leftAxis.setDrawGridLines(false);
		leftAxis.setAxisMinValue(10);
		leftAxis.setAxisMaxValue(100);
		leftAxis.setDrawAxisLine(false);
		leftAxis.setTextSize(14);
		leftAxis.setTextColor(colors[6]);
		leftAxis.setLabelCount(7, true);
		leftAxis.setValueFormatter(new MyYAxisValueFormatter());
		// target line
		LimitLine targetLine185 = new LimitLine(18.5f, "BMI 18.5");
		LimitLine targetLine249 = new LimitLine(24.9f, "BMI 24.9");
		targetLine185.setLineColor(colors[3]);
		targetLine185.setLineWidth((float) 0.5);
		targetLine185.setTextColor(colors[6]);
		targetLine185.setTextSize(12);
		
		targetLine249.setLineColor(colors[4]);
		targetLine249.setLineWidth((float) 0.5);
		targetLine249.setTextColor(colors[4]);
		targetLine249.setTextSize(12);
		leftAxis.addLimitLine(targetLine249);
		leftAxis.addLimitLine(targetLine185);
		myChart.setDescription("");
		myChart.setScaleEnabled(false);
		myChart.getLegend().setFormSize(12f);
		myChart.animateXY(2000, 2000);
		myChart.notifyDataSetChanged();

	}

	public class MyYAxisValueFormatter implements YAxisValueFormatter {

		private DecimalFormat mFormat;

		public MyYAxisValueFormatter() {
			mFormat = new DecimalFormat("#");
		}

		@Override
		public String getFormattedValue(float value, YAxis yAxis) {

			return mFormat.format(value);
		}

	}

}
