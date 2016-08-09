package com.quran.android.quranandroidlearning.widgets;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.text.TextUtils;

import com.quran.android.quranandroidlearning.R;

/**
 * Created by HP_Spectre on 7/29/2016.
 */
public class JuzView extends Drawable {

    public static final int TYPE_JUZ = 1;
    public static final int TYPE_HALF = 3;
    public static final int TYPE_THREE_QUARTERS = 4;
    public static final int TYPE_QUARTER = 2;
    private final Paint mCirclePaint;
    private final Paint mcircleBackgroundPaint;

    private final String mOverlayText;
    private final int mPercentage;
    private float mtextOffset;
    private TextPaint mOverlayTextPaint;
    private int mRadius;
    private float mCircleY;
    private RectF mCircleRect;

    public JuzView(Context context, int type, String overlayText) {
        final Resources resources = context.getResources();
        final int circleColor = ContextCompat.getColor(context, R.color.accent_color);
        final int circleBackground = ContextCompat.getColor(context, R.color.accent_color_dark);

        mCirclePaint = new Paint();
        mCirclePaint.setStyle(Paint.Style.FILL);
        mCirclePaint.setColor(circleColor);
        mCirclePaint.setAntiAlias(true);

        mcircleBackgroundPaint = new Paint();
        mcircleBackgroundPaint.setStyle(Paint.Style.FILL);
        mcircleBackgroundPaint.setColor(circleBackground);
        mcircleBackgroundPaint.setAntiAlias(true);

        mOverlayText = overlayText;
        if (!TextUtils.isEmpty(mOverlayText)) {
            final int textColor = ContextCompat.getColor(context, R.color.header_background);
            final int textSize=resources.getDimensionPixelSize(R.dimen.juz_overlay_text_size);

            mOverlayTextPaint = new TextPaint();
            mOverlayTextPaint.setAntiAlias(true);
            mOverlayTextPaint.setColor(textColor);
            mOverlayTextPaint.setTextSize(textSize);
            mOverlayTextPaint.setTextAlign(Paint.Align.CENTER);

            final float textHeight=
                    mOverlayTextPaint.descent()-mOverlayTextPaint.ascent();
            mtextOffset = (textHeight / 2) - mOverlayTextPaint.descent();
        }

        final int percentage;
        switch (type) {
            case TYPE_JUZ:
                percentage=100;
                break;
            case TYPE_THREE_QUARTERS:
                percentage=75;
                break;
            case TYPE_HALF:
                percentage = 50;
                break;
            case TYPE_QUARTER:
                percentage = 25;
                break;
            default:
                percentage = 0;
        }
        mPercentage = percentage;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawCircle(mRadius,mCircleY,mRadius,mcircleBackgroundPaint);
        canvas.drawArc(mCircleRect,-90,
                (int)(3.6*mPercentage),true,mCirclePaint);
        if (mOverlayTextPaint != null) {
            canvas.drawText(mOverlayText, mCircleRect.centerX(),
                    mCircleRect.centerY() + mtextOffset, mOverlayTextPaint);
        }
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        mRadius = (right - left) / 2;
        final int yOffset = ((bottom - top) - (2 * mRadius)) / 2;
        mCircleY = mRadius + yOffset;
        mCircleRect = new RectF(left, top + yOffset, right, top + yOffset + 2 * mRadius);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }
}
