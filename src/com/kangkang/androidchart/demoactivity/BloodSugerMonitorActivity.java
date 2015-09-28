package com.kangkang.androidchart.demoactivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.kangkang.androidchart.R;

public class BloodSugerMonitorActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blood_suger_monitor);
		
		String[] labels = {"周日" , "周一","周二", "周三",  "周四", "周五", "周六"};
		double[] values = {5.0, 3.5, 5.9, 12.0, 6.40, 8.8, 4.0};
		PieChart pieChart = (PieChart) findViewById(R.id.bloodSugerMonitorChart1);
		LineChart lineChart = (LineChart) findViewById(R.id.bloodSugerMonitorChart2);
		int normalCount = 3;
		int highCount =  2;
		int lowCount = 5;
		setPieChart(pieChart, normalCount,highCount,lowCount);
		setLineChart(lineChart, values, labels);
		
	}

	private void setLineChart(LineChart lineChart, double[] values, String[] labels) {
		int[] colors = new int[10];
		colors[0] = Color.argb(255, 180, 180, 180);
		colors[1] = Color.argb(255, 233, 103, 102);
		colors[2] = Color.argb(255, 101, 232, 133);
		colors[3] = Color.argb(205, 251, 139, 99);
		colors[4] = Color.argb(255, 30, 30, 30);
		colors[5] = Color.argb(255, 244, 170, 103);
		colors[6] = Color.argb(0, 250, 250, 250);
		colors[7] = Color.argb(97, 97, 154, 228);
		colors[8] = Color.argb(255, 226, 106, 92);
		colors[9] = Color.argb(255, 253, 134, 91);
		
		ArrayList<String> dataLabels = new ArrayList<String>();
		for(int i=0;i<labels.length;++i){
			dataLabels.add("");
			dataLabels.add(labels[i]);
		}
		dataLabels.add("");
		
		ArrayList<Entry> dataEntries = new ArrayList<Entry>();
		for(int i=0;i<values.length;++i){
			dataEntries.add(new Entry((float) values[i], 2*i+1));
		}
		int[] circleColors = new int[values.length];
		for(int i=0;i<values.length;++i){
			if (values[i]>7.8){
				circleColors[i] = colors[1];
			}
			else if (values[i]> 3.9){
				circleColors[i] = colors[2];
			}
			else {
				circleColors[i] = colors[3];
			}
		}
		LineDataSet lineDataSet = new LineDataSet(dataEntries,"");
		lineDataSet.setColor(colors[0]);
		lineDataSet.setCircleColors(circleColors);
		lineDataSet.setDrawCircleHole(true);
		lineDataSet.setCircleSize(4f);
		lineDataSet.setDrawValues(false);
		lineDataSet.setLineWidth(1f);
		LineData lineData = new  LineData(dataLabels, lineDataSet);
		lineChart.setData(lineData);
		XAxis xAxis = lineChart.getXAxis();
		xAxis.setDrawGridLines(false);
		xAxis.setDrawAxisLine(false);
		xAxis.setTextColor(colors[4]);
		xAxis.setTextSize(15f);
		xAxis.setSpaceBetweenLabels(1);
		xAxis.setAvoidFirstLastClipping(true);
		xAxis.setLabelsToSkip(0);
		xAxis.setPosition(XAxisPosition.BOTTOM);
		lineChart.getAxisRight().setEnabled(false);
		YAxis leftAxis = lineChart.getAxisLeft();
		leftAxis.setStartAtZero(false);
		leftAxis.setAxisMaxValue(15f);
		leftAxis.setAxisMinValue(3f);
		leftAxis.setDrawAxisLine(false);
		leftAxis.setGridLineWidth(1);
		leftAxis.setLabelCount(7, true);
		leftAxis.setTextSize(16f);
		leftAxis.setTextColor(colors[5]);
		leftAxis.setDrawGridLines(false);
		leftAxis.setValueFormatter(new MyYAxisValueFormatter());
		LimitLine targetLine39 = new LimitLine(3.9f, "3.9");
		LimitLine targetLine61 = new LimitLine(6.1f, "6.1");
		LimitLine targetLine78 = new LimitLine(7.8f, "7.8");
		
		targetLine39.setLineColor(colors[7]);
		targetLine39.setLineWidth((float) 0.5);
		targetLine39.setTextColor(colors[7]);
		targetLine39.setTextSize(12);
		targetLine61.setLineColor(colors[8]);
		targetLine61.setLineWidth((float) 0.5);
		targetLine61.setTextColor(colors[8]);
		targetLine61.setTextSize(12);
		targetLine78.setLineColor(colors[9]);
		targetLine78.setLineWidth((float) 0.5);
		targetLine78.setTextColor(colors[9]);
		targetLine78.setTextSize(12);
		leftAxis.addLimitLine(targetLine78);
		leftAxis.addLimitLine(targetLine61);
		leftAxis.addLimitLine(targetLine39);
		
		lineChart.setDescription("");
		lineChart.getLegend().setPosition(LegendPosition.BELOW_CHART_LEFT);
		lineChart.getLegend().setEnabled(false);
		lineChart.setScaleEnabled(false);
		lineChart.notifyDataSetChanged();
		

		
	}

	private void setPieChart(PieChart pieChart, int normalCount, int highCount, int lowCount) {
		int[] colors = new int[3];
		colors[0] = Color.argb(255, 15, 221, 71);
		colors[1] = Color.argb(255, 253, 99, 28);
		colors[2] = Color.argb(255, 255, 57, 43);
		ArrayList<String> dataLabels = new ArrayList<String>();
		dataLabels.add("正常");
		dataLabels.add("偏低");
		dataLabels.add("偏高");
		int totalCount = normalCount + highCount + lowCount;
		ArrayList<Entry> dataEntries = new ArrayList<Entry>();
		dataEntries.add(new Entry( (float) lowCount / (float) totalCount,1));
		dataEntries.add(new Entry( (float) normalCount / (float) totalCount,0));
		dataEntries.add(new Entry( (float) highCount / (float) totalCount,2));
		PieDataSet pieDataSet = new PieDataSet(dataEntries, "");
		pieDataSet.setColors(colors);
		pieDataSet.setDrawValues(false);
		
		PieData pieData = new PieData(dataLabels, pieDataSet);
		pieChart.setHoleRadius(60f);
		pieChart.setTransparentCircleRadius(65f);
		pieChart.setDescription("");
		pieChart.setDrawSliceText(false);
		pieChart.getLegend().setPosition(LegendPosition.RIGHT_OF_CHART);
		pieChart.getLegend().setTextSize(12);
		pieChart.setData(pieData);
		pieChart.animateY(1500, Easing.EasingOption.EaseInOutQuad);
		pieChart.setTouchEnabled(false);
		pieChart.notifyDataSetChanged();
		
	}
	
	public class MyYAxisValueFormatter implements YAxisValueFormatter{

	    private DecimalFormat mFormat;

	    public MyYAxisValueFormatter () {
	        mFormat = new DecimalFormat("#.#"); // use one decimal
	    }

	    @Override
	    public String getFormattedValue(float value, YAxis yAxis) {
	        // write your logic here
	        // access the YAxis object to get more information
	        return mFormat.format(value); // e.g. append a dollar-sign
	    }

	}

}
