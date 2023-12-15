package GUI;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import System.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.DatePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField ConfirmPasswordField;

    @FXML
    private TextField usernameField;

    // @FXML
    // private CheckBox GenderChoice;

    @FXML
    private ToggleGroup gender;

    @FXML
    private DatePicker Birthdate;

    @FXML
    private void registerButtonAction(ActionEvent event) {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = ConfirmPasswordField.getText();
        String genderString = gender.getSelectedToggle().toString();
        char Gender = genderString.charAt(0);
        String Date = Birthdate. getValue(). format(DateTimeFormatter. ofPattern("yyyy-MM-dd"));

        if(!password.equals(confirmPassword))
            System.out.println("Password doesn't match");
        else{
            int returnValue = FBsystem.Register(email, username, password, Gender, Date);
            if (returnValue == 1) {
                System.out.println("invalid email");
            } else if (returnValue == 2) {
                System.out.println("invalid Password");
            } else if (returnValue == 3) {
                System.out.println("invalid Name");
            } else
                System.out.println("Successful");
            }
            // System.out.println("Register");
            // System.out.println("Username: " + username);
            // System.out.println("Password: " + password);
    }

    @FXML
    private void GoToLogin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLs/Login.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
