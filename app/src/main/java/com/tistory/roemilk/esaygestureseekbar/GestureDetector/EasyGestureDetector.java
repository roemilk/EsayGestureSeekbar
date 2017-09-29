package com.tistory.roemilk.esaygestureseekbar.GestureDetector;

import android.content.Context;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class EasyGestureDetector implements GestureDetector.OnGestureListener{
    private static final String TAG = "FensterGestureListener";

    private int SWIPE_THRESHOLD = 100; //min scroll distance..

    private Context mContext;
    private EsayGestureListener mEsayGestureListener = null;
    private boolean mIsScrolling = false; //스크롤 상태여부

    public EasyGestureDetector(Context context, EsayGestureListener listener) {
        this.mContext = context;
        this.mEsayGestureListener = listener;
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        Log.d(TAG, "onDown..");
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {
        Log.d(TAG, "onShowPress..");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        Log.d(TAG, "onSingleTapUp..");
        mEsayGestureListener.onSingleTapUp(); //one touchEvent..
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        mIsScrolling = true;

        float deltaY = motionEvent1.getY() - motionEvent.getY();
        float deltaX = motionEvent1.getX() - motionEvent.getX();

        if (Math.abs(deltaX) > Math.abs(deltaY)) {
            if (Math.abs(deltaX) > SWIPE_THRESHOLD) {
                mEsayGestureListener.onHorizontalScroll(motionEvent1, deltaX);
                if (deltaX > 0) {
                    Log.i(TAG, "right scroll..");
                } else {
                    Log.i(TAG, "left scroll..");
                }
            }
        } else {
            if (Math.abs(deltaY) > SWIPE_THRESHOLD) {
                mEsayGestureListener.onVerticalScroll(motionEvent1, deltaY);
                if (deltaY > 0) {
                    Log.i(TAG, "down scroll..");
                } else {
                    Log.i(TAG, "up scroll..");
                }
            }
        }
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {
        Log.d(TAG, "onLongPress..");
    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    /**
     * check is scrolling..
     * @return
     */
    public boolean isScrolling(){
        return mIsScrolling;
    }

    /**
     * set is scroll state..
     * @param state
     */
    public void setScrolling(boolean state){
        this.mIsScrolling = state;
    }
}
