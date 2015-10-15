package com.demo.develop.animationtestproject;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.demo.develop.animationtestproject.constants.ImageSize;

import static com.demo.develop.animationtestproject.constants.ImageSize.PART_OF_SCREEN;

public class MainActivity extends Activity {
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initImageViewSize();

        imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setBackgroundResource(R.drawable.animation);

        AnimationDrawable mAnimationDrawable = (AnimationDrawable) imageView.getBackground();
        mAnimationDrawable.start();
    }

    private void initImageViewSize() {
        final RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.main_relative_layout);
        relativeLayout.getViewTreeObserver()
                .addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

                    @Override
                    public void onGlobalLayout() {
                        int heidht = relativeLayout.getHeight();
                        if (relativeLayout.getViewTreeObserver() != null) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                                relativeLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                            }
                            else{
                                relativeLayout.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                            }
                        }
                        convertPixelsToDp(heidht, getApplicationContext());
                    }
                });
    }

    private float convertPixelsToDp(int px, Context context){
        float dp = px;
        imageView.getLayoutParams().height = (int) dp/PART_OF_SCREEN;
        imageView.getLayoutParams().width = (int) dp/PART_OF_SCREEN;
        return dp;
    }
}
