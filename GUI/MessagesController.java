package GUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;

public class MessagesController {
    @FXML
    VBox panel;
    @FXML
    HBox topBar;
    @FXML
    Button backButton;
    @FXML
    Label username;
    @FXML
    ScrollPane messageScrollPane;
    @FXML
    VBox messageVBox;
    @FXML
    HBox bottomBar;
    @FXML
    TextArea inputField;
    @FXML
    Button sendButton;

    GUI.FeedController container;

    public void init(GUI.FeedController container) throws IOException{
        this.container = container;
        panel.prefHeightProperty().bind(container.searchScrollPane.heightProperty().divide(1.002));
        messageScrollPane.prefHeightProperty().bind(container.searchScrollPane.heightProperty().subtract(topBar.heightProperty()).subtract(bottomBar.heightProperty()));
        messageVBox.prefWidthProperty().bind(panel.widthProperty());
    }

    public void setUsername(String username){
        this.username.setText(username);
    }

    @FXML
    public void close() throws IOException{
        container.closeChat();
    }

}
