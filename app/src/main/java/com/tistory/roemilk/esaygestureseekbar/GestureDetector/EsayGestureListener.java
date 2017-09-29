package com.tistory.roemilk.esaygestureseekbar.GestureDetector;

import android.view.MotionEvent;

public interface EsayGestureListener {
    void onTap();
    void onHorizontalScroll(MotionEvent event, float delta);
    void onVerticalScroll(MotionEvent event, float delta);
    void onSwipeRight();
    void onSwipeLeft();
    void onSwipeBottom();
    void onSwipeTop();
    void onSingleTapUp();
}
