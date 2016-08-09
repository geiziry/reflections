package com.quran.android.quranandroidlearning;

import android.content.Intent;

import com.quran.android.quranandroidlearning.ui.QuranActionBarActivity;

/**
 * Created by HP_Spectre on 7/30/2016.
 */
public class QuranPreferenceActivity extends QuranActionBarActivity {
    public void restartActivity() {
        ((QuranApplication) getApplication()).refreshLocale(this, true);
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
