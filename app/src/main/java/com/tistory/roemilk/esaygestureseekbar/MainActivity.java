package com.tistory.roemilk.esaygestureseekbar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tistory.roemilk.esaygestureseekbar.GestureDetector.EasyGestureDetector;
import com.tistory.roemilk.esaygestureseekbar.GestureDetector.EsayGestureListener;

public class MainActivity extends AppCompatActivity implements EsayGestureListener{
    public static final String TAG = "MainActivity";

    private GestureDetector mGestureDetector;
    private EasyGestureDetector mEasyGestureDetector;

    private TextView mGestureTextView;
    private SeekBar mSeekBar;
    private int mScaleProgress = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGestureTextView = (TextView)findViewById(R.id.geture_textView);
        mSeekBar = (SeekBar)findViewById(R.id.seekBar);

        setEsayGestureDetector(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }

            switch (event.getAction()) {
                case MotionEvent.ACTION_UP:
                    if (mEasyGestureDetector.isScrolling()) {
                        mGestureTextView.setVisibility(View.GONE);
                        mEasyGestureDetector.setScrolling(false);
                        mSeekBar.setProgress(mScaleProgress);
                    }
                    break;
            }
        return false;
    }

    //Gesture Seek Control
    private void setEsayGestureDetector(EsayGestureListener listener) {
        mEasyGestureDetector = new EasyGestureDetector(getBaseContext(), this);
        mGestureDetector = new GestureDetector(this, mEasyGestureDetector);
    }

    private void updateSwipeProgressBar(float delta) {
        mScaleProgress = extractDeltaScale(mSeekBar.getWidth(), delta, mSeekBar);
        if(mGestureTextView.getVisibility() != View.VISIBLE){
            mGestureTextView.setVisibility(View.VISIBLE);
        }
        mGestureTextView.setText("" + mScaleProgress);
        Log.d(TAG, "ScaleProgress : " + mScaleProgress);
    }

    private int extractDeltaScale(int availableSpace, float deltaX, SeekBar seekbar) {
        int x = (int) deltaX;
        float progress = seekbar.getProgress();
        final int max = seekbar.getMax();
        float scale = (float) (x) / (float) max;

        if (x < 0) {
            Log.d(TAG, "back scroll..");
            progress += scale * max;
        } else {
            Log.d(TAG, "forward scroll..");
            progress += scale * max;
        }
        return (int) progress;
    }

    @Override
    public void onTap() {

    }

    @Override
    public void onHorizontalScroll(MotionEvent event, float delta) {
        Log.d(TAG, "onHorizontalScroll");
            updateSwipeProgressBar(delta);
    }

    @Override
    public void onVerticalScroll(MotionEvent event, float delta) {

    }

    @Override
    public void onSwipeRight() {

    }

    @Override
    public void onSwipeLeft() {

    }

    @Override
    public void onSwipeBottom() {

    }

    @Override
    public void onSwipeTop() {

    }

    @Override
    public void onSingleTapUp() {
        Toast.makeText(getBaseContext(), "one TouchEvent..", Toast.LENGTH_SHORT).show();

    }
}
