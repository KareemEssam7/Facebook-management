package GUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;

public class ChatController {

    @FXML
    Hyperlink chat;

    GUI.FeedController container;

    @FXML
    public void chatPressed() throws IOException{
        container.openChat(chat.getText());
    }

    public void setContainer(GUI.FeedController container){
        this.container = container;
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
