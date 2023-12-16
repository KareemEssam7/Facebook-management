package GUI;

import java.io.IOException;
import System.*;
import System.FBsystem;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.text.Text;
import CustomStructures.*;

public class SearchResults {
    @FXML
    Hyperlink user;
    
    FeedController feedController;

    @FXML
    private void usernameHover() {
        user.setStyle("-fx-text-fill: #0077b6; -fx-underline: false;");
    }

    @FXML
    private void usernameDefault() {
        user.setStyle("-fx-text-fill: #0d1b2a; -fx-underline: false;");
    }
}
