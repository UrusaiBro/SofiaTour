package com.mbtutu.sofiatour;

import android.content.res.Resources;
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
import java.util.Arrays;

public class ToursActivity extends AppCompatActivity {
    //FirebaseFirestore db;

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tours);

        //db = FirebaseFirestore.getInstance();
        listView = findViewById(R.id.testList);

        loadListView();

    }


    private void loadListView(){
        final ArrayList<Sight> sights0 = new ArrayList<>();
        final ArrayList<Sight> sights1 = new ArrayList<>();
        final ArrayList<Sight> sights2 = new ArrayList<>();

        final ArrayList<Tour> allbundles = new ArrayList<>();

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
            sights0.add(new Sight(name, description, pictureUrl, price, coordinates));
        }

        for(int i=5; i<9; i++){
            String name = sight_titles[i];
            String description = sight_descs[i];
            String pictureUrl = sight_pic_ids[i];
            double price = 1;
            GeoPoint coordinates = null;
            sights1.add(new Sight(name, description, pictureUrl, price, coordinates));
        }

        for(int i=10; i<14; i++){
            String name = sight_titles[i];
            String description = sight_descs[i];
            String pictureUrl = sight_pic_ids[i];
            double price = 1;
            GeoPoint coordinates = null;
            sights2.add(new Sight(name, description, pictureUrl, price, coordinates));
        }


        String[] bundle_titles = res.getStringArray(res.getIdentifier("bundle_names", "array", getPackageName()));
        String[] bundle_descs = res.getStringArray(res.getIdentifier("bundle_descs", "array", getPackageName()));
        String[] bundle_pic_ids = res.getStringArray(res.getIdentifier("bundle_pic_ids", "array", getPackageName()));

        for(int i=0; i<bundle_titles.length; i++){

            String name = bundle_titles[i];
            String description = bundle_descs[i];
            String pictureUrl = bundle_pic_ids[i];
            double price = 10;
            allbundles.add(new Tour(name, description, pictureUrl, price, null));
            if(i==0)
                allbundles.get(i).setSights(sights0);
            if(i==1)
                allbundles.get(i).setSights(sights1);
            if(i==2)
                allbundles.get(i).setSights(sights2);

        }


        listView.setAdapter(new ImageListViewAdapter(ToursActivity.this, allbundles));
    }

    /*
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
    */
}
