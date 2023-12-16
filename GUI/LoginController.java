package GUI;

import java.io.IOException;

import System.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Text errorsText;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        String username = emailField.getText();
        String password = passwordField.getText();
        int userId = FBsystem.Login(username, password);
        if (userId < 0) {
            errorsText.setText("Wrong email or password");
        } else {
            FBsystem.CurUser = FBsystem.users.get(userId);
            
            GoToFeed(event);
        }
    }

    @FXML
    private void GoToRegister(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLs/Register.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void GoToFeed(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/feed.fxml"));
        Parent root = loader.load();
        FeedController Controller = loader.getController();
        Controller.init();
        Controller.setContent();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);


        stage.setScene(scene);
        stage.show();
    }
}