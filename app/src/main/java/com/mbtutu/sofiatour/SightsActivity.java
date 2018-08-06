package com.mbtutu.sofiatour;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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


    FirebaseFirestore db;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sights);

        db = FirebaseFirestore.getInstance();
        listView = findViewById(R.id.testList);

        loadListView();

    }


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



}
