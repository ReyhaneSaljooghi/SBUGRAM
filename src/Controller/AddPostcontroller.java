package Controller;

import Model.PageLoader;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;

public class AddPostcontroller {
    public TextField title_field;
    public TextArea descroption_field;
    public Button browse_image_button;
    public ImageView image_view_field;
    public Button publish_button;
    public Button go2postlist_button;
    @FXML
    public void initialize() {
        TranslateTransition t = new TranslateTransition(Duration.millis(1600), publish_button);
        t.setToY(-45);
        t.playFromStart();
        TranslateTransition t2 = new TranslateTransition(Duration.millis(1720), go2postlist_button);
        t2.setToY(-55);
        t2.playFromStart();
        TranslateTransition t3 = new TranslateTransition(Duration.millis(2000), browse_image_button);
        t3.setToX(+26);
        t3.playFromStart();
    }


    public void Browse_image(ActionEvent actionEvent) {
    }

    public void Publish(ActionEvent actionEvent) {
    }

    public void loadPostlist(ActionEvent actionEvent) {
        try {
            new PageLoader().load("TimeLine");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
