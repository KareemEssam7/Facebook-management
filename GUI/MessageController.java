package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class MessageController {
    @FXML
    Label username;
    @FXML
    Text body;

    public void setContent(String username, String body){
        if(username != null)
            this.username.setText(username);
        this.body.setText(body);
    }
}