package com.kangkang.androidchart.demoactivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.kangkang.androidchart.R;
import com.kangkang.androidchart.demoactivity.BodyHeightAnalysisActivity.MyYAxisValueFormatter;

public class TemperatureActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.temperature);
		double[] yValues = { 37.0, 37.5, 36.8, 37.2, 36.20, 36.0, 37.4 };
		int[] xValues = { 1, 3, 5, 7, 9, 21, 23 };
		LineChart myChart = (LineChart) findViewById(R.id.temperature);
		setChart(myChart, xValues, yValues);

	}

	private void setChart(LineChart myChart, int[] xValues, double[] yValues) {

		int[] colors = new int[5];
		colors[0] = Color.argb(255, 75, 189, 210);
		colors[1] = Color.argb(255, 170, 170, 170);
		colors[2] = Color.argb(255, 244, 170, 103);
		colors[3] = Color.argb(255, 30, 30, 30);
		colors[4] = Color.argb(0, 255, 255, 255);

		ArrayList<Entry> dataEntries = new ArrayList<Entry>();
		for (int i = 0; i < yValues.length; ++i) {

			dataEntries.add(new Entry((float) yValues[i], xValues[i]));
		}

		ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		dataSets.add(new LineDataSet(dataEntries, "ÌוÎÂ"));

		dataSets.get(0).setColor(colors[0]);
		dataSets.get(0).setDrawCircleHole(false);
		dataSets.get(0).setCircleColor(colors[0]);
		dataSets.get(0).setCircleSize(3f);
		dataSets.get(0).setDrawValues(false);
		dataSets.get(0).setLineWidth(1);

		String[] dataLabels = { "0", "1", "2", "3", "4", "5", "6", "7", "8",
				"9", "10", "11", "12", "13", "14", "15", "16", "17", "18",
				"19", "20", "21", "22", "23", "24" };

		LineData lineData = new LineData(dataLabels, dataSets);

		myChart.setData(lineData);

		XAxis xAxis = myChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(true);
		xAxis.setGridColor(colors[1]);
		xAxis.setGridLineWidth(0.4f);
		xAxis.setDrawAxisLine(false);
		xAxis.setTextColor(colors[3]);
		xAxis.setSpaceBetweenLabels(1);
		xAxis.setAvoidFirstLastClipping(true);
		// xAxis.setLabelsToSkip(0);
		xAxis.setTextSize(15f);
		xAxis.setSpaceBetweenLabels(1);
		myChart.getAxisRight().setEnabled(false);

		YAxis leftAxis = myChart.getAxisLeft();
		leftAxis.setStartAtZero(false);
		leftAxis.setAxisMinValue(35.5f);
		leftAxis.setAxisMaxValue(41.5f);
		leftAxis.setDrawGridLines(true);
		leftAxis.setGridColor(colors[1]);
		leftAxis.setGridLineWidth(0.4f);
		leftAxis.setLabelCount(13, true);

		leftAxis.setDrawAxisLine(false);
		leftAxis.setTextSize(14);
		leftAxis.setTextColor(colors[2]);
		leftAxis.setValueFormatter(new MyYAxisValueFormatter());

		myChart.setDescription("");
		myChart.setScaleEnabled(false);
		myChart.setBackgroundColor(colors[4]);
		myChart.setGridBackgroundColor(colors[4]);
		myChart.getLegend().setEnabled(false);
		myChart.animateX(2000);
//		myChart.animateXY(2000, 2000, EasingOption., easingY);
		myChart.notifyDataSetChanged();

	}

	public class MyYAxisValueFormatter implements YAxisValueFormatter {

		private DecimalFormat mFormat;

		public MyYAxisValueFormatter() {
			mFormat = new DecimalFormat("#.#");
		}

		@Override
		public String getFormattedValue(float value, YAxis yAxis) {

			return mFormat.format(value);
		}

	}

}
