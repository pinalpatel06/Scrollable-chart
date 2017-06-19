package com.example.asus.chartexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;

import java.util.ArrayList;

/**
 * Created by Tejas Sherdiwala on 6/19/2017.
 * &copy; Knoxpo
 */

public class BarChartActivity extends AppCompatActivity implements View.OnClickListener {

    private BarChart mChart;
    private Button mAddBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_chart);

        mChart = (BarChart) findViewById(R.id.chart_bar);
        // mChart.setOnChartValueSelectedListener(this);
        mChart.getDescription().setEnabled(false);

        mAddBtn = (Button) findViewById(R.id.add_btn);
        mAddBtn.setOnClickListener(this);
//        mChart.setDrawBorders(true);

        // scaling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);

        mChart.setDrawBarShadow(false);

        mChart.setDrawGridBackground(false);
        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        // mChart.setScaleYEnabled(false);
        mChart.setScaleEnabled(false);
        mChart.setScaleXEnabled(true);
        // mChart.setScaleYEnabled(true);

        XAxis xAxis = mChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setEnabled(true);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return String.valueOf((int) value);
            }
        });

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawAxisLine(false);
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        setData();

        mChart.getAxisRight().setEnabled(false);
        mChart.setVisibleXRangeMaximum(7);
        mChart.getLegend().setEnabled(false);


    }



    private void setData() {

        ArrayList<BarEntry> yVals1 = new ArrayList<BarEntry>();
        ArrayList<BarEntry> yVals2 = new ArrayList<BarEntry>();

        float groupSpace = 0.60f;
        float barSpace = 0.00f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet

        int groupCount = 10;
        int startYear = 1980;
        int endYear = startYear + groupCount;


        float randomMultiplier = 2 * 100000f;

        for (int i = startYear; i < endYear; i++) {
            yVals1.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
            yVals2.add(new BarEntry(i, (float) (Math.random() * randomMultiplier)));
        }

        BarDataSet set1, set2;

        // create 4 DataSets
        set1 = new BarDataSet(yVals1, "Company A");
        set1.setColor(Color.parseColor("#75ADE4"));
        set2 = new BarDataSet(yVals2, "Company B");
        set2.setColor(Color.parseColor("#1976D2"));
        set1.setDrawValues(false);
        set2.setDrawValues(false);
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        // data.setValueTypeface(mTfLight);

        mChart.setData(data);


        // specify the width each bar should have
        mChart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        mChart.getXAxis().setAxisMinimum(startYear);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        mChart.getXAxis().setAxisMaximum(startYear + mChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        mChart.groupBars(startYear, groupSpace, barSpace);

        // mChart.invalidate();
    }

    @Override
    public void onClick(View v) {
        float groupSpace = 0.60f;
        float barSpace = 0.00f; // x4 DataSet
        float barWidth = 0.2f; // x4 DataSet

        int groupCount = 11;
        int startYear = 1980;

        BarDataSet set1, set2;
        float randomMultiplier = 2 * 100000f;
        set1 = (BarDataSet) mChart.getData().getDataSetByIndex(0);
        set2 = (BarDataSet) mChart.getData().getDataSetByIndex(1);

        set1.addEntry(new BarEntry(1991, (float) (Math.random() * randomMultiplier)));
        set2.addEntry(new BarEntry(1991, (float) (Math.random() * randomMultiplier)));
        set1.setDrawValues(false);
        set2.setDrawValues(false);
        mChart.clear();
        BarData data = new BarData(set1, set2);
        mChart.setData(data);

        mChart.getBarData().setBarWidth(barWidth);

        // restrict the x-axis range
        mChart.getXAxis().setAxisMinimum(startYear);

        // barData.getGroupWith(...) is a helper that calculates the width each group needs based on the provided parameters
        mChart.getXAxis().setAxisMaximum(startYear + mChart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        mChart.groupBars(startYear, groupSpace, barSpace);

        // mChart.getData().notifyDataChanged();
        //  mChart.notifyDataSetChanged();
        mChart.invalidate();
    }
}
