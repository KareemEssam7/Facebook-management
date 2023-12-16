package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/feed.fxml"));
        Parent root = loader.load();
        FeedController feed = loader.getController();

        feed.init();

        feed.addPost("KareemEssam", "Meow.");
        feed.addPost("Tuber", "Mew.");
        feed.addPost("KareemEssam", "Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2Meow2.");
        feed.addPost("KareemEssam", "Meow3.");
        feed.addPost("KareemEssam", "Meow4.");

        for(int i = 1 ; i <= 100; i++)
            feed.addChat("KAREEMESSAM" + i);
        
        
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}