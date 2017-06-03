package com.icebug.android.studyunion;

/**
 * Created by nafis on 02-Jun-17.
 */

public class GroupRoom {

    private String groupName;
    private int numberOfPeople;

    public GroupRoom(){

    }

    public GroupRoom(String groupName) {
        this.groupName = groupName;
        this.numberOfPeople = 0;

    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupName() {

        return groupName;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }


}
