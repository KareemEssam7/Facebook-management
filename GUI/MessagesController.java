package GUI;

import java.io.IOException;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
    ScrollPane messagScrollPane;
    @FXML
    VBox messagVBox;
    @FXML
    HBox bottomBar;
    @FXML
    TextField inputField;
    @FXML
    Button sendButton;

    GUI.FeedController container;

    public void init(GUI.FeedController container) throws IOException{
        this.container = container;
        panel.prefHeightProperty().bind(container.searchScrollPane.heightProperty().divide(1.002));
        messagScrollPane.prefHeightProperty().bind(container.searchScrollPane.heightProperty().subtract(topBar.heightProperty()).subtract(bottomBar.heightProperty()));
        messagVBox.prefWidthProperty().bind(panel.widthProperty());
        topBar.prefWidthProperty().bind(panel.widthProperty());
        bottomBar.prefWidthProperty().bind(panel.widthProperty());
    }

    public void setUsername(String username){
        this.username.setText(username);
    }

    @FXML
    public void close() throws IOException{
        container.closeChat();
    }

}
