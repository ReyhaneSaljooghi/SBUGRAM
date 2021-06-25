package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Message;
import Model.ServerAndClient.Post;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
/*this is the controller of message item.
 * each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
 */
public class MessageItemController {
    public AnchorPane root;
    public Label message_text;
    public TextArea hidden_text;
    public Label time_label;
    public Button finish_button;
    public Button edit_button;
    public Circle circle;
    Message message;

    public MessageItemController(Message message) throws IOException {
        new PageLoader().load("MessageItem", this);
        this.message = message;
    }

    public AnchorPane init() throws IOException {
        File file=new File("images/greenTick.png");
        Image image=new Image(new ByteArrayInputStream(Files.readAllBytes(file.toPath())));
        circle.setFill(new ImagePattern(image));
        circle.setVisible(false);
        if (!message.getSender().equals(Main.currentusername)){
            edit_button.setVisible(false);
        }
        message_text.setText(message.getMessage());
        time_label.setText(message.createdTimeString);
        if (message.getSender().equals(Main.currentusername))
            message_text.setStyle("-fx-background-color: #72bfb9");
        else {
            message_text.setStyle("-fx-background-color: #aabcb5");
        }
        return root;
    }

    public void delete(ActionEvent actionEvent) {
        ClientHandlerCommands.deleteMessage(message);
    }

    public void edit(ActionEvent actionEvent) {
        message_text.setVisible(false);
        hidden_text.setVisible(true);
        circle.setVisible(true);
        finish_button.setVisible(true);

    }
    public void finishEdit (ActionEvent actionEvent){
        ClientHandlerCommands.editMessage(message,hidden_text.getText());
        message_text.setText(hidden_text.getText());
        hidden_text.setVisible(false);
        circle.setVisible(false);
        try {
            new PageLoader().load("Chat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
