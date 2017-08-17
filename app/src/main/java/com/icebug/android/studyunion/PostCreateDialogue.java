package com.icebug.android.studyunion;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nafis on 11-Aug-17.
 */

public class PostCreateDialogue extends DialogFragment implements View.OnClickListener {

    private FaqPost mPost;
    private ProgressDialog mProgressDialog;
    private View mRootView;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        mProgressDialog = new ProgressDialog(getContext());

        mPost = new FaqPost();
        mRootView = getActivity().getLayoutInflater().inflate(R.layout.post_create_dialogue, null);
        mRootView.findViewById(R.id.post_dialog_send_imageview).setOnClickListener(this);
        builder.setView(mRootView);
        return builder.create();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.post_dialog_send_imageview:
                sendPost();
                break;
        }
    }

    private void sendPost() {

        mProgressDialog.setMessage("Sending post...");
        mProgressDialog.setCancelable(false);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.show();


        DatabaseReference databaseRef =  FirebaseDatabase.getInstance().getReference().child("FaqPost").push();

        mPost.setPostID(databaseRef.getKey());

        TextView postDialogTextView = (TextView) mRootView.findViewById(R.id.post_dialog_edittext);

        String text = postDialogTextView.getText().toString();

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = df.format(c.getTime());

        mPost.setOP(FirebaseAuth.getInstance().getCurrentUser().getEmail());

        mPost.setTimeCreated(formattedDate);

        mPost.setPost(text);

        databaseRef.setValue(mPost);

        mProgressDialog.dismiss();

        dismiss();

    }


}
