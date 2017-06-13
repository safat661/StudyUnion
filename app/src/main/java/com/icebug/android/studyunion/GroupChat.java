package com.icebug.android.studyunion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GroupChat extends AppCompatActivity implements View.OnClickListener {

    private ListView groupchatList;
    private EditText text;
    private Button sendButton;
    private String groupName ;
    private String groupUid;
    private String groupSize ;

    private FirebaseListAdapter<ChatMessage> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_chat);

        groupName = getIntent().getStringExtra("GroupName");
        groupUid = getIntent().getStringExtra("GroupID");
        groupSize = getIntent().getStringExtra("GroupSize");

        groupchatList = (ListView)findViewById(R.id.list_group_chat);
        text = (EditText)findViewById(R.id.message_to_send);
        sendButton = (Button) findViewById(R.id.send_text_button);

        sendButton.setOnClickListener(this);

        displayMessages();

    }

    private void displayMessages() {

        adapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,R.layout.list_item, FirebaseDatabase.getInstance().getReference().child("GroupRooms").child(groupUid).child("Chat")) {

            @Override
            protected void populateView(View v, ChatMessage message, int position) {
                TextView messageText,messageUser;
                messageText = (TextView)v.findViewById(R.id.message_text);
                messageUser = (TextView)v.findViewById(R.id.message_user);

                messageText.setText(message.getMessageText());
                messageUser.setText(message.getMessageUser());
            }
        };

        groupchatList.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

        if(v == sendButton ){

            ChatMessage chatMessage = new ChatMessage(text.getText().toString(), FirebaseAuth.getInstance().getCurrentUser().getEmail());

            DatabaseReference databaseRef =  FirebaseDatabase.getInstance().getReference().child("GroupRooms").child(groupUid).child("Chat").push();

            databaseRef.setValue(chatMessage);

            text.setText("");

        }
    }


}
