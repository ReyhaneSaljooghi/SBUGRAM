package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
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
import java.util.ArrayList;

public class ProfilePagecontroller {
    public static Profile profile;
    public  Label following_label;
    public  Label followers_label;
    public  Label name_label;
    public  Label username_label;
    public  Label location_label;
    public  Label birthdate_label;
    public  Button follow_button;
    public Button block_button;
    public Button mute_button;
    public ListView<Post> postlist;
    public  ImageView imageview_field;
    ArrayList<Post> allPostsOfThisProfile = new ArrayList<>();
    Post thisPost = new Post();
    @FXML
    public void initialize() {
       this.name_label.setText(profile.getName());
        this.followers_label.setText(String.valueOf(profile.followings.size()));
       this.username_label.setText(profile.getUserName());
        Image image = new Image(new ByteArrayInputStream(profile.getProfileImage()));
        this.imageview_field.setImage(image);

        allPostsOfThisProfile= ClientHandlerCommands.givePostsOfcurrentUser(username_label.getText());
        postlist.setItems(FXCollections.observableArrayList(allPostsOfThisProfile));

        //customize each cell of postList with new graphic object PostItem
        postlist.setCellFactory(postList -> new PostItem());
    }

    public void follow(ActionEvent actionEvent) {
    }

    public void block(ActionEvent actionEvent) {
    }

    public void mute(ActionEvent actionEvent) {
    }
}
