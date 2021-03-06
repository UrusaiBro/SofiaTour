package com.mbtutu.sofiatour;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProfileActivity extends AppCompatActivity {


    private static final int RC_SIGN_IN = 123;
    Button change_acc_btn;
    TextView profile_name_textview, profile_email_textview;
    ImageView profile_pic_imgview;

    FirebaseUser currentUser;
    FirebaseFirestore db;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        currentUser = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();


        // set ids of ui elements
        change_acc_btn = findViewById(R.id.change_acc_btn);
        profile_name_textview = findViewById(R.id.profile_name_textview);
        profile_email_textview = findViewById(R.id.profile_email_textview);
        profile_pic_imgview = findViewById(R.id.profile_pic_imgview);

        refreshInfo();
    }



    // sign in method
    private void signIn() {
        List<AuthUI.IdpConfig> providers = Arrays.asList(new AuthUI.IdpConfig.GoogleBuilder().build());
        // starts sign in activity
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().setAvailableProviders(providers).build(), RC_SIGN_IN);

    }

    // method refreshing the info when the sign in activity is done
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        fireCloudLogin();


        refreshInfo();
        Log.e("asd", "refreshing after signin");
    }





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

                            Log.w("login", "Registering user:" + currentUser.getEmail());
                            db.collection("users").document(currentUser.getEmail()).set(user);

                        } else {
                            Log.w("login", "Error getting documents.", task.getException());
                        }
                    }
                });
    }



    // sign out method
    private void signOut() {
        AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
            public void onComplete(@NonNull Task<Void> task) {
                refreshInfo();
                Log.e("asd", "refreshing after signout");
            }
        });
    }



    // refreshes everything
    void refreshInfo(){
        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            profile_name_textview.setText(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());
            profile_email_textview.setText(FirebaseAuth.getInstance().getCurrentUser().getEmail());
            new DownloadImageTask(profile_pic_imgview).execute(FirebaseAuth.getInstance().getCurrentUser().getPhotoUrl().toString());

            change_acc_btn.setText("log out");
            change_acc_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signOut();
                }
            });

        } else {
            profile_name_textview.setText("no profile found");
            profile_email_textview.setText("no profile found");
            profile_pic_imgview.setImageDrawable(null);

            change_acc_btn.setText("log in");
            change_acc_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    signIn();
                }
            });
        }
    }





}
