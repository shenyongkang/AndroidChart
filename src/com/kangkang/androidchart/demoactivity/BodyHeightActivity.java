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

public class BodyHeightActivity extends Activity{
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.body_height);
			double[] values = { 41.0, 44.0, 46.0, 53.0, 62.0, 66.0, 74.0 };					
			String[] labels = { "一月", "二月", "三月", "四月", "五月", "六月", "七月" };
			LineChart lineChart = (LineChart) findViewById(R.id.bodyHeight);

			setChart(lineChart, values, labels);
		}

		private void setChart(LineChart myChart, double[] values, String[] labels) {

			int[] colors = new int[8];
			colors[0] = Color.argb(255, 11, 209, 80);
			colors[1] = Color.argb(255, 249, 153, 38);
			colors[2] = Color.argb(255, 75, 189, 210);
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

			ArrayList<Entry> dataEntries = new ArrayList<Entry>();
			for (int i = 0; i < values.length; ++i) {	
				dataEntries.add(new Entry((float) values[i], 2 * i + 1));
				}

			ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
			dataSets.add(new LineDataSet(dataEntries, "身高"));
			dataSets.get(0).setColor(colors[2]);
			dataSets.get(0).setDrawCircleHole(false);
			dataSets.get(0).setCircleColor(colors[2]);
			dataSets.get(0).setCircleSize(3f);
			dataSets.get(0).setDrawValues(false);
			dataSets.get(0).setLineWidth(1);

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
			leftAxis.setAxisMinValue(30);
			leftAxis.setAxisMaxValue(210);
			leftAxis.setDrawAxisLine(false);
			leftAxis.setTextSize(14);
			leftAxis.setTextColor(colors[6]);
			leftAxis.setLabelCount(10, true);
			leftAxis.setValueFormatter(new MyYAxisValueFormatter());

			myChart.setDescription("");
			myChart.setScaleEnabled(false);
			myChart.getLegend().setEnabled(false);
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
