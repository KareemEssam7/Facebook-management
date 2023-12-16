package GUI;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import System.*;
import CustomStructures.*;

public class PostResultsController {
    @FXML
    VBox postsResultsVbox;
    FeedController feedController;

    public void init( FeedController feedController) throws IOException{

        this.feedController = feedController;

        postsResultsVbox.prefWidthProperty().bind(feedController.postsScrollpane.widthProperty().subtract(10));


        //add profile posts here

    }
    
}
