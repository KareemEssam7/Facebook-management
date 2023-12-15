package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class ChatController {

    @FXML
    Hyperlink chat;

    @FXML
    public void chatPressed(){

    }

    public void setUsername(String username){
        chat.setText(username);
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
