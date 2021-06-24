package Model.ServerAndClient;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

public class Message implements Serializable {
    private String message;
    public boolean isSeen=false;
    private String sender;
    private String receiver;
    private static final long serialVersionUID = 102L;
    private Long createdTime;
    public String createdTimeString;

    public Message(String message,String sender,String receiver) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
        this.createdTime= Instant.now().toEpochMilli();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.createdTimeString = formatter.format(new Date());
    }

    public Message(){
        this.createdTime= Instant.now().toEpochMilli();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        this.createdTimeString = formatter.format(new Date());
    }

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }


    public String getSender() { return sender; }

    public void setSender(String sender) { this.sender = sender; }

    public String getReceiver() { return receiver; }

    public void setReceiver(String receiver) { this.receiver = receiver; }

    public Long getCreatedTime() {
        return createdTime;
    }

    public String getCreatedTimeString() {
        return createdTimeString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(message, message1.message) && Objects.equals(sender, message1.sender) && Objects.equals(receiver, message1.receiver) && Objects.equals(createdTime, message1.createdTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(message, sender, receiver, createdTime);
    }
}
