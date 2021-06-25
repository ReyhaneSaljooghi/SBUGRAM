package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Comment;
import Model.ServerAndClient.Post;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

import java.io.IOException;
/**this is the controller of add comment fxml file
 * @author reyhane:)
 */
public class Addcommentcontroller {
    public TextArea text_area_field;
    public Button comment_button;
    public static Post post ;

    public void setPost(Post post) {
        this.post = post;
    }

    public void releaseComment(ActionEvent actionEvent) {

        Comment newComment = new Comment(Main.currentusername, text_area_field.getText());
        ClientHandlerCommands.addcomment(newComment, post);

        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
