package com.example.alex.comics;

import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        loadComic();
    }

    public void buttonOnClick(View v) {
        loadComic();
    }

    private void loadComic() {
        BufferedReader br = null;
        String link = null;

        try {
            URLConnection con = new URL("https://c.xkcd.com/random/comic/").openConnection();
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String line;
            while ((line = br.readLine()) != null) {
                int i = line.indexOf("https://imgs.xkcd.com");

                if (i >= 0) {
                    link = line.substring(i, line.length());
                    break;
                }
            }
        }
        catch (Exception e) {

        }
        finally {
            if (br != null) {
                try {
                    br.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        assert link != null;

        PhotoView photoView = findViewById(R.id.photo_view);
        Picasso.with(this).load(link).into(photoView);
    }

    private void loadFromURL(String url) {
        Picasso.with(this).load(url).placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {

                    }
                });
    }
}
