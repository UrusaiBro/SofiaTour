package com.mbtutu.sofiatour;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleSightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlesight);

        Toolbar toolbar = findViewById(R.id.toolbar);
        ImageView toolbarLayoutImgview = findViewById(R.id.toolbar_layout_imgview);
        CollapsingToolbarLayout toolbarLayout = findViewById(R.id.toolbar_layout);
        TextView desc_text = findViewById(R.id.description);



        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String desc = intent.getStringExtra("desc");
        String picurl = intent.getStringExtra("picurl");
        Log.e("asdf", "title: " + title + ", desc: " + desc + ", picurl: " + picurl);



        setSupportActionBar(toolbar);
        toolbarLayout.setTitle(title);

        Resources res = getApplicationContext().getResources();
        int picid = res.getIdentifier(picurl, "drawable", getPackageName());
        toolbarLayoutImgview.setImageResource(picid);

        desc_text.setText(desc);






        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Repl3242342342with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
