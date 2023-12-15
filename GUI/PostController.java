package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class PostController {

    @FXML
    VBox panel;
    @FXML
    Hyperlink username;
    @FXML
    Text body;
    @FXML
    Button likeButton;
    @FXML
    Button commentButton;

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