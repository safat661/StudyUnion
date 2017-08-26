package com.icebug.android.studyunion;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class card_list_activity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton fab;
    private ListView list;
    public static ArrayList<String> events = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list_activity);

        getSupportActionBar().setTitle(""+getIntent().getStringExtra("CardHeader"));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fab = (FloatingActionButton)findViewById(R.id.edit_card_list);
        list = (ListView) findViewById(R.id.group_list);

        Toast.makeText(this,""+events.size(),Toast.LENGTH_LONG).show();

        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment fragment = new Fragment();
                PostCreateDialogue dialog = new PostCreateDialogue();

                Bundle bundle = new Bundle();
                bundle.putString("Type","Add");

                dialog.setArguments(bundle);
                dialog.show(getSupportFragmentManager(),"Add "+getIntent().getStringExtra("CardHeader"));


            }

        });

       CustomAdpater adapter = new CustomAdpater();
       list.setAdapter(adapter);
    }

    class CustomAdpater extends BaseAdapter{

        @Override
        public int getCount() {
            return events.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.custom_list_adapter,null);

            String type = "";
            String duedate = "";

            String [] temp_array;
            temp_array = events.get(position).split(",");
            type = temp_array[0];
            duedate = temp_array[1];

            TextView typetext = (TextView)convertView.findViewById(R.id.type_name);
            TextView typedue = (TextView)convertView.findViewById(R.id.type_due);

            typetext.setText(type);
            typedue.setText(duedate);

            return convertView;
        }

    }

    private void display() {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                R.layout.card_list_item,
                events );
//
//        String type = "";
//        String duedate = "";
//
//        String [] temp_array;
//        if(!arrayAdapter.isEmpty()) {
//            temp_array = events.get(arrayAdapter.getCount()-1).split(",");
//            type = temp_array[0];
//            duedate = temp_array[1];
//        }
//
//        TextView messageText,messageUser;
//        messageText = (TextView)findViewById(R.id.message_text);
//        messageUser = (TextView)findViewById(R.id.message_user);
//
//        messageText.setText(type);
//        messageUser.setText(duedate);

        list.setAdapter(arrayAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onClick(View v) {

    }
}
