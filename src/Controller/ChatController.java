package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Chat;
import Model.ServerAndClient.Message;
import Model.ServerAndClient.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ChatController {

/**this is the controller of chat fxml file */
    public Circle circle;
    public ListView<Message> listView;
    public Label username_label;
    public TextArea text_area;
    ArrayList<Message> messages;
    public static Chat chat;


    @FXML
    public void initialize() {

        chat=ClientHandlerCommands.getChat(Main.currentusername,chat.getAnother(Main.currentusername));
        chat=ClientHandlerCommands.setIsseen(Main.currentusername,chat.getAnother(Main.currentusername));
        messages = new ArrayList<>(chat.getMessages());
        listView.setItems(FXCollections.observableArrayList(messages));

        //customize each cell of postList with new graphic object PostItem
        listView.setCellFactory(postList -> new MessageItem());
        Image image = new Image(new ByteArrayInputStream
                (chat.getAnother
                        (ClientHandlerCommands.get_profile_by_Username(Main.currentusername))
                        .getProfileImage()));
        circle.setFill(new ImagePattern(image));
        username_label.setText(chat.getAnother(Main.currentusername));
    }

    public void send(ActionEvent actionEvent) {
        String text = text_area.getText();
        Message toSend = new Message();
        toSend.setMessage(text);
        toSend.setSender(Main.currentusername);
        toSend.setReceiver(chat.getAnother(Main.currentusername));
        Chat newchat = ClientHandlerCommands.sendMessage(toSend
        );
        chat = newchat;
        try {
            new PageLoader().load("Chat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void refresh(ActionEvent actionEvent) {
        try {
            new PageLoader().load("Chat");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void menu(ActionEvent actionEvent) {
        try {
            new PageLoader().load("Menu");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void go2ChatList(ActionEvent actionEvent) {
        try {
            new PageLoader().load("PrivateChatTimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
