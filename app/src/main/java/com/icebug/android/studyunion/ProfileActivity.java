package com.icebug.android.studyunion;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth firebaseAuth;
    private TextView name,college,major,year,email;
    private Button buttonLogout;
    private FirebaseDatabase ref;
    private DatabaseHelper help;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        help = new DatabaseHelper(this);
        firebaseAuth = FirebaseAuth.getInstance();

        FirebaseUser user = firebaseAuth.getCurrentUser();

        name = (TextView)findViewById(R.id.user_name);
        college = (TextView)findViewById(R.id.user_college);
        major = (TextView)findViewById(R.id.user_major);
        email = (TextView)findViewById(R.id.user_email);
        year = (TextView)findViewById(R.id.user_year);

        buttonLogout = (Button) findViewById(R.id.logoutButton);
        buttonLogout.setOnClickListener(this);

        getUserInfo();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View view){
    if(view == buttonLogout){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(this, LoginActivity.class));
    }
    }

    public void getUserInfo() {

            Cursor c = help.getData("Name");
            c.moveToNext();
            name.setText(c.getString(c.getColumnIndex("Name")));
            c = help.getData("College");
            c.moveToNext();
            college.setText(c.getString(c.getColumnIndex("College")));
            c = help.getData("Major");
            c.moveToNext();
            major.setText(c.getString(c.getColumnIndex("Major")));
            c = help.getData("Year");
            c.moveToNext();
            year.setText(c.getString(c.getColumnIndex("Year")));
            c = help.getData("Email");
            c.moveToNext();
            email.setText(c.getString(c.getColumnIndex("Email")));

        c.close();

    }
}