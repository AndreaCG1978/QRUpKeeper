package com.boxico.android.kn.qrupkeeper.util;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMinimo implements InputFilter {
    private double min;

    public InputFilterMinimo(double min) {
        this.min = min;
    }

    public InputFilterMinimo(String min) {
        this.min = Double.parseDouble(min);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            double input = Double.parseDouble(dest.toString() + source.toString());
            if (isInRange(input))
                return null;
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(double input) {
        return input >= min;
    }
}
