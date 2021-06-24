package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Chat;
import Model.ServerAndClient.Message;
import Model.ServerAndClient.Post;
import Model.ServerAndClient.Profile;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class PrivateChatTimeLinecontroller {

    public JFXButton refresh_button;
    public JFXTextField text_feild;
    public JFXButton search_button;
    public ListView<Chat> listView;
    ArrayList<Chat> Chats = new ArrayList<>();
   Chat chat=new Chat();

    @FXML
    public void initialize() {
        Chats=ClientHandlerCommands.getAllChat(Main.currentusername);
       Collections.sort(Chats);

        listView.setItems(FXCollections.observableArrayList(Chats));
        listView.setCellFactory(listView -> new ChatItem());
    }



        public void search(ActionEvent actionEvent) {
//      String second= text_feild.getText();
            Chats = (ArrayList<Chat>) Chats.stream().filter(chat1 -> chat1.getAnother(Main.currentusername).
                    equals(text_feild.getText())).collect(Collectors.toList());
//      Chat chat=ClientHandlerCommands.getChat(Main.currentusername,second);
//      Chats.clear();
//      Chats.add(chat);

            listView.setItems(FXCollections.observableArrayList(Chats));
            listView.setCellFactory(listView -> new ChatItem());
        }


    public void refresh(ActionEvent actionEvent) {
        //Chats=ClientHandlerCommands.getAllChat(Main.currentusername);
        try {
            new PageLoader().load("PrivateChatTimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
