package com.quran.android.quranandroidlearning.ui;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quran.android.quranandroidlearning.R;
import com.quran.android.quranandroidlearning.data.QuranInfo;
import com.quran.android.quranandroidlearning.ui.helpers.QuranListAdapter;
import com.quran.android.quranandroidlearning.ui.helpers.QuranRow;
import com.quran.android.quranandroidlearning.util.QuranUtils;

import static com.quran.android.quranandroidlearning.data.Constants.*;

public class SuraListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private QuranRow[] elements;

    public static SuraListFragment newInstance() {
        return new SuraListFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view =
                inflater.inflate(R.layout.quran_list, container, false);

        final Context context = getActivity();
        mRecyclerView =
                (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        final QuranListAdapter adapter =
                new QuranListAdapter(context, mRecyclerView, getSuraList(), true);
        mRecyclerView.setAdapter(adapter);
        return view;


    }

    public QuranRow[] getSuraList() {
        int next;
        int pos = 0;
        int sura = 1;
        QuranRow[] elements = new QuranRow[SURAS_COUNT + JUZ2_COUNT];

        Activity activity = getActivity();
        boolean wantPrefix = activity.getResources().getBoolean(R.bool.show_surat_prefix);
        boolean wantTranslation = activity.getResources().getBoolean(R.bool.show_sura_names_translation);

        for (int juz = 1; juz <= JUZ2_COUNT; juz++) {
            final String headerTitle =
                    activity.getString(R.string.juz2_description,
                            QuranUtils.getLocalizedNumber(activity, juz));

            final QuranRow.Builder headerBuilder = new QuranRow.Builder()
                    .withType(QuranRow.HEADER)
                    .withText(headerTitle)
                    .withPage(QuranInfo.JUZ_PAGE_START[juz - 1]);

            elements[pos++] = headerBuilder.build();
            next = (juz == JUZ2_COUNT) ? PAGES_LAST + 1 : QuranInfo.JUZ_PAGE_START[juz];

            while ((sura <= SURAS_COUNT) && (QuranInfo.SURA_PAGE_START[sura - 1] < next)) {
                final QuranRow.Builder builder = new QuranRow.Builder()
                        .withText(QuranInfo.getSuraName(activity, sura, wantPrefix, wantTranslation))
                        .withMetadata(QuranInfo.getSuraListMetaString(activity, sura))
                        .withSura(sura)
                        .withPage(QuranInfo.SURA_PAGE_START[sura - 1]);

                elements[pos++] = builder.build();
                sura++;
            }
        }
        return elements;

    }
}
