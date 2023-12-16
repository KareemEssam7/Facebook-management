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
    
    FeedController feedController;

    @FXML
    public void chatPressed() throws IOException{
        feedController.openChat(chat.getText());
    }

    public void init(Conversation c, FeedController feedController){
        chat.setText(FBsystem.conversations.get(c.getUniqueID()).convName);
    }
    
    public void loadChats(FeedController feedController,int userId) throws IOException{
        
        for(Long i : FBsystem.users.get(userId).userConvs){

            feedController.addChat(FBsystem.conversations.get(i));
        }

    }

    public void loadMessages(FeedController feedController,Long conv,Integer userId) throws IOException{
        user curUser = FBsystem.users.get(userId);
        Conversation cur =FBsystem.conversations.get(conv);
        for(Node<Long> it =cur.messagesId.iteratorToStart(); it != null; it = cur.messagesId.nextValue()) {
            feedController.addMessageToChat(it.value(), conv,cur.messages.get(it.value()).content);
            // FBsystem.conversations.get(conv).messages.get(it.value()).getMessageID(),FBsystem.conversations.get(conv).messages.get(it.value()).content
        }
          
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
