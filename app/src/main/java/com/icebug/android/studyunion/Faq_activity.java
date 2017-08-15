package com.icebug.android.studyunion;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.FirebaseDatabase;

public class Faq_activity extends FragmentActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private ListView postList;
    private FirebaseListAdapter<FaqPost> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_activity);

        fab = (FloatingActionButton)findViewById(R.id.faq_fab);

        postList = (ListView)findViewById(R.id.group_list);
        postList.setItemsCanFocus(false);

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new Fragment();
                PostCreateDialogue dialog = new PostCreateDialogue();
                dialog.show(getSupportFragmentManager(),"Post");
            }

        });

        displayGroups();


    }

    @Override
    public void onClick(View v) {

    }

    private void displayGroups() {

        adapter = new FirebaseListAdapter<FaqPost>(this,FaqPost.class, R.layout.list_item, FirebaseDatabase.getInstance().getReference().child("FaqPost")) {

            @Override
            protected void populateView(View v, FaqPost post , int position) {

                TextView messageText,messageUser;
                messageText = (TextView)v.findViewById(R.id.message_text);
                messageUser = (TextView)v.findViewById(R.id.message_user);

                messageText.setText(post.getPost());
                messageUser.setText(post.getOP());


            }
        };
        postList.setAdapter(adapter);


    }

}
