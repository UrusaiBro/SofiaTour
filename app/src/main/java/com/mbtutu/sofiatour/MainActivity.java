package com.mbtutu.sofiatour;

import android.content.Intent;
import android.os.Bundle;
import java.util.*;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //private TextView mTextMessage;
    private static final int RC_SIGN_IN = 123;

    Button tours_btn, map_btn, sights_btn, profile_btn;
    FirebaseUser currentUser;
    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiating variables
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();

        //Instantiating Views
        tours_btn = findViewById(R.id.tours_btn);
        map_btn = findViewById(R.id.map_btn);
        sights_btn = findViewById(R.id.sights_btn);
        profile_btn = findViewById(R.id.profile_btn);
        setUpButtons();


        // Setting up user: IF not logged in: log in
        // Else: toast
        if( currentUser == null) {
            signIn();
        } else {  // Else: display toast
            Toast.makeText(this, "Welcome " + currentUser.getDisplayName(), Toast.LENGTH_LONG).show();
        }


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


    }


    @Override // idk if i need this shit but will keep it here
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                currentUser = FirebaseAuth.getInstance().getCurrentUser();

                fireCloudLogin();

            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }









    //Checks if the user is already in the database
    private boolean isRegistered() {
        final boolean[] registered = {false};

        db.collection("Users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot document : task.getResult()) {
                                if (document.getData().containsValue(currentUser.getEmail())) {
                                    registered[0] = true;
                                }
                            }
                        } else {
                            Log.w("DISAPPOINTMENT", "Error getting documents.", task.getException());
                        }
                    }
                });
        return registered[0];
    }

    // If user is not in databese, add them
    private void fireCloudLogin()
    {
       if(!isRegistered()) {
           // Creates user
           Map<String, Object> user = new HashMap<>();
           user.put("displayName", currentUser.getDisplayName());
           user.put("email", currentUser.getEmail());

           db.collection("Users")
                   .add(user)

                   .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                       @Override
                       public void onSuccess(DocumentReference documentReference) {
                       }
                   })

                   .addOnFailureListener(new OnFailureListener() {
                       @Override
                       public void onFailure(@NonNull Exception e) {
                       }
                   });
       }

    }

    private void signIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build());
        // starts sign in
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), RC_SIGN_IN);
    }

}
