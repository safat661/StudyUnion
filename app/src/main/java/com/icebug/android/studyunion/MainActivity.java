package com.icebug.android.studyunion;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements TextView.OnClickListener {

    private ProgressDialog progressDialog;

    @BindView(R.id.editTextEmail) EditText editTextEmail;
    @BindView(R.id.editTextPassword) EditText editTextPassword;
    @BindView(R.id.editTextStudentName) EditText editStudentName;
    @BindView(R.id.editTextCollegeName) EditText editTextCollegeName;
    @BindView(R.id.editTextPassword2) EditText editTextPassword2;
    @BindView(R.id.editTextMajor) EditText editTextMajor;
    @BindView(R.id.buttonRegister) Button buttonRegister;
    @BindView(R.id.login) TextView login;
    @BindView(R.id.editTextCollegeYear) EditText editTextCollegeYear;

    private FirebaseAuth firebaseAuth;
    private Firebase mRootRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        Firebase.setAndroidContext(this);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }

        buttonRegister.setOnClickListener(this);
        login.setOnClickListener(this);
    }

    private void registerUser() {

        final String email = editTextEmail.getText().toString().trim();
        final String password = editTextPassword.getText().toString().trim();
        final String name = editStudentName.getText().toString().trim();
        final String college = editTextCollegeName.getText().toString().trim();
        final String year = editTextCollegeYear.getText().toString().trim();
        final String major = editTextMajor.getText().toString().trim();


        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter Password", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(name)) {
            //password is empty
            Toast.makeText(this, "Please enter your Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(college)) {
            //password is empty
            Toast.makeText(this, "Please enter your College Name", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(year)) {
            //password is empty
            Toast.makeText(this, "Please enter your College Year", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(major)) {
            //password is empty
            Toast.makeText(this, "Please enter your Major", Toast.LENGTH_SHORT).show();
            return;
        }
            //validation ok if reaches here

            progressDialog.setMessage("Registering");
            progressDialog.show();

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                        //making user object and pushing to child.
                        User user = new User(name, email, college, year, major);
                      //  user.setUid(firebaseAuth.getCurrentUser().getUid());
                        FirebaseDatabase.getInstance().getReference().child("Users").push().setValue(user);
                   //     SavedUserInfo saved = new SavedUserInfo(user);
                        Toast.makeText(MainActivity.this, "Registered Successfully!!", Toast.LENGTH_SHORT).show();

                        finish();
                        startActivity(new Intent(getApplicationContext(), MenuActivity.class));
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                    } else {
                        Toast.makeText(MainActivity.this, "Could not Register", Toast.LENGTH_SHORT).show();
                    }
                    progressDialog.dismiss();
                }
            });


        }


    @Override
    public void onClick(View view) {
        if (view == buttonRegister) {
            registerUser();
        }
        if (view == login) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
            overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
        }

    }

}
