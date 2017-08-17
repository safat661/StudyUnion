package com.icebug.android.studyunion;

import java.util.ArrayList;

/**
 * Created by nafis on 10-Aug-17.
 */

public class FaqPost {

    private String post;
    private String OP;
    private ArrayList<FaqComment> comment;
    private String postID;
    private String timeCreated;

    public void setTimeCreated(String timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {

        this.postID = postID;
    }

    public String getTimeCreated() {
        return timeCreated;
    }

    public FaqPost (){

    }

    public ArrayList<FaqComment> getComment() {
        return comment;
    }

    public void setPost(String post) {

        this.post = post;
    }

    public void setOP(String OP) {
        this.OP = OP;
    }

    public void addComment(FaqComment comment) {
        this.comment.add(comment);
    }

    public String getPost() {

        return post;
    }

    public String getOP() {
        return OP;
    }

    public FaqPost(String post, String OP){
        this.post = post;
        this.OP = OP;
    }


}
