package com.icebug.android.studyunion;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FaqCommentActivity extends AppCompatActivity implements View.OnClickListener  {

    private ListView commentList;
    private EditText comment;
    private Button postButton;
    private TextView originalPost ;
    private FirebaseListAdapter<FaqComment> adapter;
    private FaqComment faqComment ;

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

        postButton.setOnClickListener(this);

        displayComments();

    }

    private void displayComments() {

        adapter = new FirebaseListAdapter<FaqComment>(this,FaqComment.class, R.layout.list_item, FirebaseDatabase.getInstance().getReference().child("FaqPost").child(""+getIntent().getStringExtra("FaqPostID")).child("PostComments")) {
            @Override
            protected void populateView(View v, FaqComment comment, int position) {


                TextView messageText,messageUser,messageTime;
                messageText = (TextView)v.findViewById(R.id.message_text);
                messageUser = (TextView)v.findViewById(R.id.message_user);
                messageTime = (TextView)v.findViewById(R.id.message_time) ;

                messageText.setText(comment.getComment());
                messageUser.setText(comment.getCommentOP());
                messageTime.setText(comment.getTimeCreated());

            }
        };
        commentList.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {

        if(v == postButton) {

            if(!comment.getText().toString().equals("")) {
                DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference().child("FaqPost").child("" + getIntent().getStringExtra("FaqPostID")).child("PostComments").push();

                faqComment = new FaqComment("" + comment.getText());

                faqComment.setCommentOP("" + getIntent().getStringExtra("FaqPostOP"));

                Calendar c = Calendar.getInstance();
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String formattedDate = df.format(c.getTime());

                faqComment.setTimeCreated(formattedDate);

                databaseRef.setValue(faqComment);

                comment.setText("");
            }
        }


    }
}
