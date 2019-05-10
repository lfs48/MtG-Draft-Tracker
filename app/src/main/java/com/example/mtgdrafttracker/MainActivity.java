package com.example.mtgdrafttracker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    static ArrayList<BarEntry> entries;
    static BarEntry cmc1entry, cmc2entry, cmc3entry, cmc4entry, cmc5entry, cmc6entry, last;
    static Stack<int[]> history;
    static HorizontalBarChart cmcChart;
    static int colorIndex = 0;
    static float max = 10f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cmcChart = findViewById(R.id.cmcChart);
        styleChart(cmcChart);
        initializeChart(cmcChart);
        history = new Stack<int[]>();
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
        chart.getAxisLeft().setAxisMaxValue(max);

        chart.setDrawValueAboveBar(false);
    }

    protected void initializeChart(HorizontalBarChart chart) {
        entries = new ArrayList<BarEntry>();
        cmc1entry = new BarEntry(5f, new float[] {0f, 0f, 0f, 0f, 0f} );
        cmc2entry = new BarEntry(4f, new float[] {0f, 0f, 0f, 0f, 0f} );
        cmc3entry = new BarEntry(3f, new float[] {0f, 0f, 0f, 0f, 0f} );
        cmc4entry = new BarEntry(2f, new float[] {0f, 0f, 0f, 0f, 0f} );
        cmc5entry = new BarEntry(1f, new float[] {0f, 0f, 0f, 0f, 0f} );
        cmc6entry = new BarEntry(0f, new float[] {0f, 0f, 0f, 0f, 0f} );

        entries.add(cmc1entry);
        entries.add(cmc2entry);
        entries.add(cmc3entry);
        entries.add(cmc4entry);
        entries.add(cmc5entry);
        entries.add(cmc6entry);

        BarDataSet set = new BarDataSet(entries, "BarDataSet");
        set.setValueTextSize(20);
        set.setValueTextColor(Color.GRAY);
        int mtgWhite = Color.rgb(255,252,214);
        int mtgBlue = Color.rgb(14, 104, 171);
        int mtgBlack = Color.rgb(21, 11, 0);
        int mtgRed = Color.rgb(211, 32, 42);
        int mtgGreen = Color.rgb(0, 115, 62);
        int[] colorClasses = new int[] {mtgWhite, mtgBlue, mtgBlack, mtgRed, mtgGreen};
        set.setColors(colorClasses);
        BarData data = new BarData(set);
        data.setValueFormatter(new CmcChartValueFormatter(0));
        data.setBarWidth(0.75f);
        chart.setData(data);
    }

    protected void addToChart(int index) {
        BarEntry entry = entries.get(index);
        if (entry.getY() == max - 1) {
            updateMax();
        }
        float[] y = entry.getYVals();
        y[colorIndex] = y[colorIndex] + 1;
        entry.setVals(y);
        cmcChart.invalidate();
        history.push(new int[]{index, colorIndex});
    }

    protected void updateMax() {
        max = max * 2;
        cmcChart.getAxisLeft().setAxisMaxValue(max);
        cmcChart.invalidate();
    }

    public void handleAdd1Button(View view) {
        addToChart(0);
    }

    public void handleAdd2Button(View view) {
        addToChart(1);
    }

    public void handleAdd3Button(View view) {
        addToChart(2);
    }

    public void handleAdd4Button(View view) {
        addToChart(3);
    }

    public void handleAdd5Button(View view) {
        addToChart(4);
    }

    public void handleAdd6Button(View view) {
        addToChart(5);
    }

    public void handleResetButton(View view) {
        new AlertDialog.Builder(this)
                .setTitle("Reset?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        reset();
                    }})
                .setNegativeButton(android.R.string.no, null).show();
    }

    protected void reset() {
        cmc1entry.setVals(new float[] {0f, 0f, 0f, 0f, 0f});
        cmc2entry.setVals(new float[] {0f, 0f, 0f, 0f, 0f});
        cmc3entry.setVals(new float[] {0f, 0f, 0f, 0f, 0f});
        cmc4entry.setVals(new float[] {0f, 0f, 0f, 0f, 0f});
        cmc5entry.setVals(new float[] {0f, 0f, 0f, 0f, 0f});
        cmc6entry.setVals(new float[] {0f, 0f, 0f, 0f, 0f});
        max = 10;
        cmcChart.getAxisLeft().setAxisMaxValue(max);
        cmcChart.invalidate();
    }

    public void handleUndoButton(View view) {
        if (!history.empty()) {
            int[] last = history.pop();
            BarEntry lastEntry = entries.get(last[0]);
            int lastColorIndex = last[1];

            float[] y = lastEntry.getYVals();
            y[lastColorIndex] = y[lastColorIndex] - 1;
            lastEntry.setVals(y);
            cmcChart.invalidate();
        }
    }

    public void handleWhiteButton(View view) {
        colorIndex = 0;
    }

    public void handleBlueButton(View view) {
        colorIndex = 1;
    }

    public void handleBlackButton(View view) {
        colorIndex = 2;
    }

    public void handleRedButton(View view) {
        colorIndex = 3;
    }

    public void handleGreenButton(View view) {
        colorIndex = 4;
    }

    public void handleSuggestion(View view) {

    }

}
