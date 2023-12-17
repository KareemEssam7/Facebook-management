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

    int Userid;
    FeedController feedController;

    public void init(int Userid, FeedController feedController) throws IOException{
        this.Userid = Userid;
        this.feedController = feedController;

        profileVBox.prefWidthProperty().bind(feedController.postsScrollpane.widthProperty().subtract(10));

        this.username.setText(FBsystem.users.get(Userid).getName());

        if(Userid == FBsystem.CurUser.getId()){
            friendButton.setVisible(false);
            restrictButton.setVisible(false);
            blockButton.setVisible(false);
        }
            

        if(FBsystem.CurUser.friends.contains(Userid))
            friendButton.setText("Remove Friend");
        else
            friendButton.setText("Add Friend");

        if(FBsystem.CurUser.restrictedUsers.get(Userid))
            restrictButton.setText("Unrestrict");
        else
            restrictButton.setText("Restrict");

        if(FBsystem.CurUser.blockedUsers.get(Userid))
            blockButton.setText("Unblock");
        else
            blockButton.setText("Block");

        user profileUser = FBsystem.users.get(Userid);
        profileUser.posts.setReversedIteration(true);
        for(Node<Integer> it = profileUser.posts.iteratorToStart(); it != null; it = profileUser.posts.nextValue()) {
            Post temp = FBsystem.posts.get(it.value());
            if(temp.getPrivacy() == '+' || temp.getPrivacy() == '#' && FBsystem.CurUser.friends.contains(Userid)
                || temp.getPrivacy() == '-' && !profileUser.restrictedUsers.get(FBsystem.CurUser.getId())) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/post.fxml"));
                VBox post = loader.load(); 
                PostController controller = loader.getController();
                controller.init(FBsystem.posts.get(it.value()), feedController);
                profileFeed.getChildren().add(post);
            }
        }
        profileUser.posts.setReversedIteration(false);
    }
    @FXML
    private void friendButtonPressed(){
        if(FBsystem.CurUser.friends.contains(Userid)){
            FBsystem.CurUser.removeFriend(Userid);
            friendButton.setText("Add Friend");
        }
        else{
             FBsystem.CurUser.addFriend(FBsystem.users.get(Userid));
             friendButton.setText("Remove Friend");
        }
    }
    @FXML
    private void restrictButtonPressed(){
         if(FBsystem.CurUser.restrictedUsers.get(Userid)){
            FBsystem.CurUser.unrestrictUser(Userid);
            restrictButton.setText("Restrict");
        }
        else{
             FBsystem.CurUser.restrictUser(Userid);
             restrictButton.setText("Unrestrict");
        }
    }
    @FXML
    private void blockButtonPressed(){
        if(FBsystem.CurUser.restrictedUsers.get(Userid)){
            FBsystem.CurUser.unblock(Userid);
            restrictButton.setText("Block");
        }
        else{
             FBsystem.CurUser.block(Userid);
             restrictButton.setText("Unblock");
        }
    }
}
