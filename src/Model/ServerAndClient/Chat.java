package Model.ServerAndClient;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.Vector;

public class Chat implements Serializable,Comparable<Chat> {
    /**
     * use serialVersionUID
     */
    private static final long serialVersionUID = 10L;
    private Profile user1;
    private Profile user2;
    private long ID;
    Date date;
    /**
     * The message is a collection to keeping sent messages in chat
     */
    private Vector<Message> messages = new Vector<>();

    public Chat(Profile user1, Profile user2) {
        this.user1 = user1;
        this.user2 = user2;
        date=new Date();
    }
    public Chat(){
        date=new Date();
    }


    public Profile getUser1() {
        return user1;
    }

    public Profile getUser2() {
        return user2;
    }

    public void setUser1(Profile user1) {
        this.user1 = user1;
    }

    public void setUser2(Profile user2) {
        this.user2 = user2;
    }

    public boolean IsInChat(Profile profile){
        return user1.equals(profile)||user2.equals(profile);
    }

    public boolean IsInChat(String profile){
        return user1.getUsername().equals(profile)||user2.getUsername().equals(profile);
    }

    public Profile getAnother(Profile user){
        if (user1.equals(user))
            return user2;
        else
            return user1;
    }
    public String getAnother(String user){
        if (user1.getUsername().equals(user))
            return user2.getUsername();
        else
            return user1.getUsername();
    }


    public Vector<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "Chat{" +
                "user1=" + user1 +
                ", user2=" + user2 +
                '}';
    }

    @Override
    public int compareTo(Chat o) {
        if (this.messages.size()==0 && o.messages.size() != 0)
            return +1;
        if (o.messages.size()==0 && this.messages.size() != 0)
            return -1;
        if(this.messages.size()==0 && o.messages.size() == 0)
            return 0;
        return o.getMessages().get(o.getMessages().size()-1).getCreatedTime().
                compareTo
                        (this.messages.get(messages.size()-1).getCreatedTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(user1, chat.user1) && Objects.equals(user2, chat.user2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user1, user2);
    }
}
