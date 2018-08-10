package com.mbtutu.sofiatour;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.firestore.GeoPoint;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class SingleTourActivity extends AppCompatActivity {

    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_tour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ImageView toolbarLayoutImgview = findViewById(R.id.toolbar_layout_imgview);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        //TextView desc_text = findViewById(R.id.description);


        Intent intent = getIntent();
        //String title = intent.getStringExtra("title");
        //String desc = intent.getStringExtra("desc");
        String picurl = intent.getStringExtra("picurl");

        Resources res = getApplicationContext().getResources();
        int picid = res.getIdentifier(picurl, "drawable", getPackageName());
        toolbarLayoutImgview.setImageResource(picid);

        //desc_text.setText(desc);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        lv = findViewById(R.id.testList);
        /*lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });*/

        loadListView();

    }

    private void loadListView(){
        final ArrayList<Sight> result = new ArrayList<>();

        Resources res = getApplicationContext().getResources();
        String[] sight_titles = res.getStringArray(res.getIdentifier("sight_titles", "array", getPackageName()));
        String[] sight_descs = res.getStringArray(res.getIdentifier("sight_descs", "array", getPackageName()));
        String[] sight_pic_ids = res.getStringArray(res.getIdentifier("sight_pic_ids", "array", getPackageName()));

        for(int i=0; i<4; i++){

            String name = sight_titles[i];
            String description = sight_descs[i];
            String pictureUrl = sight_pic_ids[i];
            double price = 1;
            GeoPoint coordinates = null;
            result.add(new Sight(name, description, pictureUrl, price, coordinates));

        }


        lv.setAdapter(new ImageListViewAdapter(SingleTourActivity.this, result));
    }


}
