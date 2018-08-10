package com.mbtutu.sofiatour;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SightsActivity extends AppCompatActivity {


    //FirebaseFirestore db;

    RecyclerView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights);

        //db = FirebaseFirestore.getInstance();
        listView = findViewById(R.id.testList);

        loadListView();


    }


    private void loadListView(){
        final ArrayList<Sight> result = new ArrayList<>();

        Resources res = getApplicationContext().getResources();
        String[] sight_titles = res.getStringArray(res.getIdentifier("sight_titles", "array", getPackageName()));
        String[] sight_descs = res.getStringArray(res.getIdentifier("sight_descs", "array", getPackageName()));
        String[] sight_pic_ids = res.getStringArray(res.getIdentifier("sight_pic_ids", "array", getPackageName()));



        for(int i=0; i<sight_titles.length; i++){

            String name = sight_titles[i];
            String description = sight_descs[i];
            String pictureUrl = sight_pic_ids[i];
            double price = 1;
            GeoPoint coordinates = null;
            result.add(new Sight(name, description, pictureUrl, price, coordinates));

        }


        listView.setAdapter(new ImageListViewAdapter(SightsActivity.this, result));
    }


    /*
    // loads data to listview
    private void loadListView(){

        final ArrayList<Sight> result = new ArrayList<>();
        db.collection("tours").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot document : task.getResult()) {
                        String name = document.get("name").toString();
                        String description = document.get("description").toString();
                        String pictureUrl = document.get("picture").toString();
                        double price = document.getDouble("price");
                        GeoPoint coordinates = document.getGeoPoint("coordinates");
                        result.add(new Sight(name, description, pictureUrl, price, coordinates));
                    }
                    listView.setAdapter(new ImageListViewAdapter(SightsActivity.this, result));
                }else {
                    Log.e("err", "Error getting documents.", task.getException());
                }
            }
        });
    }
    */



}
