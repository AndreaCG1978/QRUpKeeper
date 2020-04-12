package com.boxico.android.kn.qrupkeeper.util;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.style.TextAppearanceSpan;
import android.widget.TextView;

public class InputFilterMinimo implements InputFilter {
    private double min;
    private TextView tv;
    private Drawable alerta = null;

    public InputFilterMinimo(String min, TextView tv, Drawable alerta)
    {
        this.min = Double.parseDouble(min);
        this.tv = tv;
        this.alerta = alerta;
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
                    tv.setBackground(alerta);
                    tv.setTextSize(9);
                }
                return null;
            }else{
                return "";
            }
        } catch (NumberFormatException nfe) { }
        return null;
    }

    private boolean isInRange(double input) {
        return input >= min;
    }
}
