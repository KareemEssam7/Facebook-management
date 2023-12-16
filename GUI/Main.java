package GUI;

import System.FileHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Optional;

public class Main extends Application {

        private void handleCloseRequest(WindowEvent event) {
        // Display a confirmation alert
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Do you really want to close the application?");
        alert.setContentText("Press yes to save and exit");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try{
                FileHandler.save();
            }
            catch(IOException e) {
                System.out.println("Error Saving");
            }
            System.exit(0);
        } else {
            event.consume();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/Login.fxml"));
        Parent root = loader.load();
        try{
            FileHandler.load();
        }
        catch(IOException e) {
            System.out.println("Error Loding");
        }
        primaryStage.setOnCloseRequest(this::handleCloseRequest);
        
        // FeedController feed = loader.getController();

        // feed.init();
        

        // for(int i = 1 ; i <= 100; i++)
        //     feed.addChat("KAREEMESSAM" + i);
        
        
        primaryStage.setTitle("FakeBook");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}