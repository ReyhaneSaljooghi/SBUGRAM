package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Profile;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.util.Duration;

import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Createaccountcontroller  {
    private final static String PICTURE_DEFAULT = "images/2.jpg";
    public TextField name_feild;
    public TextField lastname_field;
    public TextField username_feild;
    public TextField password_field;
    public TextField phoneNum_field;
    public TextField birthyear_field;
    public Circle circle;

    @FXML
    public void initialize() throws IOException {
       File file=new File("C:\\Users\\Saljooghi\\Desktop\\Project\\images\\istockphoto-922962354-612x612.jpg");
       Main.profileImage=Files.readAllBytes(file.toPath());
        Image image=new Image(new ByteArrayInputStream(Main.profileImage));
        circle.setFill(new ImagePattern(image));
    }



    public void sign_up(ActionEvent actionEvent) {
        System.out.println(username_feild.getText());
        if ( hasEmptyField() ) return;
        if (!passwordisValid(password_field.getText()) ) return;
       if( ClientHandlerCommands.CheckUsername(username_feild.getText())){
           Alert alert=new Alert(Alert.AlertType.INFORMATION,"This username has been already taken!");
           alert.showAndWait();
           return;
       }
        Main.currentusername=username_feild.getText();
        Main.currentpassword=password_field.getText();
        Profile newProfile= makeprofile();
        Main.setCurrentProfile(newProfile);
        Main.setCurrentProfile(newProfile);
        ClientHandlerCommands.sign_up(newProfile);
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public Profile makeprofile(){
        Profile ans=new Profile(username_feild.getText());
        ans.setPassword(password_field.getText());
        ans.setName(name_feild.getText());
        ans.setPhoneNumber(phoneNum_field.getText());
        ans.setProfileImage(Main.profileImage);
        ans.setBirthYear(birthyear_field.getText());
        return ans;
    }

    public void browseImage(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser=new FileChooser() ;
        File file=fileChooser.showOpenDialog(new Popup());
        if (file==null){
            return;
        }
        FileInputStream fileInputStream=new FileInputStream(file);
        Main.profileImage=fileInputStream.readAllBytes();
        Image image=new Image(new ByteArrayInputStream(Main.profileImage));
        circle.setFill(new ImagePattern(image));

    }

    public void sign_in(ActionEvent actionEvent) {
        try {
            new PageLoader().load("SignInOut");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean hasEmptyField(){
        if ( username_feild.getText().isEmpty()
                || password_field.getText().isEmpty()
                || name_feild.getText().isEmpty()
        ) {
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"Fill out the necessary fields!");
            alert.showAndWait();
            return true;
        }
        return false;
    }
    private static final String PASSWORD_PATTERN =
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean passwordisValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return true;
        }
        else {
            Alert alert=new Alert(Alert.AlertType.INFORMATION,"password should have at least" +
                    " 8 chars including number,upper and lower alpha chars!");
            alert.showAndWait();
            return false;

        }
    }


}
