package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.ServerAndClient.Comment;
import Model.ServerAndClient.Post;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.ArrayList;

public class showAllCommentscontroller {
    public ListView<Comment> CommentList;

    ArrayList<Comment> comments = new ArrayList<>();

    @FXML
    public void initialize() {
        Comment comment=new Comment("me1","des1");
        Comment comment2=new Comment("me2","des2");
        Comment comment3=new Comment("me3","des3");
       comments.add(comment);
       comments.add(comment2);
       comments.add(comment3);
       CommentList.setItems(FXCollections.observableArrayList(comments));
        CommentList.setCellFactory(CommentList -> new commentItem());
    }
}
