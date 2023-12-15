package GUI;

import java.io.IOException;

import System.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        String username = emailField.getText();
        String password = passwordField.getText();
        if (FBsystem.Login(username, password) < 0) {
            System.out.println("doesnt work mosalah");
        } else {
            GoToFeed(event);
        }
        System.out.println("Login");
        System.out.println("Username: " + username);
        System.out.println("Password: " + password);
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

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);

        Controller.init();

        stage.setScene(scene);
        stage.show();
    }
}