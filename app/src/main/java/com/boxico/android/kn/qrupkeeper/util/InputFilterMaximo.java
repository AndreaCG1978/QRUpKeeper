package com.boxico.android.kn.qrupkeeper.util;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMaximo implements InputFilter {
    private double max;

    public InputFilterMaximo(double max) {
        this.max = max;
    }

    public InputFilterMaximo(String max) {
        this.max = Double.parseDouble(max);
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
        return input <= max;
    }
}
