package Controller;

import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Comment;
import Model.ServerAndClient.Post;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
/*this class control each comment item*/
public class CommentItemcontroller {
    public AnchorPane root;
    public TextArea commentText_area;
    public Label  writername_label;
    Comment comment;

    public CommentItemcontroller(Comment comment) throws IOException {
        new PageLoader().load("CommentItem", this);
        this.comment = comment;
    }
    public AnchorPane init() throws IOException {
        commentText_area.setText(comment.getCommentText());
        writername_label.setText(comment.getWriter());
        return root;
    }

}
