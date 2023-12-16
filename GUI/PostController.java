package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;

import System.*;

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

    private Post post;
    FeedController feedController;

    public void init(Post post, FeedController feedController){
        this.feedController = feedController;
        this.post = post;
        username.setText(FBsystem.users.get(post.getUserId()).getName());
        body.setText(post.content);
    }

    @FXML
    public void likePressed(){
       post.ReactorsID.add(FBsystem.CurUser.getId());
    }

    @FXML
    public void commentPressed(){
     
    }

    @FXML
    public void usernamePressed() throws IOException{
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
