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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ProfilePagecontroller {
    public static Profile profile;
    public  Label following_label;
    public  Label followers_label;
    public  Label name_label;
    public  Label username_label;
    public  Label birthdate_label;
    public  Button follow_button;
    public Button block_button;
    public Button mute_button;
    public ListView<Post> postlist;
    public  ImageView imageview_field;
    public Button unfollow_button1;
    ArrayList<Post> allPostsOfThisProfile = new ArrayList<>();
    Post thisPost = new Post();
    @FXML
    public void initialize() {
        profile=ClientHandlerCommands.get_profile_by_Username(profile.getUsername());
        if (ClientHandlerCommands.get_profile_by_Username(Main.currentusername).followings.contains(profile)){
            unfollow_button1.setVisible(true);
        }
        else
            follow_button.setVisible(true);

       this.name_label.setText(profile.getName());
       this.following_label.setText(String.valueOf(profile.followings.size()));
       this.followers_label.setText(String.valueOf(profile.followers.size()));
       this.username_label.setText(profile.getUserName());

       this.birthdate_label.setText(profile.getBirthYear());

       if (profile.getProfileImage()!=null) {
           Image image = new Image(new ByteArrayInputStream(profile.getProfileImage()));
           this.imageview_field.setImage(image);
       }
        allPostsOfThisProfile= ClientHandlerCommands.givePostsOfcurrentUser(username_label.getText());

        postlist.setItems(FXCollections.observableArrayList(allPostsOfThisProfile));

        postlist.setCellFactory(postList -> new PostItem());

    }

    public void follow(ActionEvent actionEvent) {
        ClientHandlerCommands.follow(Main.currentusername,profile.getUsername());
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void block(ActionEvent actionEvent) {
    }

    public void mute(ActionEvent actionEvent) {
    }

    public void unfollow(ActionEvent actionEvent) {
        ClientHandlerCommands.unfollow(Main.currentusername,profile.getUsername());
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
