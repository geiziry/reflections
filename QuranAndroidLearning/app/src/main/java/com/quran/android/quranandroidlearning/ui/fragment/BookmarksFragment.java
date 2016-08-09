package com.quran.android.quranandroidlearning.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quran.android.quranandroidlearning.R;
import com.quran.android.quranandroidlearning.data.Constants;
import com.quran.android.quranandroidlearning.ui.helpers.QuranListAdapter;
import com.quran.android.quranandroidlearning.ui.helpers.QuranRow;

/**
 * Created by HP_Spectre on 7/31/2016.
 */
public class BookmarksFragment extends Fragment implements
        QuranListAdapter.QuranTouchListener{

        RecyclerView mRecyclerView;
    private QuranListAdapter mBookmarksAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.quran_list, container, false);

        final Context context = getActivity();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        mBookmarksAdapter = new QuranListAdapter(context, mRecyclerView, new QuranRow[0], true);
        mBookmarksAdapter.setQuranTouchListener(this);
        mRecyclerView.setAdapter(mBookmarksAdapter);
        return view;
    }

    @Override
    public void onClick(QuranRow row, int position) {

    }

    @Override
    public boolean onLongClick(QuranRow row, int position) {
        return false;
    }

    public static BookmarksFragment newInstance() {
        return new BookmarksFragment();
    }

}
