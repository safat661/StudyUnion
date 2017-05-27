package com.icebug.android.studychat;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private static int SIGN_IN_REQUEST_CODE = 1;
    private FirebaseListAdapter<ChatMessage> adapter;
    RelativeLayout activity_main;
    FloatingActionButton fab;


    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.menu_sign_out)
        {
            AuthUI.getInstance().signOut(this).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(MainActivity.this, "You have been signed out !", Toast.LENGTH_SHORT).show();
                    finish();

                }
            });

        }

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == SIGN_IN_REQUEST_CODE)
        {
            if(resultCode == RESULT_OK)
            {
                Toast.makeText(MainActivity.this, "Successfully signed in Welcome", Toast.LENGTH_SHORT).show();
                displayChatMessage();

            }
            else{
                Toast.makeText(this, "We could'nt sign you in. Please sign in again later", Toast.LENGTH_SHORT).show();
                finish();

            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (RelativeLayout)findViewById(R.id.activity_main);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener(){
                                   @Override
                                   public void onClick(View view){
                                       EditText input = (EditText)findViewById(R.id.input);
                                       FirebaseDatabase.getInstance().getReference().push().setValue(new com.icebug.android.studychat.ChatMessage(input.getText().toString(),
                                               FirebaseAuth.getInstance().getCurrentUser().getEmail()));
                                       input.setText("");
                                   }

                               }



        );
        //CHECKING IF NOT SIGN IN THEN NAVIGATE SIGNIN PAGE
        if(FirebaseAuth.getInstance().getCurrentUser() ==  null)
        {
            startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder().build(),SIGN_IN_REQUEST_CODE);

        }
        else{
            Toast.makeText(this,"Welcome "+FirebaseAuth.getInstance().getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
            //load content
            displayChatMessage();
        }

        //loading content

        displayChatMessage();
    }



    private void displayChatMessage(){
        ListView listOfMessage = (ListView)findViewById(R.id.list_of_message);
        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class, R.layout.list_item,FirebaseDatabase.getInstance().getReference())
        {
            @Override
            protected void populateView(View v, com.icebug.android.studychat.ChatMessage model, int position){
                //get references to the view of list items
                TextView messageText,messageUser,messageTime;
                messageText = (TextView)v.findViewById(R.id.message_text);
                messageUser = (TextView)v.findViewById(R.id.message_user);
                messageTime = (TextView)v.findViewById(R.id.message_time);

                messageText.setText(model.getMessageText());
                messageUser.setText(model.getMessageUser());
                messageTime.setText(DateFormat.format("dd-MM-YY (HH:mm)",model.getMessageTime()));
            }
        };
        listOfMessage.setAdapter(adapter);
    }


}

