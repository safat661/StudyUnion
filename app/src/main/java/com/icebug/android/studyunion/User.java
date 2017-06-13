package com.icebug.android.studyunion;

/**
 * Created by nafis on 02-Jun-17.
 */

public class User {

    private String name;
    public String uid;
    private String email;
    public String firebaseToken;
    private String collegeName;
    private String collegeYear;
    private String major;


    public User(){


    }

    public User(String email) {
        this.email = email;
    }

    public User(String name, String email, String collegeName, String collegeYear, String major) {
        this.name = name;
        this.email = email;
        this.collegeName = collegeName;
        this.collegeYear = collegeYear;
        this.major = major;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public void setCollegeYear(String collegeYear) {
        this.collegeYear = collegeYear;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public String getCollegeYear() {
        return collegeYear;
    }

    public String getMajor() {
        return major;
    }

    public String getUid() {
        return uid;
    }

    public String getFirebaseToken() {
        return firebaseToken;
    }


}
