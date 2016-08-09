package com.quran.android.quranandroidlearning.ui.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.quran.android.quranandroidlearning.QuranApplication;
import com.quran.android.quranandroidlearning.QuranPreferenceActivity;
import com.quran.android.quranandroidlearning.R;
import com.quran.android.quranandroidlearning.data.Constants;

/**
 * Created by HP_Spectre on 7/30/2016.
 */
public class QuranSettingsFragment extends PreferenceFragment implements
        SharedPreferences.OnSharedPreferenceChangeListener{

    private Context mAppContext;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.quran_preferences);

        final Context context=getActivity();
        mAppContext=context.getApplicationContext();

        ((QuranApplication) mAppContext).getApplicationComponent().inject(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(Constants.PREF_USE_ARABIC_NAMES)) {
            final Context context = getActivity();
            if (context instanceof QuranPreferenceActivity) {
                ((QuranPreferenceActivity) context).restartActivity();
            }
        }

    }
}
