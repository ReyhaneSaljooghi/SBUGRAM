package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Comment;
import Model.ServerAndClient.Post;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.util.ArrayList;
/**this is the controller of showComments fxml file*/
public class showAllCommentscontroller {
    public ListView<Comment> CommentList;
    public static  Post post;
    public Button backbutton;
    ArrayList<Comment> comments ;

    @FXML
    public void initialize() {
       comments=new ArrayList<>(post.comments);
       CommentList.setItems(FXCollections.observableArrayList(comments));
       CommentList.setCellFactory(CommentList -> new commentItem());
    }

    public void goBack(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
