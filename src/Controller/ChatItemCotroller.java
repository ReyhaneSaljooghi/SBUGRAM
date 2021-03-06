package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Chat;
import Model.ServerAndClient.Message;
import Model.ServerAndClient.Post;
import Model.ServerSide.ServerMain;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
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
/**this is the controller for chat item fxml but it is not set for it
* each list item will have its exclusive controller in runtime so we set the controller as we load the fxml*/
public class ChatItemCotroller {
    public AnchorPane root;
    public Label username_field;
    public Label date_label;
    public Circle circle;
    Chat chat;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public ChatItemCotroller(Chat chat) throws IOException {
        new PageLoader().load("ChatItem", this);
       this.chat=chat;

    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() throws IOException {
       for (Message it:chat.getMessages()){
           if (it.getReceiver().equals(Main.currentusername)) {
               if (!it.isSeen) {
                   File file = new File("images/newMesasage.jpg");
                   Image image = new Image(new ByteArrayInputStream(Files.readAllBytes(file.toPath())));
                   circle.setFill(new ImagePattern(image));
                   circle.setVisible(true);
               }
           }
       }

        username_field.setText(chat.getAnother(Main.currentusername));
        if (chat.getMessages().size()!=0){
            date_label.setText(chat.getMessages().get(chat.getMessages().size()-1).createdTimeString);
        }
        return root;
    }
//we enter to the chat with  a specific person
    public void   enterChat(ActionEvent actionEvent){
        if (ClientHandlerCommands.get_profile_by_Username(chat.getAnother(Main.currentusername)).blockedUsers
                .contains(ClientHandlerCommands.get_profile_by_Username(Main.currentusername))){
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"This user has blocked you!");
            alert.showAndWait();
            return;
        }
        ChatController.chat=chat;
        try {
            new PageLoader().load("Chat");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
