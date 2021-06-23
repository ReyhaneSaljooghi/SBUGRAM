package Controller;

import Model.ClientHandlerCommands;
import Model.Main;
import Model.PageLoader;
import Model.ServerAndClient.Post;
import Model.ServerSide.ServerMain;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
/* this is the controller for each post */
public class PostItemController {
    public AnchorPane root;
    public Label username_postitem;
    public Label title;
    public Label numberOflikes;
    public Label numberOfreposts;
    public Label DateAndTimelabel;
    public Circle circle;
    public ImageView postImageview;
    public ImageView like_imageView;
    public TextArea post_description_area;
    public Button view_profile_button;
    public Button Like_button;

    public Button add_comments_button;
    Post post;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new PageLoader().load("PostItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() throws IOException {
        username_postitem.setText(post.getWriter());
        title.setText(post.getTitle());
        DateAndTimelabel.setText(post.getCreatedTimeString());
        Image image;
        if (post.getPublisher().getProfileImage()!=null) {
            image = new Image(new ByteArrayInputStream(post.getPublisher().getProfileImage()));
            circle.setFill(new ImagePattern(image));
        }
        if (post.getImageAttachedTopost()!=null){
            image = new Image(new ByteArrayInputStream(post.getImageAttachedTopost()));
            postImageview.setImage(image);
        }
        post_description_area.setText(post.getDescription());
        numberOflikes.setText(String.valueOf(post.Likers.size()));
        numberOfreposts.setText(String.valueOf(post.numberOfReposts));
        if (post.Likers.contains(Main.currentProfile)) {
            File file=new File("images/like.jpg");
            byte[]b= Files.readAllBytes(file.toPath());
            Image image1=new Image(new ByteArrayInputStream(b));
            like_imageView.setImage(image1);

        }
        return root;
    }
    /*
    you can also add on mouse click for like and repost image
     */
    public void LikeAction(ActionEvent actionEvent) throws IOException {
        if (post.Likers.contains(Main.currentProfile)) {
            return;
        }

      if (ClientHandlerCommands.Like(post,Main.currentusername)) {
          File file=new File("images/like.jpg");
          byte[]b= Files.readAllBytes(file.toPath());
          Image image1=new Image(new ByteArrayInputStream(b));
          like_imageView.setImage(image1);
      }

    }

    public void   add_comments(ActionEvent actionEvent){
        Addcommentcontroller.post=post;
        try {
            new PageLoader().load("Addcomment");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void   Repost(ActionEvent actionEvent){
        ClientHandlerCommands.Repost(post,Main.currentusername);
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void   view_comments(ActionEvent actionEvent){
        try {
            showAllCommentscontroller.post=post;
            new PageLoader().load("showAllComments");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void  view_profile(ActionEvent actionEvent){
        ProfilePagecontroller.profile=post.getPublisher();
        try {
            if (!post.publisher.getUserName().equals(Main.currentusername)) {
                ProfilePagecontroller.profile=post.getPublisher();
                new PageLoader().load("ProfilePage");
            }
            else {
                CurrentProfilePagecontroller.thisProfile=ClientHandlerCommands.get_profile_by_Username(Main.currentusername);
                new PageLoader().load("CurrentProfilePage");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
