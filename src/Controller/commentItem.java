package Controller;

import Model.ServerAndClient.Comment;
import Model.ServerAndClient.Post;
import Model.ServerAndClient.Post;
import javafx.scene.control.ListCell;

import java.io.IOException;


public class commentItem extends ListCell<Comment>  {
    @Override
    public void updateItem(Comment comment, boolean empty) {
        super.updateItem(comment, empty);
        if (comment != null) {
            try {
                setGraphic(new CommentItemcontroller(comment).init());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
            setGraphic(null );
    }
}
