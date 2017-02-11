package epam.com.entities;

import java.io.Serializable;
import java.sql.Timestamp;


public class Comment implements Serializable {
    private String commentText;
    private Timestamp time;
    private String owner;

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}
