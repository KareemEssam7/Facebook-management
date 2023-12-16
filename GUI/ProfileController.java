package GUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import System.*;
import CustomStructures.*;

public class ProfileController {
    @FXML
    VBox profileVBox;
    @FXML
    Button friendButton;
    @FXML
    Button restrictButton;
    @FXML
    Button blockButton;
    @FXML
    Label username;
    @FXML
    VBox profileFeed;

    FeedController feedController;

    public void init(String username, FeedController feedController) throws IOException{

        this.feedController = feedController;

        profileVBox.prefWidthProperty().bind(feedController.postsScrollpane.widthProperty().subtract(10));

        this.username.setText(username);

        //add profile posts here
        
        for( Node<Integer> it = FBsystem.CurUser.feed.iteratorToStart(); it != null; it = FBsystem.CurUser.feed.nextValue()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/post.fxml"));
            VBox post = loader.load();
            PostController controller = loader.getController();
            controller.init(FBsystem.posts.get(it.value()),feedController);
            profileFeed.getChildren().add(post); 
        }

        // FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/post.fxml"));
        // VBox post = loader.load();
        // PostController controller = loader.getController();
        // controller.init(username, "Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2.", feedController);
        // profileFeed.getChildren().add(post);

        // FXMLLoader load = new FXMLLoader(getClass().getResource("FXMLs/post.fxml"));
        // VBox post2 = load.load();
        // PostController controller2 = load.getController();
        // controller2.init(username, "Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2.", feedController);
        // profileFeed.getChildren().add(post2);

        // FXMLLoader load3 = new FXMLLoader(getClass().getResource("FXMLs/post.fxml"));
        // VBox post23 = load3.load();
        // PostController controller23 = load3.getController();
        // controller23.init(username, "Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2.", feedController);
        // profileFeed.getChildren().add(post23);

    }
    @FXML
    private void friendButtonPressed(){

    }
    @FXML
    private void restrictButtonPressed(){

    }
    @FXML
    private void blockButtonPressed(){

    }
}
