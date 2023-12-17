package GUI;

import java.io.IOException;
import System.*;
import System.FBsystem;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import CustomStructures.*;

public class ChatController {
    @FXML
    Hyperlink chat;
    
    Conversation conv;
    FeedController feedController;

    @FXML
    public void chatPressed() throws IOException{
        feedController.openChat(conv);
    }

    public void init(Conversation conv, FeedController feedController){
        this.feedController = feedController;
        this.conv = conv;
        chat.setText(FBsystem.conversations.get(conv.getUniqueID()).convName);
    }
    
    @FXML
    private void usernameHover() {
        chat.setStyle("-fx-text-fill: #0077b6; -fx-underline: false;");
    }

    @FXML
    private void usernameDefault() {
        chat.setStyle("-fx-text-fill: #0d1b2a; -fx-underline: false;");
    }
}
