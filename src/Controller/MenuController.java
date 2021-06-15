package Controller;

import Model.ClientHandlerCommands;
import Model.Clientconnection;
import Model.Main;
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
        ClientHandlerCommands.logout(Main.currentusername);
        try {
            new PageLoader().load("SignInOut");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void go2NewPostPage(ActionEvent actionEvent) {
        try {
            new PageLoader().load("AddPost");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void gotoProfile(ActionEvent actionEvent) {
        CurrentProfilePagecontroller.thisProfile=ClientHandlerCommands.get_profile_by_Username(Main.currentusername);
        try {
            new PageLoader().load("CurrentProfilePage");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void followList_action(ActionEvent actionEvent)  {
        try {
            new PageLoader().load("Search2Follow");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
