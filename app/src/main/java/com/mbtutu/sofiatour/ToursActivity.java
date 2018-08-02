package com.mbtutu.sofiatour;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

public class ToursActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tours);

        ListView listView = findViewById(R.id.testList);
        listView.setAdapter(new ImageListViewAdapter(ToursActivity.this));

    }


}