package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Profile;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
/*this class controls forget_password fxml file*/
public class ForgetPassswordcontroller {
    public JFXTextField username_field;
    public JFXTextField email_field;
    public JFXTextField password_field;

    @FXML
    public void initialize() throws IOException {

    }


    public void getPassword(ActionEvent actionEvent) {
        String username=username_field.getText();
        String email=email_field.getText();
        if(ClientHandlerCommands.getPass(username,email)){
           Profile profile= ClientHandlerCommands.get_profile_by_Username(username);
           password_field.setText(profile.getPassword());
        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"The email is not correct!");
            alert.showAndWait();
            return;
        }
    }

    public void signIn(ActionEvent actionEvent) {
        try {
            new PageLoader().load("SignInOut");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
