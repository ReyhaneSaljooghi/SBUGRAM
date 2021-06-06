package Controller;

import Model.Clientconnection;
import Model.PageLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.io.IOException;

public class MenuController {
    public Button Go2Profile_button;
    public Button NewPost_button;
    public Button Logout_button;
    public Button follow_button;

    public void LogOut(ActionEvent actionEvent) {
        //??????/its not ok . fix it
       // Clientconnection.disconnectFromServer();
        try {
            new PageLoader().load("SignInOut");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void go2NewPostPage(ActionEvent actionEvent) {
    }

    public void gotoProfile(ActionEvent actionEvent) {
    }

    public void followList_action(ActionEvent actionEvent) {
    }
}
