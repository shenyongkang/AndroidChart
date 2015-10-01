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
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.kangkang.androidchart.R;

public class BloodSugerAnalysisDiagramActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blood_suger_analysis_diagram);
		BarChart barChart1 = (BarChart) findViewById(R.id.bloodSugerAnalysisDiagramChart1);
		BarChart barChart2 = (BarChart) findViewById(R.id.bloodSugerAnalysisDiagramChart2);
		PieChart pieChart = (PieChart) findViewById(R.id.bloodSugerAnalysisDiagramChart3);
		double[] chart1Values = { 18.0, 15 };
		double[] chart2Values = { 18.0, 15, 24, 20 };

		setChart1(barChart1, chart1Values);
		setchart2(barChart2, chart2Values);
		int normalCount = 3;
		int highCount = 2;
		int lowCount = 5;
		setChart3(pieChart, lowCount, normalCount, highCount);
	}

	private void setChart3(PieChart pieChart, int lowCount, int normalCount,
			int highCount) {
		int[] colors = new int[3];
		colors[0] = Color.argb(255, 255, 36, 35);
		colors[1] = Color.argb(255, 254, 140, 0);
		colors[2] = Color.argb(255, 15, 221, 74);
		// colors[2] = Color.argb(255, 108, 176, 1);
		String[] dataLabels = { "", "", "" };
		int totalCount = lowCount + normalCount + highCount;
		ArrayList<Entry> dataEntries = new ArrayList<Entry>();
		dataEntries.add(new Entry((float) lowCount / (float) totalCount, 1));
		dataEntries.add(new Entry((float) normalCount / (float) totalCount, 0));
		dataEntries.add(new Entry((float) highCount / (float) totalCount, 2));
		PieDataSet pieDataSet = new PieDataSet(dataEntries, "");
		pieDataSet.setColors(colors);
		pieDataSet.setDrawValues(false);

		PieData pieData = new PieData(dataLabels, pieDataSet);
		pieChart.setHoleRadius(80f);
		// pieChart.setTransparentCircleRadius(65f);
		pieChart.setDescription("");
		pieChart.setDrawSliceText(false);
		pieChart.getLegend().setEnabled(false);
		pieChart.setData(pieData);
		pieChart.animateY(2000, Easing.EasingOption.EaseInOutQuad);
		pieChart.setTouchEnabled(false);
		pieChart.notifyDataSetChanged();
	}

	private void setchart2(BarChart barChart2, double[] chart2Values) {
		int[] colors = new int[5];
		colors[0] = Color.argb(255, 15, 227, 74);
		colors[1] = Color.argb(255, 255, 143, 0);
		colors[2] = Color.argb(255, 227, 57, 42);
		colors[3] = Color.argb(255, 227, 57, 42);
		colors[4] = Color.argb(255, 86, 86, 86);
		String[] dataLabels = { "空腹", "餐前", "餐后","睡前" };
		ArrayList<BarEntry> dataEntries = new ArrayList<BarEntry>();

		for (int i = 0; i < chart2Values.length; ++i) {
			dataEntries.add(new BarEntry((float) chart2Values[i], i));

		}

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
		
		dataSets.add(new BarDataSet(dataEntries, ""));
		dataSets.get(0).setBarSpacePercent(65f);
		dataSets.get(0).setColors(colors);
		dataSets.get(0).setDrawValues(true);
		dataSets.get(0).setValueFormatter(new MyValueFormatter());
		dataSets.get(0).setValueTextSize(20);
		BarData barData = new BarData(dataLabels, dataSets);
		barChart2.setData(barData);

		XAxis xAxis = barChart2.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);
		xAxis.setAxisLineWidth(1f);
		xAxis.setAxisLineColor(colors[4]);
		xAxis.setTextSize(17f);
		xAxis.setTextColor(colors[4]);
		xAxis.setLabelsToSkip(0);
		// barChart1.setExtraBottomOffset(10f);
		barChart2.getAxisLeft().setEnabled(false);
		barChart2.getAxisRight().setEnabled(false);
		barChart2.setExtraTopOffset(5f);
		barChart2.setGridBackgroundColor(Color.WHITE);
		barChart2.setBackgroundColor(Color.WHITE);
		barChart2.getLegend().setEnabled(false);
		barChart2.setDescription("");
		barChart2.setTouchEnabled(false);
		barChart2.animateXY(2000, 2000, EasingOption.Linear,
				EasingOption.Linear);

		barChart2.invalidate();

	}

	private void setChart1(BarChart barChart1, double[] chart1Values) {
		int[] colors = new int[3];
		colors[0] = Color.argb(255, 227, 57, 42);
		colors[1] = Color.argb(255, 255, 141, 0);
		colors[2] = Color.argb(255, 86, 86, 86);
		String[] dataLabels = { "最高值", "最低值" };
		ArrayList<BarEntry> dataEntries = new ArrayList<BarEntry>();

		for (int i = 0; i < chart1Values.length; ++i) {
			dataEntries.add(new BarEntry((float) chart1Values[i], i));

		}

		ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();

		dataSets.add(new BarDataSet(dataEntries, ""));
		dataSets.get(0).setBarSpacePercent(65f);
		dataSets.get(0).setColors(colors);
		dataSets.get(0).setDrawValues(true);
		dataSets.get(0).setValueTextSize(20);

		dataSets.get(0).setValueFormatter(new MyValueFormatter());
		BarData barData = new BarData(dataLabels, dataSets);
		barChart1.setData(barData);

		XAxis xAxis = barChart1.getXAxis();
		xAxis.setPosition(XAxisPosition.BOTTOM);
		xAxis.setDrawGridLines(false);
		xAxis.setAxisLineWidth(1f);
		xAxis.setAxisLineColor(colors[2]);
		xAxis.setTextSize(17f);
		xAxis.setTextColor(colors[2]);
		xAxis.setLabelsToSkip(0);
		// barChart1.setExtraBottomOffset(10f);
		barChart1.getAxisLeft().setEnabled(false);
		barChart1.getAxisRight().setEnabled(false);
		barChart1.setExtraTopOffset(5f);
		barChart1.setGridBackgroundColor(Color.WHITE);
		barChart1.setBackgroundColor(Color.WHITE);
		barChart1.getLegend().setEnabled(false);
		barChart1.setDescription("");
		barChart1.setTouchEnabled(false);
		barChart1.animateXY(2000, 2000, EasingOption.Linear,EasingOption.Linear);
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
