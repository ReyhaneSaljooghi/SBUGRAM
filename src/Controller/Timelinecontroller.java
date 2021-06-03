package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;

public class Timelinecontroller {


    public TextField title_field;
    public TextArea description_field;
    public ListView<Post> postList;
    public Button refresh_button;
    public Button Menu_button;

    ArrayList<Post> posts = new ArrayList<>();
    Post currentPost = new Post();

    @FXML
    public void initialize() {
        posts= ClientHandlerCommands.givePostsOfcurrentUser(Main.currentusername);
        postList.setItems(FXCollections.observableArrayList(posts));

        //customize each cell of postList with new graphic object PostItem
        postList.setCellFactory(postList -> new PostItem());
    }

    public void addPost(ActionEvent actionEvent) {
        //set the post features
        currentPost.setTitle(title_field.getText());
        currentPost.setDescription(description_field.getText());
        currentPost.setWriter("ali alavi");

        //save the post in arraylist
        posts.add(currentPost);

        //show the arraylist in listview
        postList.setItems(FXCollections.observableArrayList(posts));
        postList.setCellFactory(postList -> new PostItem());

        /*
        if the listview cells are not customized and list view is made of strings
        this code will add new post title to the list view
        postList.getItems().add(currentPost.getTitle());
         */

        currentPost = new Post();

        //empty fields
        title_field.setText("");
        description_field.setText("");
    }

    public void clear(ActionEvent actionEvent) {
        title_field.setText("");
        description_field.setText("");
    }


    //this function is usable for uncustomized_cell listview of strings
    public void showPost(MouseEvent mouseEvent) {
        Post p = new Post();
        //p.setTitle(postList.getSelectionModel().getSelectedItem());
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).equals(p)) {
                title_field.setText(posts.get(i).getTitle());
                description_field.setText(posts.get(i).getDescription());
            }
        }
    }
//check kon ino
    public void Refresh(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void GotoMenu(ActionEvent actionEvent) {
    }
}

