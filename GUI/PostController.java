package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
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
    Label LikesLabel;
    @FXML
    private Button commentButton;
    @FXML
    Label commentsLabel;
    @FXML
    private ToggleGroup privacySettings;

    private Post post;
    FeedController feedController;

    public void init(Post post, FeedController feedController){
        this.feedController = feedController;
        this.post = post;
        username.setText(FBsystem.users.get(post.getUserId()).getName());
        body.setText(post.content);
        LikesLabel.setText(post.ReactorsID.size() + " Likes");
        if(post.ReactorsID.contains(FBsystem.CurUser.getId())){
            likeButton.setText("Unlike");
        }
        commentsLabel.setText(post.Comments.getSize() + " Comments");

    }

    private void react(){
        if(post.ReactorsID.contains(FBsystem.CurUser.getId())){
            post.ReactorsID.remove(FBsystem.CurUser.getId());
            likeButton.setText("Like");
        }
        else{
            post.ReactorsID.add(FBsystem.CurUser.getId());
            likeButton.setText("Unlike");
        }
        LikesLabel.setText(post.ReactorsID.size() + " Likes");
    }

    @FXML
    public void likePressed(){
       react();
    }

    @FXML
    public void commentPressed(){
     
    }

    @FXML
    public void usernamePressed() throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/profile.fxml"));
        VBox profile = loader.load();
        ProfileController controller = loader.getController();

        controller.init(post.getUserId(), feedController);
        
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
