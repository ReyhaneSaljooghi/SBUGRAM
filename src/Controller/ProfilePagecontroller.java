package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Post;
import Model.ServerAndClient.Profile;
import com.jfoenix.controls.JFXButton;
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
/**this class controls profile page of another user not the current one */
public class ProfilePagecontroller {
    public static Profile profile;
    public  Label following_label;
    public  Label followers_label;
    public  Label name_label;
    public  Label username_label;
    public  Label birthdate_label;
    public  Button follow_button;
    public ListView<Post> postlist;
    public  ImageView imageview_field;
    public Button unfollow_button1;
    public JFXButton unmute_button;
    public JFXButton mute_button1;
    public JFXButton unblock_button;
    public JFXButton block_button;
    ArrayList<Post> allPostsOfThisProfile = new ArrayList<>();
    Post thisPost = new Post();
    @FXML
    public void initialize() {
        profile=ClientHandlerCommands.get_profile_by_Username(profile.getUsername());
        Profile current=ClientHandlerCommands.get_profile_by_Username(Main.currentusername);
        if (current.followings.contains(profile)){
            unfollow_button1.setVisible(true);
        }
        else
            follow_button.setVisible(true);
        if (current.mutedUsers.contains(profile)) {
            unmute_button.setVisible(true);
        }
        else {
           mute_button1.setVisible(true);
        }
        if (current.blockedUsers.contains(profile)) {
            unblock_button.setVisible(true);
        }
        else {
           block_button.setVisible(true);
        }

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
        ClientHandlerCommands.block(Main.currentusername,profile.getUsername());

    }
    public void unblock(ActionEvent actionEvent) {
        ClientHandlerCommands.unBlock(Main.currentusername,profile.getUsername());
    }

    public void mute(ActionEvent actionEvent) {

        ClientHandlerCommands.mute(Main.currentusername,profile.getUserName());

    }
    public void unmute(ActionEvent actionEvent) {
        ClientHandlerCommands.unMute(Main.currentusername,profile.getUsername());
    }

    public void unfollow(ActionEvent actionEvent) {
        ClientHandlerCommands.unfollow(Main.currentusername,profile.getUsername());
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void go2timeline(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
