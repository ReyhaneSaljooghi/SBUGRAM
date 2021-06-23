package Model.ServerAndClient;

import java.io.File;
import java.time.LocalDateTime;

public class Message {
    private String message;
    private LocalDateTime date;
    private String sender;
    private String receiver;
    private static final long serialVersionUID = 102L;

    public Message(String message,String sender,String receiver) {
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
    }

    public Message(){}

    public String getMessage() { return message; }

    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }

    public String getSender() { return sender; }

    public void setSender(String sender) { this.sender = sender; }

    public String getReceiver() { return receiver; }

    public void setReceiver(String receiver) { this.receiver = receiver; }


}
