package GUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class PostController {

    @FXML
    private VBox panel;
    @FXML
    private Hyperlink username;
    @FXML
    private Text body;
    @FXML
    private Button likeButton;
    @FXML
    private Button commentButton;
    @FXML
    private ToggleGroup privacySettings;

    FeedController feedController;

    public void init(String usernameString, String bodyString, FeedController feedController){
        username.setText(usernameString);
        body.setText(bodyString);
        this.feedController = feedController;
    }

    @FXML
    private void likePressed(){

    }

    @FXML
    private void commentPressed(){

    }

    @FXML
    private void usernamePressed() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/profile.fxml"));
        VBox profile = loader.load();
        ProfileController controller = loader.getController();

        controller.init(username.getText(), feedController);

        feedController.postsScrollpane.setContent(profile);
        feedController.postsScrollpane.setVvalue(feedController.postsScrollpane.getVmin());
    }

    @FXML
    private void usernameHover() {
        username.setStyle("-fx-text-fill: #0077b6; -fx-underline: false;");
    }

    @FXML
    private void usernameDefault() {
        username.setStyle("-fx-text-fill: #0d1b2a; -fx-underline: false;");
    }
}
