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
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;

public class ToursActivity extends AppCompatActivity {
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

        final ArrayList<TourBundle> result = new ArrayList<>();
        db.collection("bundles").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (DocumentSnapshot document : task.getResult()) {
                        String name = document.get("name").toString();
                        String description = document.get("description").toString();
                        String pictureUrl = document.get("picture").toString();
                        ArrayList<String> tours = new ArrayList<>(Arrays.asList(document.get("tours").toString().split("/")));
                        double price = document.getDouble("price");
                        result.add(new TourBundle(name, description, pictureUrl, price, tours));
                    }
                    listView.setAdapter(new ImageListViewAdapter(ToursActivity.this, result));
                }else {
                    Log.e("err", "Error getting documents.", task.getException());
                }
            }
        });
    }
}
