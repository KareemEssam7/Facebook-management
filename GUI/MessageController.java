package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import System.Conversation;
import System.FBsystem;

public class MessageController {
    @FXML
    Label username;
    @FXML
    Text body;

    public void setContent(Long MsgID, Conversation convo){

        if(convo.messages.get(MsgID).getSenderID() != FBsystem.CurUser.getId())
            this.username.setText(FBsystem.users.get(convo.messages.get(MsgID).getSenderID()).getName());
        
        this.body.setText(convo.messages.get(MsgID).content);
    }
}