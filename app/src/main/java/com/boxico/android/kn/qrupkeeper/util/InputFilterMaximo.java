package com.boxico.android.kn.qrupkeeper.util;

import android.graphics.Color;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.TextView;

public class InputFilterMaximo implements InputFilter {
    private double max;
    private TextView tv;

    public InputFilterMaximo(String max, TextView tv) {
        this.max = Double.parseDouble(max);
        this.tv = tv;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        try {
            String temp = dest.toString() + source.toString();
            double input = Double.parseDouble(dest.toString() + source.toString());
            if(source.length() == 0){// Se borró un caracter
                temp = dest.toString();
                String primeraParte = temp.substring(0,dstart);
                String segundaParte = temp.substring(dstart + 1, temp.length());
                temp = primeraParte + segundaParte;
                input = Double.parseDouble(temp);
            }
            if(temp.length() <= ConstantsAdmin.maxValoresNumericos) {
                if (isInRange(input)) {
                    tv.setTextColor(Color.BLACK);
                    tv.setBackgroundColor(Color.WHITE);
                    tv.setTextSize(10);
                } else {
                    tv.setTextColor(Color.WHITE);
                    tv.setBackgroundColor(Color.RED);
                    tv.setTextSize(11);
                }
                return null;
            }else{
                return "";
            }
        } catch (NumberFormatException nfe) { }
        return null;
    }

    private boolean isInRange(double input) {
        return input <= max;
    }
}
