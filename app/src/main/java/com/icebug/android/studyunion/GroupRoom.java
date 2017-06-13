package com.icebug.android.studyunion;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nafis on 02-Jun-17.
 */

public class GroupRoom {

    private String groupName;
    private int numberOfPeople = 0;
    private List<DatabaseReference> users;
    private ArrayList<User> selectedUsers;
    private String Uid;


    public GroupRoom(){

    }

    public GroupRoom(String groupName,ArrayList<User> selectedUsers) {
        this.groupName = groupName;
        this.selectedUsers = selectedUsers;

    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {

        return groupName;
    }

    public ArrayList<User> getSelectedUsers() {
        return selectedUsers;
    }

    public int getNumberOfPeople() {

        return numberOfPeople;

    }

    public void setSelectedUsers(ArrayList<User> selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public void setNumberOfPeople(){

        for (int i = 0; i < selectedUsers.size();i++){

            numberOfPeople++;
        }

    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }


}
