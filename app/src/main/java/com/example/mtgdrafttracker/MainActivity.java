package com.example.mtgdrafttracker;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<BarEntry> entries;
    static BarEntry cmc1entry, cmc2entry, cmc3entry, cmc4entry, cmc5entry, cmc6entry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HorizontalBarChart cmcChart = findViewById(R.id.cmcChart);
        styleChart(cmcChart);
        initializeChart(cmcChart);
    }

    protected void styleChart(HorizontalBarChart chart) {
        chart.getAxisLeft().setDrawLabels(false);
        chart.getAxisRight().setDrawLabels(false);
        chart.getXAxis().setDrawLabels(false);
        chart.getAxisLeft().setDrawGridLines(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisRight().setDrawGridLines(false);

        XAxis xAxis = chart.getXAxis();
        xAxis.setEnabled(false);

        YAxis yAxis = chart.getAxisLeft();
        yAxis.setEnabled(false);

        YAxis yAxis2 = chart.getAxisRight();
        yAxis2.setEnabled(false);

        chart.setDrawBorders(false);
        chart.setDrawGridBackground(false);

        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);

        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);

        chart.setPinchZoom(false);
        chart.setAutoScaleMinMaxEnabled(true);
        Legend legend = chart.getLegend();
        legend.setEnabled(false);
    }

    protected void initializeChart(HorizontalBarChart chart) {
        entries = new ArrayList<BarEntry>();
        cmc1entry = new BarEntry(0f,0f);
        cmc2entry = new BarEntry(1f,0f);
        cmc3entry = new BarEntry(2f,0f);
        cmc4entry = new BarEntry(3f,0f);
        cmc5entry = new BarEntry(4f,0f);
        cmc6entry = new BarEntry(5f,0f);
        entries.add(cmc6entry);
        entries.add(cmc5entry);
        entries.add(cmc4entry);
        entries.add(cmc3entry);
        entries.add(cmc2entry);
        entries.add(cmc1entry);

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        BarData data = new BarData(set);
        data.setBarWidth(0.75f);
        chart.setData(data);
    }

}
