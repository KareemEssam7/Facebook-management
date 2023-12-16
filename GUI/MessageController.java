package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import System.FBsystem;

public class MessageController {
    @FXML
    Label username;
    @FXML
    Text body;

    public void setContent(Long MsgID, Long convId , String body){

        String userName = FBsystem.users.get(FBsystem.conversations.get(convId).messages.get(MsgID).getSenderID()).getName();

        if(userName != null)
            this.username.setText(userName);
        
        this.body.setText(body);
    }
}