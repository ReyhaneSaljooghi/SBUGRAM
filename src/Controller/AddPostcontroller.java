package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Post;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.util.Duration;

import java.io.*;
import java.nio.file.Files;

public class AddPostcontroller {
    public TextField title_field;
    public TextArea descroption_field;
    public Button browse_image_button;
    public ImageView image_view_field;
    public Button publish_button;
    public Button go2postlist_button;
    public byte[]image2set;
    @FXML
    public void initialize() {
        TranslateTransition t = new TranslateTransition(Duration.millis(1600), publish_button);
        t.setToY(-45);
        t.playFromStart();
        TranslateTransition t2 = new TranslateTransition(Duration.millis(1720), go2postlist_button);
        t2.setToY(-55);
        t2.playFromStart();
        File file=new File("images/noImage.jpg");
        byte[]b= new byte[0];
        try {
            b = Files.readAllBytes(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Image image=new Image(new ByteArrayInputStream(b));
        image_view_field.setImage(image);
    }


    public void Browse_image(ActionEvent actionEvent) {
        FileChooser fileChooser=new FileChooser() ;
        File file=fileChooser.showOpenDialog(new Popup());
        if (file==null){
            return;
        }
        FileInputStream fileInputStream= null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        byte[]b= new byte[0];
        try {
            b = fileInputStream.readAllBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        image2set=b;
        Image image=new Image(new ByteArrayInputStream(b));
       image_view_field.setImage(image);
    }

    public void Publish(ActionEvent actionEvent) {
        Post newPost=new Post();
        newPost.setTitle(title_field.getText());
        newPost.setDescription(descroption_field.getText());
        newPost.setPublisher(Main.currentProfile);
        newPost.setWriter(Main.currentusername);
        newPost.setImageAttachedTopost(image2set);
        ClientHandlerCommands.Addpost(newPost,Main.currentusername);
    }

    public void loadPostlist(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
