package Controller;

import Model.ServerAndClient.Post;
import javafx.scene.control.ListCell;

import java.io.IOException;
/*this is the post item class in the client side
* it is different from the post class which is common between server and client */
public class PostItem extends ListCell<Post> {

    @Override
    public void updateItem(Post post, boolean empty) {
        super.updateItem(post, empty);
        if (post != null) {
            try {
                setGraphic(new PostItemController(post).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            setGraphic(null );
    }
}