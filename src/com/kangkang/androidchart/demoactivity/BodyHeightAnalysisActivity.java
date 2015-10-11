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
import com.kangkang.androidchart.demoactivity.BodyWeightActivity.MyYAxisValueFormatter;

public class BodyHeightAnalysisActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_height_analysis);
		double[][] values = { {41.0, 44.0, 46.0, 53.0, 62.0, 66.0, 74.0}, 
				{31.0, 34.0, 36.0, 43.0, 52.0, 55.0, 56.0 } };
		String[] labels = { "6" , "7","8", "9",  "10", "11", "12" };
		LineChart myChart = (LineChart) findViewById(R.id.bodyHeightAnalysis);
		setChart(myChart, values, labels);
		
	}

	private void setChart(LineChart myChart, double[][] values,
			String[] labels) {
		
		int[] colors = new int[7];
		colors[0] = Color.argb(255, 92, 165, 169);
		colors[1] = Color.argb(255, 0, 155, 66);
		colors[2] = Color.argb(255, 93, 195, 200);
		colors[3] = Color.argb(205, 101, 101, 101);
		colors[4] = Color.argb(205, 170, 170, 170);
		colors[5] = Color.argb(255, 30, 30, 30);
		colors[6] = Color.argb(255, 255, 255, 255);


		ArrayList<ArrayList<Entry>> dataEntries = new ArrayList<ArrayList<Entry>>();
		for (int i = 0; i < values.length; ++i) {
			ArrayList<Entry> entries = new ArrayList<Entry>();
			for (int j = 0; j < values[i].length; ++j) {
				entries.add(new Entry((float) values[i][j], j));
			}
			dataEntries.add(entries);
		}

		ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		dataSets.add(new LineDataSet(dataEntries.get(0), "身高曲线"));
		dataSets.add(new LineDataSet(dataEntries.get(1), "同龄曲线"));
		
		for (int i = 0; i < dataSets.size(); ++i) {
			dataSets.get(i).setColor(colors[i]);
			dataSets.get(i).setDrawCircleHole(false);
			dataSets.get(i).setCircleColor(colors[i]);
			dataSets.get(i).setCircleSize(0f);
			dataSets.get(i).setDrawValues(false);
			dataSets.get(i).setLineWidth(1);
			dataSets.get(i).setDrawFilled(true);
			dataSets.get(i).setFillAlpha(123);
			dataSets.get(i).setFillColor(colors[2]);
		}

		LineData lineData = new LineData(labels, dataSets);

		myChart.setData(lineData);
		
				
		XAxis xAxis = myChart.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(true);
		xAxis.setGridColor(colors[4]);
		xAxis.setGridLineWidth(0.4f);
		xAxis.setDrawAxisLine(false);
		xAxis.setTextColor(colors[5]);
		xAxis.setSpaceBetweenLabels(1);
		xAxis.setAvoidFirstLastClipping(true);
//		xAxis.setLabelsToSkip(0);
		xAxis.setTextSize(15f);
		xAxis.setSpaceBetweenLabels(1);
		myChart.getAxisRight().setEnabled(false);
		
		YAxis leftAxis = myChart.getAxisLeft();
		leftAxis.setDrawGridLines(true);
		leftAxis.setGridColor(colors[4]);
		leftAxis.setGridLineWidth(0.4f);
		leftAxis.setLabelCount(7, false);

		leftAxis.setDrawAxisLine(false);
		leftAxis.setTextSize(14);
		leftAxis.setTextColor(colors[3]);
		leftAxis.setValueFormatter(new MyYAxisValueFormatter());
		
		myChart.setDescription("");
		myChart.setScaleEnabled(false);
		myChart.setBackgroundColor(colors[6]);
		myChart.setGridBackgroundColor(colors[6]);
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

