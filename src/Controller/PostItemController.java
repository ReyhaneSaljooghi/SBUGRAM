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
import javafx.scene.shape.Circle;

import java.io.ByteArrayInputStream;
import java.io.IOException;

public class PostItemController {
    public AnchorPane root;
    public Label username_postitem;
    public Label title;
    public Label numberOflikes;
    public Label numberOfreposts;
    public Label DateAndTimelabel;
    public ImageView PosterProfileimage;
    public TextArea post_description_area;
    public Button view_profile_button;
    public Button Like_button;
    public Button add_comments_button;
    public Circle like_circle=new Circle() ;
    Post post;

    //each list item will have its exclusive controller in runtime so we set the controller as we load the fxml
    public PostItemController(Post post) throws IOException {
        new PageLoader().load("postItem", this);
        this.post = post;
    }

    //this anchor pane is returned to be set as the list view item
    public AnchorPane init() {
        username_postitem.setText(post.getWriter());
        title.setText(post.getTitle());
        DateAndTimelabel.setText(post.getCreatedTimeString());
        Image image;
        if (post.getPublisher().getProfileImage()!=null) {
            image = new Image(new ByteArrayInputStream(post.getPublisher().getProfileImage()));
            PosterProfileimage.setImage(image);
        }
        post_description_area.setText(post.getDescription());
        numberOflikes.setText(String.valueOf(post.Likers.size()));
        numberOfreposts.setText(String.valueOf(post.numberOfReposts));
        if (post.Likers.contains(Main.currentProfile)) {
            like_circle.setVisible(true);
        }
        return root;
    }
    /*
    you can also add on mouse click for like and repost image
     */
    public void LikeAction(ActionEvent actionEvent){
        if (post.Likers.contains(Main.currentProfile)) {
            return;
        }

      if (ClientHandlerCommands.Like(post,Main.currentusername))
        like_circle.setVisible(true);

    }

    public void   add_comments(ActionEvent actionEvent){

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

    }
    public void  view_profile(ActionEvent actionEvent){

    }
}
