package com.quran.android.quranandroidlearning.ui.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.preference.CheckBoxPreference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.quran.android.quranandroidlearning.R;

/**
 * Created by HP_Spectre on 7/30/2016.
 */
public class QuranCheckBoxPreference extends CheckBoxPreference {
    public QuranCheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    public QuranCheckBoxPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        if (isEnabled()) {
            final TextView tv = (TextView) view.findViewById(android.R.id.title);
            if (tv != null) {
                tv.setTextColor(Color.WHITE);
            }
        }
    }
}
