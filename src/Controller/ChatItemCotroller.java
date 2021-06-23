package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Message;
import Model.ServerAndClient.Post;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
public class ChatItemCotroller {
    public AnchorPane root;
    Label username_field;
    Label date_label;
    JFXButton directmessage_button;
    Message message;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public ChatItemCotroller(Message message) throws IOException {
        new PageLoader().load("ChatItem", this);
       this.message=message;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() throws IOException {


        return root;
    }

    public void   enterChat(ActionEvent actionEvent){

    }
}
