package GUI;

import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;

public class CreatePostController {

    @FXML
    VBox panel;
    @FXML
    Label title;
    @FXML
    TextArea inputField;
    @FXML
    Button postButton;

    @FXML
    public void postPressed(){
        
        inputField.setText("");
    }
}
