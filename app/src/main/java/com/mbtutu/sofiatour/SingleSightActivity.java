package com.mbtutu.sofiatour;

import android.content.Intent;
import android.content.res.Resources;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SingleSightActivity extends AppCompatActivity {

    Button play;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_sight);



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


        play = findViewById(R.id.Button);
        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (mediaPlayer == null){
                    mediaPlayer = MediaPlayer.create(SingleSightActivity.this, R.raw.sound );
                    mediaPlayer.start();
                    play.setText("pause");}

                else if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    play.setText("play");
                } else {
                    mediaPlayer.start();
                    play.setText("pause");
                }
            }
        });




    }





}
