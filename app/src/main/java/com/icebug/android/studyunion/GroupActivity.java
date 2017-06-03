package com.icebug.android.studyunion;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.LinkedList;
import java.util.List;

public class GroupActivity extends AppCompatActivity  {

    private FirebaseListAdapter<GroupRoom> adapter;
    private DatabaseReference groupsRef;

    private ListView groupList;
    private TextView text;
    private FloatingActionButton fab;
    private static final String TAG = "MainActivity";
    final List<GroupRoom> groups = new LinkedList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        text = (TextView)findViewById(R.id.text);
        groupList = (ListView)findViewById(R.id.group_list);
        fab = (FloatingActionButton)findViewById(R.id.fab2);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                GroupRoom group = new GroupRoom("New");
                FirebaseDatabase.getInstance().getReference().child("Groups").push().setValue(group);
                Log.v(TAG,"group added");


            }

        });
        text.setText("No Groups");
        displayGroups();


    }

    private void displayGroups() {


        adapter = new FirebaseListAdapter<GroupRoom>(this,GroupRoom.class, R.layout.list_item,FirebaseDatabase.getInstance().getReference().child("Groups")) {

        @Override
        protected void populateView(View v, GroupRoom group, int position) {

            TextView messageText,messageUser;
            messageText = (TextView)v.findViewById(R.id.message_text);
            messageUser = (TextView)v.findViewById(R.id.message_user);

            messageText.setText(group.getGroupName());
            messageUser.setText(group.getGroupName());

        }
    };
        groupList.setAdapter(adapter);

        //need to be fixed
        if(groupList.getItemsCanFocus()){
            text.setVisibility(View.INVISIBLE);
            Log.v(TAG,"yes");
        }
    }


}
