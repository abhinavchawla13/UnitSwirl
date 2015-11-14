package com.example.abhinav.colorswirl;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ViewAnimationUtils;
import android.view.Window;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import java.util.Timer;



public class MainActivity extends Activity {

    public ImageView iv;
    public ScaleGestureDetector SGD;
    public int present_count = 0;
    public String up_value = "1";
    public String animate_in = "off";
    private ProgressBar pBar;
    int progress;
    AnimatedGifView gif;
    AnimatedGifViewReverse gifreverse;
    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        final View myView = findViewById(R.id.iv2);
        myView.setVisibility(View.INVISIBLE);
        ImageView vector = (ImageView) findViewById(R.id.unitVector);
        vector.setImageResource(R.drawable.dolphin);

        pBar = (ProgressBar) findViewById(R.id.pBar);
        pBar.setProgress(7);

        final TextView hint = (TextView) findViewById(R.id.hintView);
        final ImageView hintImage = (ImageView) findViewById(R.id.hintImage);

        gif = (AnimatedGifView) findViewById(R.id.gifview);
        gifreverse = (AnimatedGifViewReverse) findViewById(R.id.gifviewreverse);
        gif.setRunning(false);
        gifreverse.setRunning(false);

        hint.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // have same code as onTouchEvent() (for the Activity) above
                hint.setVisibility(View.INVISIBLE);
                hintImage.setVisibility(View.INVISIBLE);
                return true;
            }
        });

        SGD = new ScaleGestureDetector(this, new ScaleListener());
    }

    public boolean onTouchEvent(MotionEvent ev) {
        SGD.onTouchEvent(ev);
        return true;
    }


    public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override

        public void onScaleEnd(ScaleGestureDetector detector) {
            float dect = detector.getScaleFactor();
            final View myView = findViewById(R.id.iv2);
            TextView unit = (TextView) findViewById(R.id.unitTitle);

            if (dect > 1.0 && present_count > -9) {
                present_count -= 1;
                gif.setRunning(true);
                gif.setRepeat(false);
                gif.movieRunDuration = 0;
                unitTitle();
                animate_out();
            }
            if (dect < 1.0 && present_count < 7) {
                gifreverse.movieRunDuration = 1000;
                present_count += 1;
                gifreverse.setRunning(true);
                gifreverse.setRepeat(false);
                animate_in();
                unitTitle();
            }
            if (present_count > 8){
                present_count = 7;
            }
            if (present_count < -10){
                present_count = -9;
            }
        }

    }
    public void animate_out() {

        final ImageView iv_base = (ImageView) findViewById(R.id.iv_base);
        ColorDrawable drawable_base = (ColorDrawable) iv_base.getBackground();
        int colourNum_base = drawable_base.getColor();
        final ImageView iv_top = (ImageView) findViewById(R.id.iv2);

        if (colourNum_base == -14575885){
            iv_top.setBackgroundColor(Color.parseColor("#2196F3"));
        }
        else if (colourNum_base == -26624){
            iv_top.setBackgroundColor(Color.parseColor("#FF9800"));
        }
        else if (colourNum_base == -12627531){
            iv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
        }
        else if (colourNum_base == -769226){
            iv_top.setBackgroundColor(Color.parseColor("#F44336"));
        }
        else if (colourNum_base == -1499549){
            iv_top.setBackgroundColor(Color.parseColor("#E91E63"));
        }
        // get the center for the clipping circle
        int cx = iv_top.getMeasuredWidth() / 2;
        int cy = iv_top.getMeasuredHeight() / 2;

        // get the initial radius for the clipping circle
        int initialRadius = (int) (iv_top.getWidth()/ 1.1);

        // create the animation (the final radius is zero)
        Animator animator =
                ViewAnimationUtils.createCircularReveal(iv_top, cx, cy, initialRadius, 0);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1300);
        if (iv_top.getVisibility()== View.INVISIBLE){
            iv_top.setVisibility(View.VISIBLE);
        }
        if (colourNum_base == -12627531){
            iv_base.setBackgroundColor(Color.parseColor("#F44336"));
        }
        else if (colourNum_base == -769226){
            iv_base.setBackgroundColor(Color.parseColor("#2196F3"));
        }
        else if (colourNum_base == -14575885){
            iv_base.setBackgroundColor(Color.parseColor("#FF9800"));
        }
        else if (colourNum_base == -26624){
            iv_base.setBackgroundColor(Color.parseColor("#E91E63"));
        }
        else if (colourNum_base == -1499549){
            iv_base.setBackgroundColor(Color.parseColor("#3F51B5"));
        }
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            //                @Override
            public void onAnimationEnd (Animator animation) {
                final ImageView iv_top = (ImageView) findViewById(R.id.iv2);
                super.onAnimationEnd(animation);
                iv_top.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void animate_in() {

        final ImageView iv_base = (ImageView) findViewById(R.id.iv_base);
        final ImageView iv_top = (ImageView) findViewById(R.id.iv2);
        ColorDrawable drawable_iv = (ColorDrawable) iv_top.getBackground();
        final int colourNum_iv = drawable_iv.getColor();

        int cx = iv_top.getMeasuredWidth() / 2;
        int cy = iv_top.getMeasuredHeight() / 2;

        // get the final radius for the clipping circle
        int finalRadius = Math.max(iv_top.getWidth(), iv_top.getHeight());

        // create the animator for this view (the start radius is zero)
        Animator animator =
                ViewAnimationUtils.createCircularReveal(iv_top, cx, cy, 0, finalRadius);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.setDuration(1300);
        iv_top.setVisibility(View.VISIBLE);
        animator.start();

        animator.addListener(new AnimatorListenerAdapter() {
            //                @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                if (colourNum_iv == -26624) {
                    iv_base.setBackgroundColor(Color.parseColor("#FF9800"));
                    iv_top.setVisibility(View.INVISIBLE);
                    iv_top.setBackgroundColor(Color.parseColor("#2196F3"));
                } else if (colourNum_iv == -14575885) {
                    iv_base.setBackgroundColor(Color.parseColor("#2196F3"));
                    iv_top.setVisibility(View.INVISIBLE);
                    iv_top.setBackgroundColor(Color.parseColor("#F44336"));
                } else if (colourNum_iv == -12627531) {
                    iv_base.setBackgroundColor(Color.parseColor("#3F51B5"));
                    iv_top.setVisibility(View.INVISIBLE);
                    iv_top.setBackgroundColor(Color.parseColor("#E91E63"));
                } else if (colourNum_iv == -1499549) {
                    iv_base.setBackgroundColor(Color.parseColor("#E91E63"));
                    iv_top.setVisibility(View.INVISIBLE);
                    iv_top.setBackgroundColor(Color.parseColor("#FF9800"));
                } else if (colourNum_iv == -769226) {
                    iv_base.setBackgroundColor(Color.parseColor("#F44336"));
                    iv_top.setVisibility(View.INVISIBLE);
                    iv_top.setBackgroundColor(Color.parseColor("#3F51B5"));
                }
            }
        });
    }

    public void  unitTitle(){
        TextView unit = (TextView) findViewById(R.id.unitTitle);
        TextView value = (TextView) findViewById(R.id.unitvalue);
        ImageView vector = (ImageView)findViewById(R.id.unitVector);
        TextView example = (TextView) findViewById(R.id.example);
//
        if (present_count == 0){
            unit.setText("Meter (m)");
            value.setText("SI Unit of Length");
            example.setText("The length of an average dolphin is about 3 meters.");
            vector.setImageResource(R.drawable.dolphin);
            pBar.setProgress(7);
        }
        //Bigger units
        else if (present_count == 1){
            unit.setText("Kilometer (km)");
            value.setText("1km = 1000m");
            vector.setImageResource(R.drawable.burj);
            example.setText("The height of the tallest building in the world, Burj Khalifa, " +
                    "is 0.82 km.");
            pBar.setProgress(6);
        }
        else if (present_count == 2){
            unit.setText("Mile (mi)");
            value.setText("1mi = 1609.34m");
            vector.setImageResource(R.drawable.mt);
            example.setText("The height of the tallest mountain above the ocean," +
                    " Mount Everest, is 5.5 miles.");
            pBar.setProgress(5);
        }
        else if (present_count == 3) {
            unit.setText("Nautical mile (NM, nmi)");
            value.setText("1nmi = 1852m");
            vector.setImageResource(R.drawable.ship);
            example.setText("A ship sailing at a speed of 1 knot covered this distance" +
                    " in an hour.");
            pBar.setProgress(4);
        }
        else if (present_count == 4) {
            unit.setText("Megameter (Mm)");
            value.setText("1Mm = 10\u2076m");
            vector.setImageResource(R.drawable.greatwall);
            example.setText("The Great Wall of China is 6.4 mega meters and can be" +
                    " seen from space.");
            pBar.setProgress(3);
        }
        else if (present_count == 5) {
            unit.setText("Earth Radius");
            value.setText("1 Earth Radius = 6.371 * 10\u2076m");
            vector.setImageResource(R.drawable.earth);
            example.setText("As the name says, it's radius of the Earth.");
            pBar.setProgress(2);
        }
        else if (present_count == 6) {
            unit.setText("Astronomical Unit (au)");
            value.setText("1au = 1.496 * 10\u00b9\u00b9m");
            vector.setImageResource(R.drawable.earthsun);
            example.setText("It's taken to be the huge distance between Earth and Sun.");
            pBar.setProgress(1);
        }
        else if (present_count == 7) {
            unit.setText("Light Year (ly)");
            value.setText("1ly = 9.46 * 10\u00b9\u2075m");
            vector.setImageResource(R.drawable.starearth);
            example.setText("Light from the closest star, next to Sun, would " +
                    "travel for 4.3 years to reach Earth.");
            pBar.setProgress(0);
        }

        //Smaller units
        else if (present_count == -1) {
            unit.setText("Yard (yd)");
            value.setText("1yd = 0.9144m");
            vector.setImageResource(R.drawable.football);
            example.setText("Soccer field is ~110 yards.");
            pBar.setProgress(8);
        }
        else if (present_count == -2) {
            unit.setText("Foot (ft)");
            value.setText("1ft = 0.3048m");
            vector.setImageResource(R.drawable.baseball);
            example.setText("The length of baseball bat is 3.5 ft.");
            pBar.setProgress(9);
        }
        else if (present_count == -3) {
            unit.setText("Inch (in)");
            value.setText("1in = 0.0254m");
            vector.setImageResource(R.drawable.golfball);
            example.setText("The diameter of a golf ball is 1.68\".");
            pBar.setProgress(10);
        }
        else if (present_count == -4) {
            unit.setText("Millimeter (mm)");
            value.setText("1mm = 0.001m");
            vector.setImageResource(R.drawable.redant);
            example.setText("The length of average red ant 5 millimeters.");
            pBar.setProgress(11);
        }
        else if (present_count == -5) {
            unit.setText("Micron");
            value.setText("1 Micron = 10\u2212\u2076m");
            vector.setImageResource(R.drawable.rbc);
            example.setText("The diameter of a Red Blood Cell (RBC) is about 7 microns.");
            pBar.setProgress(12);
        }
        else if (present_count == -6) {
            unit.setText("Nanometer (nm)");
            value.setText("1nm = 10\u2212\u2079m");
            vector.setImageResource(R.drawable.beard);
            example.setText("A beard grows at an average 7 nano meters " +
                    "in a second. That small!");
            pBar.setProgress(13);
        }

        else if (present_count == -7) {
            unit.setText("Angstrom");
            value.setText("1 Angstrom = 10\u2212\u00b9\u2070m");
            vector.setImageResource(R.drawable.dna);
            example.setText("The width of Protein \u03B1-Helix 5 angstroms.");
            pBar.setProgress(14);
        }
        else if (present_count == -8) {
            unit.setText("Bohr's Radius (a\u2070)");
            value.setText("1a\u2070 = 10\u2212\u00b9\u00b9");
            vector.setImageResource(R.drawable.hydrogen);
            example.setText("It's the radius of a stable hydrogen atom.");
            pBar.setProgress(15);
        }
        else if (present_count == -9) {
            unit.setText("Planck length");
            value.setText("1 Plank Length = 1.616 * 10\u2212\u00b3\u2075");
            vector.setImageResource(R.drawable.quantum);
            example.setText("It's officially the smallest unit and is used to describe" +
                    " actions of quantum physics.");
            pBar.setProgress(16);
        }

    }

}