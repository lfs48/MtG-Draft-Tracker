package com.example.mtgdrafttracker;

import com.github.mikephil.charting.formatter.DefaultValueFormatter;

public class CmcChartValueFormatter extends DefaultValueFormatter {

    public CmcChartValueFormatter(int digits) {
        super(digits);
    }

    @Override
    public String getFormattedValue(float value) {
        if (value == 0) {
            return "";
        } else {
            return mFormat.format(value);
        }
    }

}
