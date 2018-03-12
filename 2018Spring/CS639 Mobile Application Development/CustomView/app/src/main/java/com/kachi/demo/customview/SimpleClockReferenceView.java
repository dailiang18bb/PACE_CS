package com.kachi.demo.customview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by kachi on 2/22/18.
 */

public class SimpleClockReferenceView extends LinearLayout {


    static final long SECOND_IN_MILLIS	= 1000;
    static final long MINUTE_IN_MILLIS 	= 60 * 1000;
    static final long HOUR_IN_MILLIS 	= 60 * 60 *1000;

    public static final long INTERVAL_SECOND = SECOND_IN_MILLIS;
    private static final long[] TIMES_IN_MILLIS = {HOUR_IN_MILLIS, MINUTE_IN_MILLIS, SECOND_IN_MILLIS};


    public static final int DEFAULT_COLOR = Color.parseColor("#cccccc");
    public static final double FONT_RATIO = 72.0/dpToPx(80);

    public int mTextColor = DEFAULT_COLOR;

    LinearLayout.LayoutParams mChildParams;

    private long mStartTime;
    private Runnable mUpdateRunnable = new Runnable() {

        @Override
        public void run() {
            updateClockAndRepeat();
        }
    };


    public SimpleClockReferenceView(Context context) {
        super(context);
        init();
    }

    public SimpleClockReferenceView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Demo);
        mTextColor = typedArray.getColor(R.styleable.Demo_textColor, DEFAULT_COLOR);
        typedArray.recycle();
        init();
    }

    private void init() {
        configLayout();
        mStartTime = 0;
    }

    private void configLayout() {
        setOrientation(LinearLayout.HORIZONTAL);
        mChildParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT);
        mChildParams.weight = 1;
        createClockLayout();
    }

    private void createClockLayout() {
        for(int i=0; i < 3; i++) {
            for(int j=0; j < 2; j++) {
                TextView timeNumber = new TextView(getContext());
                timeNumber.setTextSize(72);
                timeNumber.setText(R.string.number_zero);
                timeNumber.setTextColor(mTextColor);
                timeNumber.setGravity(Gravity.CENTER);
                this.addView(timeNumber, mChildParams);
            }

            if (i < 2) {
                TextView colon = new TextView(getContext());
                colon.setTextSize(72);
                colon.setTextColor(mTextColor);
                colon.setText(R.string.colon);
                colon.setGravity(Gravity.CENTER);
                LinearLayout.LayoutParams colonParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                this.addView(colon, colonParams);
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        int fontSize = (int)(FONT_RATIO * getHeight());
        for(int i=0, size=getChildCount(); i < size; i++) {
            View child = getChildAt(i);
            if(child instanceof TextView) {
                ((TextView) child).setTextSize(fontSize);
            }
        }
    }

    public void start() {
        removeCallbacks(mUpdateRunnable); //remove the runnable. We're resetting things
        mStartTime = System.currentTimeMillis();
        updateTime();

        postDelayed(mUpdateRunnable, INTERVAL_SECOND); //re attach it
    }

    public void stop() {
        removeCallbacks(mUpdateRunnable);
    }

    /**
     * Updates the time associated with the flip clock and performs a flip animation if need be.
     * If a particular time of interest has been reached, then do nothing.
     */
    private void updateClockAndRepeat() {
        updateTime();
        postDelayed(mUpdateRunnable, INTERVAL_SECOND);
    }

    private void updateTime() {
        long time = Math.max(0, (System.currentTimeMillis() - mStartTime));

        CharSequence timeString = convertTimeToString(time); //00:00:02
        //00:00:02 -> [0, 0, :, 0, 0, :, 0, 0]
        int viewIndex = 0;
        int stringIndex = 0;
        while(viewIndex < getChildCount()) {
            char newChar = timeString.charAt(stringIndex);
            TextView clockNumberView = (TextView) getChildAt(viewIndex);
            clockNumberView.setText(newChar + "");

            //update indexes
            viewIndex++;
            stringIndex++;
        }
    }

    private String convertTimeToString(long newTime) {

        String[] intervals = new String[4];

        for(int i=0, len=TIMES_IN_MILLIS.length; i < len; i++) {
            int interval = (int)(newTime/TIMES_IN_MILLIS[i]);
            intervals[i]= (interval > 9 ? "" : "0") + interval;
            newTime = newTime % TIMES_IN_MILLIS[i];
        }
        return intervals[0] + ":" + intervals[1] + ":" + intervals[2]; //00:00:02
    }

    private static int dpToPx(int dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpValue, Resources.getSystem().getDisplayMetrics());
    }

}
