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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;

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
    public ListView <Post>postlist;
    ArrayList<Post> allPostsOfThisProfile = new ArrayList<>();
    Post thisPost = new Post();

    @FXML
    public void initialize() {
        //thisProfile=ClientHandlerCommands.get_profile_by_Username(thisProfile.getUsername());
        System.out.println(thisProfile.getBirthYear());
        System.out.println(thisProfile.getName());

        this.name_field.setText(thisProfile.getName());
        this.followings.setText(String.valueOf(thisProfile.followings.size()));
        this.followers.setText(String.valueOf(thisProfile.followers.size()));
        this.username_label.setText(thisProfile.getUserName());
        this.birthYear_field.setText(thisProfile.getBirthYear());

        if (thisProfile.getProfileImage()!=null) {
            Image image = new Image(new ByteArrayInputStream(thisProfile.getProfileImage()));
           circle.setFill(new ImagePattern(image));
        }
        this.allPostsOfThisProfile= ClientHandlerCommands.givePostsOfcurrentUser(username_label.getText());

        this.postlist.setItems(FXCollections.observableArrayList(this.allPostsOfThisProfile));

        this.postlist.setCellFactory(postList -> new PostItem());

    }

    public void update(ActionEvent actionEvent) throws IOException {
        String name=name_field.getText();
        String year=birthYear_field.getText();
        ClientHandlerCommands.update(name,year,Main.currentusername);
        thisProfile.setBirthYear(year);
        thisProfile.setName(name);
        new PageLoader().load("CurrentProfilePage");
    }

    public void deletAccount(ActionEvent actionEvent) {
    }
}
