package com.example.mtgdrafttracker;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    static ArrayList<BarEntry> entries;
    static BarEntry cmc1entry, cmc2entry, cmc3entry, cmc4entry, cmc5entry, cmc6entry, last;
    static Stack<BarEntry> history;
    static HorizontalBarChart cmcChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cmcChart = findViewById(R.id.cmcChart);
        styleChart(cmcChart);
        initializeChart(cmcChart);
        history = new Stack<BarEntry>();
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
        chart.getAxisLeft().setAxisMaxValue(10f);
    }

    protected void initializeChart(HorizontalBarChart chart) {
        entries = new ArrayList<BarEntry>();
        cmc1entry = new BarEntry(5f,0f);
        cmc2entry = new BarEntry(4f,0f);
        cmc3entry = new BarEntry(3f,0f);
        cmc4entry = new BarEntry(2f,0f);
        cmc5entry = new BarEntry(1f,0f);
        cmc6entry = new BarEntry(0f,0f);
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

    public void handleAdd1Button(View view) {
        float y = cmc1entry.getY() + 1f;
        cmc1entry.setY(y);
        cmcChart.invalidate();
        history.push(cmc1entry);
    }

    public void handleAdd2Button(View view) {
        float y = cmc2entry.getY() + 1f;
        cmc2entry.setY(y);
        cmcChart.invalidate();
        history.push(cmc2entry);
    }

    public void handleAdd3Button(View view) {
        float y = cmc3entry.getY() + 1f;
        cmc3entry.setY(y);
        cmcChart.invalidate();
        history.push(cmc3entry);
    }

    public void handleAdd4Button(View view) {
        float y = cmc4entry.getY() + 1f;
        cmc4entry.setY(y);
        cmcChart.invalidate();
        history.push(cmc4entry);
    }

    public void handleAdd5Button(View view) {
        float y = cmc5entry.getY() + 1f;
        cmc5entry.setY(y);
        cmcChart.invalidate();
        history.push(cmc5entry);
    }

    public void handleAdd6Button(View view) {
        float y = cmc6entry.getY() + 1f;
        cmc6entry.setY(y);
        cmcChart.invalidate();
        history.push(cmc6entry);
    }

    public void handleResetButton(View view) {
        cmc1entry.setY(0);
        cmc2entry.setY(0);
        cmc3entry.setY(0);
        cmc4entry.setY(0);
        cmc5entry.setY(0);
        cmc6entry.setY(0);
        cmcChart.invalidate();
    }

    public void handleUndoButton(View view) {
        if (!history.empty()) {
            BarEntry last = history.pop();
            float y = Math.max(0f, last.getY() - 1f);
            last.setY(y);
            cmcChart.invalidate();
        }
    }

}
