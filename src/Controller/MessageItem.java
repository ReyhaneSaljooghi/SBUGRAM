package Controller;

import Model.ServerAndClient.Message;
import Model.ServerAndClient.Post;
import javafx.scene.control.ListCell;

import java.io.IOException;


public class MessageItem extends ListCell<Message> {

    @Override
    public void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);
        if (message != null) {
            try {
                setGraphic(new MessageItemController(message).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            setGraphic(null);
    }
}