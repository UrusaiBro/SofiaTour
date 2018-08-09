package com.mbtutu.sofiatour;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;

import java.lang.reflect.Array;
import java.util.*;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.GeoPoint;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;
    private static final int RC_SIGN_IN = 123;

    Button tours_btn, map_btn, sights_btn, profile_btn;
    ImageView toolbar_pic_imgview, listImage;
    //FirebaseUser currentUser;
    //FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiating variables
        //currentUser = FirebaseAuth.getInstance().getCurrentUser();
        //db = FirebaseFirestore.getInstance();

        //Instantiating Views
        tours_btn = findViewById(R.id.tours_btn);
        map_btn = findViewById(R.id.map_btn);
        sights_btn = findViewById(R.id.sights_btn);
        profile_btn = findViewById(R.id.profile_btn);
        //toolbar_pic_imgview = findViewById(R.id.toolbar_imgview);
        listImage = findViewById(R.id.listImage);
        listImage.setImageDrawable(getResources().getDrawable(R.drawable.picpic));


        setUpButtons();



        Resources res = getApplicationContext().getResources();
        String[] asd = res.getStringArray(R.array.tours);
        for(int i=0;i<asd.length;i++)
            Log.e("asd",asd[i]);



        // Setting up user: IF not logged in: log in
        // Else: toast
       // if( currentUser == null) {
       //     signIn();
       // } else {  // Else: display toast
       //     Toast.makeText(this, "Welcome " + currentUser.getDisplayName(), Toast.LENGTH_LONG).show();

       //     Log.e("asd", "asdadadadadada");
            //new DownloadImageTask(toolbar_pic_imgview).execute(currentUser.getPhotoUrl().toString());
      //  }
    }

    //Assigns onClickListeners to the buttons
    private void setUpButtons(){
        tours_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ToursActivity.class);
                startActivity(intent);
            }
        });

        map_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        sights_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SightsActivity.class);
                startActivity(intent);
            }
        });

        profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        /*toolbar_pic_imgview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });*/


    }


    @Override // idk if i need this shit but will keep it here
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                //
                //
                // currentUser = FirebaseAuth.getInstance().getCurrentUser();

               // fireCloudLogin();

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }








   /* //Checks if the user is already in the database
    private void fireCloudLogin() {
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            for (DocumentSnapshot document : task.getResult()) {
                               if (document.getId().equals(currentUser.getEmail())) {
                                   Log.d("login", document.getId() + " Match found => " + document.getData());
                                   return;
                               }
                            }

                            Map<String, Object> user = new HashMap<>();
                            user.put("displayName", currentUser.getDisplayName());
                            user.put("email", currentUser.getEmail());

                            Log.w("login4", "Registering user:" + currentUser.getEmail());
                            db.collection("users").document(currentUser.getEmail()).set(user);

                        } else {
                            Log.w("login5", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private void signIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build());
        // starts sign in
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), RC_SIGN_IN);
    }
*/
}
