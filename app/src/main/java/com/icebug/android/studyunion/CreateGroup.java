package com.icebug.android.studyunion;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CreateGroup extends AppCompatActivity implements View.OnClickListener {

    private EditText editText;
    private TextView textView;
    private ListView userList;
    private static final String TAG = "GroupActivity";
    private Button buttonDone ;

    private FirebaseListAdapter<User> userListAdapter;

    ArrayList<User> selectedUsers = new ArrayList<>();

    CheckedTextView checkedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_group);

        editText = (EditText)findViewById(R.id.editText);
        textView = (TextView)findViewById(R.id.peoples);
        userList = (ListView)findViewById(R.id.list_people);
        buttonDone = (Button) findViewById(R.id.button_done);

        buttonDone.setOnClickListener(this);

        userList.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        userList.setItemsCanFocus(false);

        displayUsers();

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                User selectedUser = (User) parent.getAdapter().getItem(position);

                if(selectedUsers.contains(selectedUser)){
                        Log.v(TAG,"Contains");
                        selectedUsers.remove(selectedUser);
                }
                else{
                        Log.v(TAG,"do not Contains");
                        selectedUsers.add(selectedUser);
                }
            }
        });

    }

    private void displayUsers(){

        userListAdapter = new FirebaseListAdapter<User>(CreateGroup.this, User.class, R.layout.check_box, FirebaseDatabase.getInstance().getReference().child("Users")){

            @Override
            protected void populateView(View v, User user, int position) {
//
//                TextView messageUser, messageText;
//                messageText = (TextView)v.findViewById(R.id.message_text_item);
//                messageUser = (TextView)v.findViewById(R.id.message_user_item);
                  checkedTextView = (CheckedTextView)v.findViewById(R.id.checkbox);
                  checkedTextView.setText(user.getName()+" ("+user.getCollegeYear()+")");
//                messageUser.setText(user.getName());
//                messageText.setText(user.getCollegeYear());
            }
        };

        userList.setAdapter(userListAdapter);

    }

    @Override
    public void onClick(View v) {

        if(v == buttonDone){

            GroupRoom groupRoom = new GroupRoom(""+editText.getText(),selectedUsers);

            groupRoom.setNumberOfPeople();

            DatabaseReference databaseRef =  FirebaseDatabase.getInstance().getReference().child("GroupRooms").push();

            groupRoom.setUid(databaseRef.getKey());

            databaseRef.setValue(groupRoom);



            for(int i = 0; i < selectedUsers.size();i++) Log.v(TAG,selectedUsers.get(i).getName());
            finish();
        }
    }
}
