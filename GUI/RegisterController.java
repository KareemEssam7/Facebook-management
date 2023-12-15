package GUI;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

import System.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.scene.control.DatePicker;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private Text errorsText;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField ConfirmPasswordField;

    @FXML
    private TextField usernameField;

    @FXML
    private ToggleGroup gender;

    @FXML
    private DatePicker Birthdate;

    @FXML
    private void registerButtonAction(ActionEvent event) {
        try{
            String username = usernameField.getText();
            String email = emailField.getText();
            String password = passwordField.getText();
            String confirmPassword = ConfirmPasswordField.getText();
            String genderString = gender.getSelectedToggle().toString();
            char Gender = genderString.charAt(0);
            String Date = Birthdate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            if(!password.equals(confirmPassword))
                errorsText.setText("Password doesn't match");
            else{
                int returnValue = FBsystem.Register(email, username, password, Gender, Date);
                if (returnValue == 1) {
                    errorsText.setText("Invalid email format");
                } else if (returnValue == 2) {
                    errorsText.setText("Password too weak");
                } else if (returnValue == 3) {
                    errorsText.setText("Invalid Name");
                } else
                    errorsText.setText("Successful");
                }
        }
        catch(Exception e){
            errorsText.setText("Please fill all fields");
        }
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
