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
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(0f,0f));
        entries.add(new BarEntry(1f,0f));
        entries.add(new BarEntry(2f,0f));
        entries.add(new BarEntry(3f,0f));
        entries.add(new BarEntry(4f,0f));
        entries.add(new BarEntry(5f,0f));

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        BarData data = new BarData(set);
        data.setBarWidth(0.75f);
        chart.setData(data);
    }

}
