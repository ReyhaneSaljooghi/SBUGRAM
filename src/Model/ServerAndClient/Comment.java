package Model.ServerAndClient;

public class Comment {
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
