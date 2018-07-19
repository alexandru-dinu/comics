package com.example.alex.comics;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {

    PhotoView photoView;

    XKCD xkcd;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        photoView = findViewById(R.id.photo_view);
        photoView.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onSwipeRight() {
                loadComic();
            }

            @Override
            public void onSwipeLeft() {
                loadComic();
            }
        });

        xkcd = new XKCD();
        loadComic();

    }

    private void loadComic() {
        String link = xkcd.getDirectComicLink();

        assert link != null;

        Picasso.with(this).load(link).into(photoView);
    }
}
