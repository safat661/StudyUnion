package com.icebug.android.studyunion;

/**
 * Created by nafis on 10-Aug-17.
 */

public class FaqComment {

    private String comment;
    private String commentOP;
    private String timeCreated;

    public String getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(String timeCreated) {

        this.timeCreated = timeCreated;
    }

    public void setCommentOP(String commentOP) {
        this.commentOP = commentOP;
    }

    public String getCommentOP() {

        return commentOP;
    }

    public FaqComment(){}

    public FaqComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
