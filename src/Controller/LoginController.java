package Controller;

import Model.ClientHandlerCommands;
import Model.Clientconnection;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Profile;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Duration;

import java.io.IOException;

public class LoginController {
    @FXML
    public Button sign_in_button;
    public PasswordField pass_word_feild;
    public TextField username_feild;
    public Button sign_up_button;
    public Label create_account_label;
    public Label wrong_password_label;
    public Label wrong_username_label;
    public TextField password_visible;
    public CheckBox check_button;

    @FXML
    public void initialize() {
        TranslateTransition t = new TranslateTransition(Duration.millis(1600), sign_in_button);
        t.setToY(-45);
        t.playFromStart();
        TranslateTransition t2 = new TranslateTransition(Duration.millis(1720), sign_up_button);
        t2.setToY(-55);
        t2.playFromStart();
        TranslateTransition t3 = new TranslateTransition(Duration.millis(2000), check_button);
        t3.setToX(+26);
        t3.playFromStart();


    }

    public void Sign_in_action(ActionEvent actionEvent) throws IOException {
        if (pass_word_feild.isVisible())
           Main.currentpassword = pass_word_feild.getText();
        else
            Main.currentpassword= password_visible.getText();
        if (!ClientHandlerCommands.CheckUsername(username_feild.getText())){
            System.out.println("wrong username");
            wrong_username_label.setVisible(true);
            return;
        }
        Main.currentusername = username_feild.getText();
        Profile profile = ClientHandlerCommands.sign_in(Main.currentusername,Main.currentpassword);
        if(profile == null){
            wrong_password_label.setVisible(true);
            System.out.println("wrong pass");
            return;
        }
        else {
            Main.setCurrentProfile(profile);
            new PageLoader().load("TimeLine");
        }
        wrong_username_label.setVisible(false);
        wrong_password_label.setVisible(false);

    }

    public void Sign_up_action(ActionEvent actionEvent) {

        try {
            new PageLoader().load("Createaccount");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void show_password(ActionEvent actionEvent) {
        if (!password_visible.isVisible()) {
            password_visible.setVisible(true);
            pass_word_feild.setVisible(false);
            password_visible.setText(pass_word_feild.getText());
        } else {
            password_visible.setVisible(false);
            pass_word_feild.setVisible(true);
            pass_word_feild.setText(password_visible.getText());
        }
    }
}