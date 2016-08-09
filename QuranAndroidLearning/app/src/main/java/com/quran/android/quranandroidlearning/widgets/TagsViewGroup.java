package com.quran.android.quranandroidlearning.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.nfc.Tag;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.quran.android.quranandroidlearning.BuildConfig;
import com.quran.android.quranandroidlearning.R;

import java.util.List;

/**
 * Created by HP_Spectre on 7/29/2016.
 */
public class TagsViewGroup extends LinearLayout {

    private static final int MAX_TAGS = 6;

    private Context mContext;
    private int mTagWidth;
    private int mTagsMargin;
    private int mTagsTextSize;
    private int mDefaultTagBackgroundColor;
    private int mTagsToShow;
    private List<Tag> mTags;
    private boolean mIsRtl;

    public TagsViewGroup(Context context) {
        this(context, null);
    }


    public TagsViewGroup(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TagsViewGroup(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context,attrs,defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mContext=context;

        Resources resources=context.getResources();
        mTagWidth = resources.getDimensionPixelSize(R.dimen.tag_width);
        mTagsMargin = resources.getDimensionPixelSize(R.dimen.tag_margin);
        mTagsTextSize = resources.getDimensionPixelSize(R.dimen.tag_text_size);
        mDefaultTagBackgroundColor = ContextCompat.getColor(context, R.color.accent_color_dark);
        mTagsToShow=MAX_TAGS;
//        mISRtl = QuranSettings.getInstance(context).isArabicNames();
    }

    public void setTags(List<Tag> tags) {
        removeAllViews();
        mTags = tags;

        for (int i = 0,tagsSize=tags.size();i<tagsSize; i++) {
            Tag tag = tags.get(i);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(mTagWidth, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity = Gravity.CENTER;
            setLeftRightMargin(params, i, Math.min(tagsSize, MAX_TAGS) - 1);

        }

    }

    private void setLeftRightMargin(LayoutParams params, int position, int maxPosition) {
        if (position == 0) {
            setStartMargin(params, 0);
            setEndMargin(params, maxPosition == 0 ? 0 : mTagsMargin);
        } else if (position == maxPosition) {

        }
    }

    private void setStartMargin(LayoutParams params, int value) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            params.setMarginStart(value);
        } else if (mIsRtl) {
            params.rightMargin = value;
        } else {
            params.leftMargin = value;
        }
    }

    private void setEndMargin(LayoutParams params, int value) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            params.setMarginEnd(value);
        } else if (mIsRtl) {
            params.leftMargin = value;
        } else {
            params.rightMargin = value;
        }
    }


}
