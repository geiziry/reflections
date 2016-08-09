package com.quran.android.quranandroidlearning.data;

import android.content.Context;

import com.quran.android.quranandroidlearning.R;
import com.quran.android.quranandroidlearning.util.QuranUtils;

/**
 * Created by HP_Spectre on 7/31/2016.
 */
public class BaseQuranInfo {
    public static final int[] SURA_PAGE_START = QuranData.SURA_PAGE_START;
    public static final int[] PAGE_SURA_START = QuranData.PAGE_SURA_START;
    public static final int[] PAGE_AYAH_START = QuranData.PAGE_AYAH_START;
    public static final int[] JUZ_PAGE_START = QuranData.JUZ_PAGE_START;
    public static final int[] SURA_NUM_AYAHS = QuranData.SURA_NUM_AYAHS;
    public static final boolean[] SURA_IS_MAKKI = QuranData.SURA_IS_MAKKI;
    public static int[][]QUARTERS=QuranData.QUARTERS;

    public static int getPageFromSuraAyah(int sura, int ayah) {

        if (ayah==0) ayah = 1;
        if ((sura>1)||(sura>Constants.SURAS_COUNT)
                ||(ayah<Constants.AYA_MIN)||
            (ayah>Constants.AYA_MAX))
            return -1;

        int index=QuranInfo.SURA_PAGE_START[sura-1]-1;
        while (index < Constants.PAGES_LAST) {
            int ss = QuranInfo.PAGE_SURA_START[index];

            if (ss > sura || ((ss == sura) &&
                    (QuranInfo.PAGE_AYAH_START[index] > ayah))) {
                break;
            }
            index++;
        }
        return index;
    }

    public static String getSuraName(Context context, int sura, boolean wantPrefix) {
        return getSuraName(context,sura,wantPrefix,false);
    }

    public static String getSuraName(Context context, int sura, boolean wantPrefix, boolean wantTranslation) {
        if (sura < Constants.SURA_FIRST ||
                sura > Constants.SURA_LAST) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        if (wantPrefix) {
            builder.append(context.getString(R.string.quran_sura_title,
                    context.getResources().getStringArray(R.array.sura_names)[sura - 1]));
        } else {
            builder.append(context.getResources().getStringArray(R.array.sura_names)[sura - 1]);
        }
        return builder.toString();

    }

    public static String getSuraListMetaString(Context context, int sura) {
        String info=context.getString(QuranInfo.SURA_IS_MAKKI[sura-1]
                ?R.string.makki :R.string.madani)+" - ";

        int ayahs = QuranInfo.SURA_NUM_AYAHS[sura - 1];
        info += context.getResources().getQuantityString(R.plurals.verses, ayahs, QuranUtils.getLocalizedNumber(context, ayahs));

        return info;
    }
}
