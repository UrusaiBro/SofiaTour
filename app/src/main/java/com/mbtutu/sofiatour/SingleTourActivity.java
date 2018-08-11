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
import android.support.v4.widget.NestedScrollView;
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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.firestore.GeoPoint;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class SingleTourActivity extends AppCompatActivity implements OnMapReadyCallback {

    ListView lv;
    private GoogleMap mMap;
    Resources res;

    MapView mv;


    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_tour);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        ImageView toolbarLayoutImgview = findViewById(R.id.toolbar_layout_imgview);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        TextView titleText = findViewById(R.id.titleTextView);
        //TextView desc_text = findViewById(R.id.description);



        res = getApplicationContext().getResources();



        // getting extra shit from the intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        //String desc = intent.getStringExtra("desc");
        String picurl = intent.getStringExtra("picurl");



        // setting data
        toolbarLayoutImgview.setImageResource(res.getIdentifier(picurl, "drawable", getPackageName()));
        toolbarLayout.setTitle(title);
        titleText.setText(title);
        //desc_text.setText(desc);



        // mapView stuff


        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        mv = findViewById(R.id.mapView);
        mv.onCreate(mapViewBundle);
        mv.getMapAsync(this);
        RelativeLayout mvc = findViewById(R.id.mapClickView);
        mvc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SingleTourActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });



        // initializing extreme listview in scrollview hack
        lv = findViewById(R.id.testList);
        lv.setOnTouchListener(new View.OnTouchListener() {
            // Setting on Touch Listener for handling the touch inside ScrollView
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                // Disallow the touch request for parent scroll on touch of child view
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });
        loadListView();
        setListViewHeightBasedOnChildren(lv);
    }



    /**** Method for Setting the Height of the ListView dynamically.
     **** Hack to fix the issue of not showing all the items of the ListView
     **** when placed inside a ScrollView  ****/
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
            return;

        int desiredWidth = View.MeasureSpec.makeMeasureSpec(listView.getWidth(), View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
                view.setLayoutParams(new ViewGroup.LayoutParams(desiredWidth, ViewGroup.LayoutParams.WRAP_CONTENT));

            view.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
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


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(sydney.latitude, sydney.longitude), 12.0f));

        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setZoomGesturesEnabled(false);
    }





    @Override
    protected void onResume() {
        super.onResume();
        mv.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mv.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mv.onStop();
    }
    @Override
    protected void onPause() {
        mv.onPause();
        super.onPause();
    }
    @Override
    protected void onDestroy() {
        mv.onDestroy();
        super.onDestroy();
    }
    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mv.onLowMemory();
    }



}
