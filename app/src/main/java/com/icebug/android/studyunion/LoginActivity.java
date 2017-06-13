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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

        @BindView(R.id.editTextEmail) EditText editTextEmail;
        @BindView(R.id.editTextPassword) EditText editTextPassword;
        @BindView(R.id.buttonLogin) Button buttonSignIn;
        @BindView(R.id.textVeiwRegister) TextView textViewRegister;

    private FirebaseAuth firebaseAuth;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
           finish();
           startActivity(new Intent(getApplicationContext(),MenuActivity.class));
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
        }

        progressDialog = new ProgressDialog(this);

        buttonSignIn.setOnClickListener(this);
        textViewRegister.setOnClickListener(this);

    }

    private void userLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();
            return;
        }

        //validation ok if reaches here

        progressDialog.setMessage("Loging In");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();

                if(task.isSuccessful()){
                    Toast.makeText(LoginActivity.this, "Hello!", Toast.LENGTH_SHORT).show();
                    finish();
                    startActivity(new Intent(getApplicationContext(),MenuActivity.class));
                    overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
                }else{
                    Toast.makeText(LoginActivity.this,"Sorry, Could Not Login",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view){
        if (view == buttonSignIn) {
            userLogin();
        }
        if(view == textViewRegister){
            finish();
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);

        }
    }

}