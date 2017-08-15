package com.icebug.android.studyunion;

/**
 * Created by nafis on 10-Aug-17.
 */

public class FaqPost {

    private String post;
    private String OP;
    private FaqComment comment;
    private String postID;
    private long timeCreated;

    public void setTimeCreated(long timeCreated) {
        this.timeCreated = timeCreated;
    }

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {

        this.postID = postID;
    }

    public FaqPost (){

    }

    public FaqComment getComment() {
        return comment;
    }

    public void setPost(String post) {

        this.post = post;
    }

    public void setOP(String OP) {
        this.OP = OP;
    }

    public void setComment(FaqComment comment) {
        this.comment = comment;
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
