package com.example.abhinav.unitswirl;
import java.io.InputStream;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.util.AttributeSet;
import android.view.View;

import com.example.abhinav.unitswirl.R;

public class AnimatedGifView extends View {

    public InputStream gifInputStream;
    private Movie gifMovie;
    private int movieWidth, movieHeight;
    private long movieDuration;
    public

    long movieRunDuration;
    private long lastTick;
    private long nowTick;

    private boolean repeat = true;
    private boolean running = true;

    public void setRepeat(boolean r) {
        repeat = r;
    }

    public void setRunning(boolean r) {
        running = r;
    }

    public AnimatedGifView(Context context) {
        super(context);
        init(context);
    }

    public AnimatedGifView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimatedGifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {

        // Turn OFF hardware acceleration
        // API Level 11
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);

        setFocusable(true);
        gifInputStream = context.getResources().openRawResource(
                R.drawable.anim3);

        gifMovie = Movie.decodeStream(gifInputStream);
        movieWidth = gifMovie.width();
        movieHeight = gifMovie.height();
        movieDuration = gifMovie.duration();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(movieWidth, movieHeight);
    }

    public int getMovieWidth() {
        return movieWidth;
    }

    public int getMovieHeight() {
        return movieHeight;
    }

    public long getMovieDuration() {
        return movieDuration;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(gifMovie == null){
            return;
        }

        nowTick = android.os.SystemClock.uptimeMillis();
        if (lastTick == 0) { // first time
            movieRunDuration = 0;
        }else{
            if(running){
                movieRunDuration += nowTick-lastTick;
                if(movieRunDuration > movieDuration){
                    if(repeat){
                        movieRunDuration = 0;
                    }else{
                        movieRunDuration = movieDuration;
                    }
                }
            }
        }

        gifMovie.setTime((int)movieRunDuration);
        gifMovie.draw(canvas, 0, 0);

        lastTick = nowTick;
        invalidate();

    }
}