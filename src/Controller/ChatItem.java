package Controller;

import Model.ServerAndClient.Message;
import Model.ServerAndClient.Post;
import javafx.scene.control.ListCell;

import java.io.IOException;
/*this is the post item class in the client side
 * it is different from the post class which is common between server and client */
public class ChatItem extends ListCell<Message> {
//ina kharabe
    @Override
    public void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);
        if (message != null) {
            try {
                setGraphic(new ChatItemCotroller(message).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            setGraphic(null );
    }
}