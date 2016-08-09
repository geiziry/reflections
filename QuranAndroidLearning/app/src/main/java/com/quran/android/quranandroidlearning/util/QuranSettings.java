package com.quran.android.quranandroidlearning.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;

import com.quran.android.quranandroidlearning.data.Constants;

/**
 * Created by HP_Spectre on 7/30/2016.
 */
public class QuranSettings {
    private static final String PREFS_FILE = "com.quran.android.quranandroidlearning.pre_installation";
    private static QuranSettings sInstance;
    private final SharedPreferences mPrefs;
    private final SharedPreferences mPrefInstallationPrefs;
    private boolean arabicNames;

    public QuranSettings(@NonNull Context appContext) {
        mPrefs = PreferenceManager.getDefaultSharedPreferences(appContext);
        mPrefInstallationPrefs = appContext.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);

    }

    public static synchronized QuranSettings getInstance(@NonNull Context context) {
        if (sInstance == null) {
            sInstance = new QuranSettings(context.getApplicationContext());
        }
        return sInstance;
    }

    public boolean isArabicNames() {
        return mPrefs.getBoolean(Constants.PREF_USE_ARABIC_NAMES,false);
    }
}
