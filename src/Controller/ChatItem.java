package Controller;

import Model.ServerAndClient.Chat;
import Model.ServerAndClient.Message;
import Model.ServerAndClient.Post;
import javafx.scene.control.ListCell;

import java.io.IOException;
/*this is the post item class in the client side
 * it is different from the post class which is common between server and client */
public class ChatItem extends ListCell<Chat> {

    @Override
    public void updateItem(Chat chat, boolean empty) {
        super.updateItem(chat, empty);
        if (chat != null) {
            try {
                setGraphic(new ChatItemCotroller(chat).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            setGraphic(null );
    }
}