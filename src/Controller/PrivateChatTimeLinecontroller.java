package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.ServerAndClient.Message;
import Model.ServerAndClient.Post;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class PrivateChatTimeLinecontroller {

    public JFXButton refresh_button;
    public JFXTextField text_feild;
    public JFXButton search_button;
    public ListView<Message> listView;
    ArrayList<Message> messages = new ArrayList<>();
    Message message=new Message();

    @FXML
    public void initialize() {
       //messages=
        listView.setItems(FXCollections.observableArrayList(messages));
        listView.setCellFactory(listView -> new ChatItem());
    }


    public void search(ActionEvent actionEvent) {
    }

    public void refresh(ActionEvent actionEvent) {
    }
}
