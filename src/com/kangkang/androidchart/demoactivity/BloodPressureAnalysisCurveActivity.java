package com.kangkang.androidchart.demoactivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import com.github.mikephil.charting.animation.Easing.EasingOption;
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

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

public class BloodPressureAnalysisCurveActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blood_pressure_analysis_curve);
		double[][] values = {
				{ 141.0, 134.0, 126.0, 133.0, 122.0, 136.0, 134.0 },
				{ 81.0, 94.0, 116.0, 83.0, 112.0, 126.0, 84.0 },
				{ 72.0, 64.0, 86.0, 73.0, 92.0, 76, 60 } };
		String[] labels = { "22" , "23","24", "25",  "26", "27", "28" };
		LineChart myChart = (LineChart) findViewById(R.id.bloodPressureAnalysisCurve);
		
		setChart(myChart, values, labels);
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
	
	
	
	private void setChart(LineChart myChart, double[][] values, String[] labels) {
		int[] colors = new int[8];
		colors[0] = Color.argb(255, 61, 180, 195);
		colors[1] = Color.argb(255, 239, 192, 58);
		colors[2] = Color.argb(255, 151, 107, 177);
		colors[3] = Color.argb(255, 101, 101, 101);
		colors[4] = Color.argb(255, 170, 170, 170);
		colors[5] = Color.argb(255, 244, 55, 43);
		colors[6] = Color.argb(230, 244, 70, 70);
		colors[7] = Color.argb(0, 255, 255, 255);

			
		ArrayList<ArrayList<Entry>> dataEntries = new ArrayList<ArrayList<Entry>>();
		for(int i=0;i<values.length;++i){
			ArrayList<Entry> entries = new ArrayList<Entry>();
			for(int j=0;j<values[i].length;++j){
				entries.add(new Entry((float) values[i][j], j));
			}
			dataEntries.add(entries);
		}
		
		//value circle colors
		ArrayList<ArrayList<Integer>> circleColors = new ArrayList<ArrayList<Integer>>();
		for(int i=0;i<values.length;++i){
			ArrayList<Integer> circleColorsSub = new ArrayList<Integer>();
			for(int j=0;j<values[i].length;++j){
				if(i==0 && values[i][j]>140 || i==1 && values[i][j]>90){
					circleColorsSub.add(colors[6]);
				}
				else{
					circleColorsSub.add(colors[i]);
				}
			}
			circleColors.add(circleColorsSub);
		}
		
		ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		dataSets.add(new LineDataSet(dataEntries.get(0), "收缩压"));
		dataSets.add(new LineDataSet(dataEntries.get(1), "舒张压"));
		dataSets.add(new LineDataSet(dataEntries.get(2), "心率"));
		for(int i=0;i<dataSets.size();++i){
			dataSets.get(i).setColor(colors[i]);
			dataSets.get(i).setDrawCircleHole(true);
			dataSets.get(i).setCircleColors(circleColors.get(i));
			dataSets.get(i).setCircleSize(5f);
			dataSets.get(i).setDrawValues(false);
			dataSets.get(i).setDrawFilled(true);
			dataSets.get(i).setFillColor(colors[i]);
			dataSets.get(i).setFillAlpha(51);
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
//		xAxis.setLabelsToSkip(0);
		xAxis.setTextSize(15f);
		xAxis.setSpaceBetweenLabels(1);
		myChart.getAxisRight().setEnabled(false);
		YAxis leftAxis = myChart.getAxisLeft();
		
//		leftAxis.setStartAtZero(false);
		leftAxis.setDrawGridLines(true);
		leftAxis.setGridColor(colors[3]);
		leftAxis.setGridLineWidth(0.5f);
		leftAxis.setAxisMaxValue(220);
		leftAxis.setDrawAxisLine(false);
		leftAxis.setTextSize(14);
		leftAxis.setTextColor(colors[4]);
		leftAxis.setLabelCount(11, false);
		leftAxis.setValueFormatter(new MyYAxisValueFormatter());
		//target line
		LimitLine targetLine90 = new LimitLine(90,"舒张压");
		LimitLine targetLine140 = new LimitLine(140,"收缩压");
		targetLine90.setLineColor(colors[5]);
		targetLine90.setLineWidth((float) 0.5);
		targetLine90.setTextColor(colors[5]);
		targetLine90.setTextSize(12);
		targetLine140.setLineColor(colors[5]);
		targetLine140.setLineWidth((float) 0.5);
		targetLine140.setTextColor(colors[5]);
		targetLine140.setTextSize(12);
		leftAxis.addLimitLine(targetLine140);
		leftAxis.addLimitLine(targetLine90);
		myChart.setDescription("");
		myChart.setScaleEnabled(false);
		myChart.getLegend().setFormSize(12f);
		myChart.animateXY(2000, 2000, EasingOption.Linear, EasingOption.Linear);
		
		
		
		myChart.notifyDataSetChanged();
		
	}

}
