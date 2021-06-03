package Model;

import Model.ServerAndClient.Profile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Main extends Application {
    public static Profile currentProfile;
    public  static String currentusername;
    public  static String currentpassword;
    public static byte[] profileImage;

    public static void setCurrentProfile(Profile currentProfile) {
        Main.currentProfile = currentProfile;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        PageLoader.initStage(primaryStage);
        new PageLoader().load("SignInOut");
    }

    public static void main(String[] args) {
        Clientconnection.connectToServer();
        launch(args);
        System.out.println("it is connected to server");
    }


}
