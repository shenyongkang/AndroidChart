package com.kangkang.androidchart.demoactivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.kangkang.androidchart.R;

public class BodyFatAnalysisDiagramActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_fat_analysis_diagram);
		BarChart barChart1 = (BarChart) findViewById(R.id.bloodFatAnalysisDiagramChart1);
		BarChart barChart2 = (BarChart) findViewById(R.id.bloodFatAnalysisDiagramChart2);
		PieChart pieChart = (PieChart) findViewById(R.id.bloodFatAnalysisDiagramChart3);
		double[][] chart1Values = { { 60.0, 30}, {64.3, 30} };
		double[][] chart2Values = { { 65.0, 30.1}, {65.0, 30}, {63.0, 30 } };

		setChart1(barChart1, chart1Values);
		setchart2(barChart2, chart2Values);
		int normalCount = 3;
		int highCount = 2;
		int lowCount = 5;
		setChart3(pieChart,lowCount,normalCount,highCount );
	}

	private void setChart3(PieChart pieChart, int lowCount, int normalCount,
			int highCount) {
		int[] colors = new int[3];
		colors[0] = Color.argb(255, 255, 36, 35);
		colors[1] = Color.argb(255, 254, 140, 0);
		colors[2] = Color.argb(255,15, 221, 74);
//		colors[2] = Color.argb(255, 108, 176, 1);
		String[] dataLabels = { "", "","" };
		int totalCount = lowCount+normalCount+highCount;
		ArrayList<Entry> dataEntries = new ArrayList<Entry>();
		dataEntries.add(new Entry( (float) lowCount / (float) totalCount,1));
		dataEntries.add(new Entry( (float) normalCount / (float) totalCount,0));
		dataEntries.add(new Entry( (float) highCount / (float) totalCount,2));
		PieDataSet pieDataSet = new PieDataSet(dataEntries, "");
		pieDataSet.setColors(colors);
		pieDataSet.setDrawValues(false);
		
		PieData pieData = new PieData(dataLabels, pieDataSet);
		pieChart.setHoleRadius(80f);
//		pieChart.setTransparentCircleRadius(65f);
		pieChart.setDescription("");
		pieChart.setDrawSliceText(false);
		pieChart.getLegend().setEnabled(false);
		pieChart.setData(pieData);
		pieChart.animateY(2000, Easing.EasingOption.EaseInOutQuad);
		pieChart.setTouchEnabled(false);
		pieChart.notifyDataSetChanged();	
		}

	
	private void setchart2(BarChart barChart2, double[][] values) {
		int[] colors = new int[3];
		colors[0] = Color.argb(255, 73, 190, 203);
		colors[1] = Color.argb(255, 255, 136, 3);
		colors[2] = Color.argb(255,154, 108, 176);
//		colors[2] = Color.argb(255, 108, 176, 1);
		
		String[] dataLabels = { "早上", "晚上","其他" };
		ArrayList<ArrayList<BarEntry>> dataEntries = new ArrayList<ArrayList<BarEntry>>();
		
		for (int i = 0; i < values[0].length; ++i) {
			dataEntries.add(new ArrayList<BarEntry>());
		}
		for (int i = 0; i < values.length; ++i) {
			for (int j = 0; j < values[i].length; ++j) {
				dataEntries.get(j).add(new BarEntry((float) values[i][j], i));
			}
		}
		
		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();

		for (int i = 0; i < dataEntries.size(); ++i) {
			dataSets.add(new BarDataSet(dataEntries.get(i), ""));
			dataSets.get(i).setBarSpacePercent(40f);
			dataSets.get(i).setColor(colors[i]);
			dataSets.get(i).setDrawValues(true);
			dataSets.get(i).setValueFormatter(new MyValueFormatter());
			dataSets.get(i).setValueTextSize(16f);
			}
		
		BarData barData = new BarData(dataLabels, dataSets);
		barData.setGroupSpace(120f);
		barChart2.setData(barData);

		XAxis xAxis = barChart2.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);
		xAxis.setAxisLineWidth(1f);
		xAxis.setAxisLineColor(Color.BLACK);
		xAxis.setTextSize(17f);
		xAxis.setTextColor(Color.BLACK);
		xAxis.setLabelsToSkip(0);
//		barChart1.setExtraBottomOffset(10f);
		barChart2.getAxisLeft().setEnabled(false);
		barChart2.getAxisRight().setEnabled(false);
		barChart2.setGridBackgroundColor(Color.WHITE);
		barChart2.setBackgroundColor(Color.WHITE);
		barChart2.getLegend().setEnabled(false);
		barChart2.setDescription("");
		barChart2.setExtraTopOffset(10f);
		barChart2.setTouchEnabled(false);
		barChart2.animateXY(2000, 2000, EasingOption.Linear, EasingOption.Linear);

		barChart2.invalidate();

	}

	private void setChart1(BarChart barChart1, double[][] values) {
		int[] colors = new int[3];
		colors[0] = Color.argb(255, 73, 190, 203);
		colors[1] = Color.argb(255, 255, 136, 3);
		colors[2] = Color.argb(255,60, 60, 60);
		String[] dataLabels = { "最高值", "最低值"};
		
		ArrayList<ArrayList<BarEntry>> dataEntries = new ArrayList<ArrayList<BarEntry>>();
		for (int i = 0; i < values[0].length; ++i) {
			dataEntries.add(new ArrayList<BarEntry>());
		}

		for (int i = 0; i < values.length; ++i) {
			for (int j = 0; j < values[i].length; ++j) {
				dataEntries.get(j).add(new BarEntry((float) values[i][j], i));
			}
		}


		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();

		for (int i = 0; i < dataEntries.size(); ++i) {
			dataSets.add(new BarDataSet(dataEntries.get(i), ""));
			dataSets.get(i).setBarSpacePercent(40f);
			dataSets.get(i).setColor(colors[i]);
			dataSets.get(i).setDrawValues(true);
			dataSets.get(i).setValueFormatter(new MyValueFormatter());
			dataSets.get(i).setValueTextSize(16f);
			
		}
		BarData barData = new BarData(dataLabels, dataSets);
		barData.setGroupSpace(120f);
		barChart1.setData(barData);

		XAxis xAxis = barChart1.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);
		xAxis.setAxisLineWidth(1f);
		xAxis.setAxisLineColor(colors[2]);
		xAxis.setTextSize(17f);
		xAxis.setTextColor(colors[2]);
		xAxis.setLabelsToSkip(0);
//		barChart1.setExtraBottomOffset(10f);
		barChart1.getAxisLeft().setEnabled(false);
		barChart1.getAxisRight().setEnabled(false);
		barChart1.setGridBackgroundColor(Color.WHITE);
		barChart1.setBackgroundColor(Color.WHITE);
		barChart1.getLegend().setEnabled(false);
		barChart1.setDescription("");
		barChart1.setTouchEnabled(false);
		barChart1.setExtraTopOffset(10f);
		barChart1.animateXY(2000, 2000, EasingOption.Linear, EasingOption.Linear);;
		barChart1.invalidate();

	}
	
	public class MyValueFormatter implements ValueFormatter{

	    private DecimalFormat mFormat;

	    public MyValueFormatter () {
	        mFormat = new DecimalFormat("#"); // use one decimal
	    }
	    
		@Override
		public String getFormattedValue(float value, Entry entry,
				int dataSetIndex, ViewPortHandler viewPortHandler) {
			return mFormat.format(value);
		}

	}

}
