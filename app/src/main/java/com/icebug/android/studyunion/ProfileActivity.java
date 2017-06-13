package com.icebug.android.studyunion;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView name,college;
    private Button buttonLogout;
    private FirebaseDatabase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();


        FirebaseUser user = firebaseAuth.getCurrentUser();

        name = (TextView)findViewById(R.id.profile_name);

     //   name.setText(SavedUserInfo.userSaved.get("User",SavedUserInfo.userSaved.name()));

        buttonLogout = (Button) findViewById(R.id.logoutButton);

        buttonLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){
    if(view == buttonLogout){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }
    }
}
