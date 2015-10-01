package com.kangkang.androidchart.demoactivity;

import java.text.DecimalFormat;
import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing.EasingOption;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.Legend.LegendPosition;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.kangkang.androidchart.R;
import com.kangkang.androidchart.demoactivity.BloodSugerMonitorActivity.MyYAxisValueFormatter;

public class BloodSugerAnalysisCurveActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blood_suger_analysis_curve);
		String[] labels = {"22" , "23","24", "25",  "26", "27", "28"};
		double[] values = {4.0, 5.0, 6.0, 5.3, 7.0, 6.0, 4.0};
		LineChart myChart = (LineChart) findViewById(R.id.bloodSugerAnalysisCurve);
		setChart(myChart,labels,values);
	}

	private void setChart(LineChart myChart, String[] labels, double[] values) {
		int[] colors = new int[10];
		colors[0] = Color.argb(230, 224, 70, 70);
		colors[1] = Color.argb(230, 31, 220, 89);
		colors[2] = Color.argb(255, 100, 200, 210);
		colors[3] = Color.argb(205, 101, 101, 101);
		colors[4] = Color.argb(255, 170, 170, 170);
		colors[5] = Color.argb(255, 50, 50, 50);
		colors[6] = Color.argb(230, 224, 70, 70);
		colors[7] = Color.argb(255, 40, 125, 226);
		colors[8] = Color.argb(255, 253, 28, 29);
		colors[9] = Color.argb(255, 253, 154, 82);
		

		
		ArrayList<Entry> dataEntries = new ArrayList<Entry>();
		for(int i=0;i<values.length;++i){
			dataEntries.add(new Entry((float) values[i], i));
		}
		int[] circleColors = new int[values.length];
		for(int i=0;i<values.length;++i){
			if (values[i]>6.1){
				circleColors[i] = colors[0];
			}
			else {
				circleColors[i] = colors[1];
			}
		}
		LineDataSet lineDataSet = new LineDataSet(dataEntries,"ÑªÌÇ");
		lineDataSet.setColor(colors[2]);
		lineDataSet.setCircleColors(circleColors);
		lineDataSet.setDrawCircleHole(true);
		lineDataSet.setCircleSize(5f);
		lineDataSet.setDrawValues(false);
		lineDataSet.setLineWidth(1f);
		lineDataSet.setDrawFilled(true);
		lineDataSet.setFillAlpha(180);
		lineDataSet.setFillColor(colors[2]);
		LineData lineData = new  LineData(labels, lineDataSet);
		myChart.setData(lineData);
		
		XAxis xAxis = myChart.getXAxis();
		xAxis.setDrawGridLines(true);
		xAxis.setGridLineWidth(0.2f);
		xAxis.setGridColor(colors[4]);
		xAxis.setDrawAxisLine(true);
		xAxis.setAxisLineColor(colors[3]);
		xAxis.setAxisLineWidth(1f);
		xAxis.setTextColor(colors[5]);
		xAxis.setTextSize(15f);
		xAxis.setSpaceBetweenLabels(1);
		xAxis.setAvoidFirstLastClipping(true);
		xAxis.setLabelsToSkip(0);
		xAxis.setPosition(XAxisPosition.BOTTOM);
		
		myChart.getAxisRight().setEnabled(false);
		YAxis leftAxis = myChart.getAxisLeft();
		leftAxis.setGridColor(colors[4]);
		leftAxis.setGridLineWidth(0.2f);
		leftAxis.setDrawAxisLine(false);
		
		leftAxis.setTextSize(16f);
		leftAxis.setTextColor(colors[3]);
		leftAxis.setAxisMaxValue(15f);
		leftAxis.setValueFormatter(new MyYAxisValueFormatter());
		
		
		LimitLine targetLine39 = new LimitLine(3.9f, "¿Õ¸¹µÍÑªÌÇ");
		LimitLine targetLine61 = new LimitLine(6.1f, "¿Õ¸¹¸ßÑªÌÇ");
		LimitLine targetLine78 = new LimitLine(7.8f, "²Íºó¸ßÑªÌÇ");
		
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
		
		myChart.setDescription("");
		myChart.getLegend().setPosition(LegendPosition.BELOW_CHART_LEFT);
		myChart.getLegend().setEnabled(false);
		myChart.setScaleEnabled(false);
		myChart.animateXY(2000, 2000, EasingOption.Linear, EasingOption.Linear);
		myChart.notifyDataSetChanged();
		

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
