package com.icebug.android.studyunion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;

public class FaqCommentActivity extends AppCompatActivity {

    private ListView commentList;
    private EditText comment;
    private Button postButton;
    private TextView originalPost ;
    private FirebaseListAdapter<FaqComment> adapter;

    private Intent intentthis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_comment);

        intentthis = getIntent();

        commentList = (ListView)findViewById(R.id.faq_comments);
        comment = (EditText)findViewById(R.id.comment);
        postButton = (Button)findViewById(R.id.post_comment_button);
        originalPost = (TextView)findViewById(R.id.faq_post_op);

        originalPost.setText(getIntent().getStringExtra("FaqPost"));

        displayCommentOs();

    }

    private void displayCommentOs() {


    }
}
