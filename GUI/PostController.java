package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class PostController {

    @FXML
    private VBox panel;
    @FXML
    private Hyperlink username;
    @FXML
    private Text body;
    @FXML
    private Button likeButton;
    @FXML
    private Button commentButton;
    @FXML
    private ToggleGroup privacySettings;

    public void setContent(String usernameString, String bodyString){
        username.setText(usernameString);
        body.setText(bodyString);
    }

    @FXML
    public void likePressed(){

    }

    @FXML
    public void commentPressed(){

    }

    @FXML
    public void usernamePressed(){
        
    }

    @FXML
    private void usernameHover() {
        username.setStyle("-fx-text-fill: #0077b6; -fx-underline: false;");
    }

    @FXML
    private void usernameDefault() {
        username.setStyle("-fx-text-fill: #0d1b2a; -fx-underline: false;");
    }
}
