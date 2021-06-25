package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Post;
import Model.ServerAndClient.Profile;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.*;
import java.util.ArrayList;
/*this is the controller for the current profile page
* so it has update button */
public class CurrentProfilePagecontroller {
    public static Profile thisProfile;
    public Label username_label;
    public TextField birthYear_field;
    public TextField name_field;
    public Button update_button;
    public Button deleteaccount_button;
    public Circle circle;
    public Label followings;
    public Label followers;
    public ListView<Post> postlist;
    public byte[]newImage;
    ArrayList<Post> allPostsOfThisProfile = new ArrayList<>();


    @FXML
    public void initialize() {
        thisProfile = ClientHandlerCommands.get_profile_by_Username(thisProfile.getUsername());

        this.name_field.setText(thisProfile.getName());
        this.followings.setText(String.valueOf(thisProfile.followings.size()));
        this.followers.setText(String.valueOf(thisProfile.followers.size()));
        this.username_label.setText(thisProfile.getUserName());
        this.birthYear_field.setText(thisProfile.getBirthYear());

        if (thisProfile.getProfileImage() != null) {
            Image image = new Image(new ByteArrayInputStream(thisProfile.getProfileImage()));
            circle.setFill(new ImagePattern(image));
        }
        this.allPostsOfThisProfile = ClientHandlerCommands.givePersonalPostsOfcurrentUser(username_label.getText());

        this.postlist.setItems(FXCollections.observableArrayList(this.allPostsOfThisProfile));

        this.postlist.setCellFactory(postList -> new PostItem());

    }

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void update(ActionEvent actionEvent) throws IOException {
        String name = name_field.getText();
        String year = birthYear_field.getText();
        System.out.println(Main.profileImage);
       thisProfile= ClientHandlerCommands.update(name, year,Main.profileImage, Main.currentusername);
        new PageLoader().load("CurrentProfilePage");
    }


    public void deleteaccount(ActionEvent actionEvent) {
        if (ClientHandlerCommands.deleteAccount(Main.currentusername)) {
            try {
                new PageLoader().load("SignInOut");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void go2postList(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void browseImage(ActionEvent actionEvent) throws IOException {
        FileChooser fileChooser=new FileChooser() ;
        File file=fileChooser.showOpenDialog(new Popup());
        if (file==null){
            return;
        }
        System.out.println(Main.profileImage);
        FileInputStream fileInputStream=new FileInputStream(file);
        Main.profileImage=fileInputStream.readAllBytes();
        Image image=new Image(new ByteArrayInputStream(Main.profileImage));
        System.out.println(Main.profileImage);
        circle.setFill(new ImagePattern(image));
    }
}
