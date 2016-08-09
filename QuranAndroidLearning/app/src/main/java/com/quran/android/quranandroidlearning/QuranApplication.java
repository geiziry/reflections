package com.quran.android.quranandroidlearning;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;

import com.quran.android.quranandroidlearning.component.ApplicationComponent;
import com.quran.android.quranandroidlearning.component.DaggerApplicationComponent;
import com.quran.android.quranandroidlearning.module.ApplicationModule;
import com.quran.android.quranandroidlearning.util.QuranSettings;

import java.util.Locale;

/**
 * Created by HP_Spectre on 7/30/2016.
 */
public class QuranApplication extends Application {

    private ApplicationComponent applicationComponent;

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public void refreshLocale(@NonNull Context context, boolean force) {
        final String language= QuranSettings.getInstance(this).isArabicNames()
                ?"ar":null;

        final Locale locale;

        if ("ar".equals(language)) {
            locale = new Locale("ar");
        } else if (force) {
            locale = Resources.getSystem().getConfiguration().locale;
        } else {
            return;
        }

        updateLocale(context, locale);
        final Context appContext = context.getApplicationContext();
        if (context != appContext) {
            updateLocale(appContext,locale);
        }
    }

    private void updateLocale(Context context, Locale locale) {
        final Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();
        config.locale = locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLayoutDirection(config.locale);
        }
        resources.updateConfiguration(config,resources.getDisplayMetrics());
    }
}
