package com.kangkang.androidchart.demoactivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.util.ArrayList;

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

public class BodyFatAnalysisCurveActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_fat_analysis_curve);
		
		double[][] values = { { 61.0, 74.0, 66.0, 63.0, 52.0, 60.0, 64.0},
							  { 31.0, 24.0, 26.0, 33.0, 32.0, 26.0, 24.0},
							  { 22.0, 24.0, 16.0, 17.0, 19.0, 17, 20 } };
		String[] labels = { "22" , "23","24", "25",  "26", "27", "28" };
		LineChart lineChart = (LineChart) findViewById(R.id.bodyFatAnalysisCurve);

		setChart(lineChart, values, labels);
		
		
	}

	private void setChart(LineChart myChart, double[][] values, String[] labels) {
		
		int[] colors = new int[8];
		colors[0] = Color.argb(255, 61, 180, 195);
		colors[1] = Color.argb(255, 239, 192, 58);
		colors[2] = Color.argb(255, 151, 107, 177);
		colors[3] = Color.argb(255, 101, 101, 101);
		colors[4] = Color.argb(255, 170, 170, 170);
		colors[5] = Color.argb(255, 244, 55, 43);
		colors[6] = Color.argb(255, 0, 0, 246);
		colors[6] = Color.argb(205, 255, 255, 255);
		colors[7] = Color.argb(0, 255, 255, 255);


		ArrayList<ArrayList<Entry>> dataEntries = new ArrayList<ArrayList<Entry>>();
		for (int i = 0; i < values.length; ++i) {
			ArrayList<Entry> entries = new ArrayList<Entry>();
			for (int j = 0; j < values[i].length; ++j) {
				entries.add(new Entry((float) values[i][j], j));
			}
			dataEntries.add(entries);
		}

		ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		dataSets.add(new LineDataSet(dataEntries.get(0), "体重(kg)"));
		dataSets.add(new LineDataSet(dataEntries.get(1), "体脂率"));
		dataSets.add(new LineDataSet(dataEntries.get(2), "BMI"));
		
		for (int i = 0; i < dataSets.size(); ++i) {
			dataSets.get(i).setColor(colors[i]);
			dataSets.get(i).setDrawCircleHole(true);
			dataSets.get(i).setCircleColor(colors[i]);
			dataSets.get(i).setCircleSize(5f);
			dataSets.get(i).setDrawValues(false);
			dataSets.get(i).setDrawFilled(true);
			dataSets.get(i).setFillColor(colors[i]);
			dataSets.get(i).setFillAlpha(75);
			dataSets.get(i).setLineWidth(1);
		}

		LineData lineData = new LineData(labels, dataSets);

		myChart.setData(lineData);
		
				
		XAxis xAxis = myChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(true);
		xAxis.setGridLineWidth(0.5f);
		xAxis.setGridColor(colors[3]);
		xAxis.setDrawAxisLine(true);
		xAxis.setAxisLineColor(colors[4]);
		xAxis.setAxisLineWidth(1f);
		
		xAxis.setTextColor(Color.BLACK);
		xAxis.setSpaceBetweenLabels(1);
		xAxis.setAvoidFirstLastClipping(true);
		xAxis.setLabelsToSkip(0);
		xAxis.setTextSize(15f);
		xAxis.setSpaceBetweenLabels(1);
		myChart.getAxisRight().setEnabled(false);
		
		YAxis leftAxis = myChart.getAxisLeft();		
		leftAxis.setDrawGridLines(true);
		leftAxis.setStartAtZero(false);
		leftAxis.setAxisMinValue(0);
		leftAxis.setAxisMaxValue(100);
		leftAxis.setDrawAxisLine(true);
		leftAxis.setGridLineWidth(0.5f);
		leftAxis.setTextSize(14);
		leftAxis.setTextColor(colors[4]);
		leftAxis.setLabelCount(14, true);
		leftAxis.setValueFormatter(new MyYAxisValueFormatter());
		// target line
		LimitLine targetLine185 = new LimitLine(18.5f, "18.5");
		LimitLine targetLine249 = new LimitLine(24.9f, "24.9");
		targetLine185.setLineColor(colors[6]);
		targetLine185.setLineWidth(0.5f);
		targetLine185.setTextColor(colors[7]);
		targetLine185.setTextSize(12);
		
		targetLine249.setLineColor(colors[5]);
		targetLine249.setLineWidth(0.5f);
		targetLine249.setTextColor(colors[7]);
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
