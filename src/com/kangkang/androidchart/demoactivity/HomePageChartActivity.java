package com.kangkang.androidchart.demoactivity;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.kangkang.androidchart.R;

public class HomePageChartActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_page_chart);
		

		int[] data = {0, 13, 118, 113, 120, 1121, 1629, 2150, 2280, 3120, 3150, 3170, 3190, 4201, 4203, 
				4528, 4640, 4751, 5263, 5271, 5290, 5300, 5408, 5420, 5420};
		ArrayList<Float> values = new ArrayList<Float>();
		for(int i=0;i<data.length;++i){
			values.add(Float.valueOf(data[i]));
		}
		LineChart myChart = (LineChart) findViewById(R.id.homePageChart);
		
		setChart(myChart, values);
		
		
		

		

	}

	private void setChart(LineChart myChart, ArrayList<Float> values) {
		ArrayList<String> dataLabels = new ArrayList<String>();
		for(int i=0;i<= 24;++i){
			dataLabels.add(i+":00");
		}
		ArrayList<Entry> dataEntries = new ArrayList<Entry>();
		for(int i=0;i<dataLabels.size();++i){
			dataEntries.add(new Entry( (float) (values.get(i)/1000.0), i));
			
		}
		LineDataSet lineDataSet = new LineDataSet(dataEntries,"Data Set 1");
		
		int[] colors = new int[3];
//		t colors = [UIColor(red: 77/255, green: 190/255, blue: 206/255, alpha: 0.9),
//		            UIColor(red: 150/255, green: 150/255, blue: 150/255, alpha: 0.9),
//		            UIColor(red: 50/255, green: 50/255, blue: 50/255, alpha: 1),]
		colors[0] = Color.argb(230, 77, 190, 206);
		colors[1] = Color.argb(230, 150, 150, 150);
		colors[2] = Color.argb(255, 50, 50, 50);
		
		lineDataSet.setColor(colors[0]);
		lineDataSet.setDrawCircleHole(false);
		lineDataSet.setCircleSize((float) 0.0);
		lineDataSet.setDrawValues(false);
		lineDataSet.setLineWidth((float) 1.0);
		lineDataSet.setDrawFilled(true);
		lineDataSet.setFillColor(colors[0]);
		lineDataSet.setFillAlpha(200);
		
		
		myChart.getXAxis().setPosition(XAxisPosition.BOTTOM);
		myChart.getXAxis().setDrawGridLines(false);
		myChart.getXAxis().setAxisLineColor(colors[2]);
		myChart.getXAxis().setAxisLineWidth((float) 2.0);
		myChart.getXAxis().setTextSize(12);
		myChart.getXAxis().setLabelsToSkip(5);
		myChart.getAxisLeft().setEnabled(true);
		myChart.getAxisRight().setEnabled(false);
		myChart.getAxisLeft().setDrawAxisLine(false);
		myChart.getAxisLeft().setGridColor(colors[1]);
		myChart.getAxisLeft().setGridLineWidth((float) 0.5);
		myChart.getAxisLeft().setLabelCount(3, false);
		myChart.getAxisLeft().setTextSize(12);
		myChart.getAxisLeft().setTextColor(colors[1]);
		
		

		
		
		
		
		
		ArrayList<LineDataSet> dataSets = new ArrayList<LineDataSet>();
		dataSets.add(lineDataSet);
		LineData lineData = new LineData(dataLabels, dataSets);
		myChart.setData(lineData);
		myChart.notifyDataSetChanged();
		
	}

}
