package Model.ServerAndClient;

import java.io.Serializable;

/**
 * this is the comment class which each post object has an array of this class
 * this is serializable bcz we want to convert an Object to stream
 * that we can send over the network or save it as file or store in DB for later usage
 */
public class Comment implements Serializable {
   private String writer;
   private String commentText;
   private Long createdTime;
   public Comment(){

   }

    public Comment(String writer, String commentText) {
        this.writer = writer;
        this.commentText = commentText;
    }

    public String getCommentText() {
        return commentText;
    }

    public String getWriter() {
        return writer;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getCreatedTime() {
        return createdTime;
    }
}
