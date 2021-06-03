package Model.ServerAndClient;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Post implements Comparable<Post>, Serializable {

    private String writer;
    private String title;
    private String description;
    private Long createdTime;
    byte[] ImageAttachedTopost;
    public int numberOfReposts = 0;
    public String createdTimeString;
    public Profile publisher;
    public Vector<Profile> Likers=new Vector<>();

    public Profile getPublisher() {
        return this.publisher;
    }

    public void setPublisher(Profile publisher) {
        this.publisher = publisher;
    }

    public String getCreatedTimeString() {
        return createdTimeString;
    }


    public void setCreatedTimeString(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.createdTimeString = formatter.format(date);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setImageAttachedTopost(byte[] imageAttachedTopost) {
        ImageAttachedTopost = imageAttachedTopost;
    }

    public byte[] getImageAttachedTopost() {
        return ImageAttachedTopost;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public int getNumberOfReposts() {
        return numberOfReposts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(title, post.title)&&Objects.equals(description, post.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(title);
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public int compareTo(Post o) {
        return o.createdTime.compareTo(createdTime);
    }
}
