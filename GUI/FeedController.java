package GUI;

import java.io.IOException;

import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class FeedController {
    
    @FXML
    VBox windowVBox;
    @FXML
    HBox barHBox;
    @FXML
    HBox feedHBox;
    @FXML
    TextField searchBar;
    @FXML
    Button searchButton;
    @FXML
    Button logoutButton;
    @FXML
    Pane whiteSpacePane;
    @FXML
    ScrollPane messagesScrollPane;
    @FXML
    public VBox messagesVBox;
    @FXML
    ScrollPane postsScrollpane;
    @FXML
    public VBox postsVBox;


    public void initFeed() throws IOException{
        feedHBox.prefWidthProperty().bind(windowVBox.widthProperty());
        feedHBox.prefHeightProperty().bind(windowVBox.heightProperty().subtract(barHBox.heightProperty()));
        DoubleBinding itemsLength = searchBar.widthProperty().add(searchButton.widthProperty()).add(logoutButton.widthProperty());
        whiteSpacePane.prefWidthProperty().bind(windowVBox.widthProperty().subtract(itemsLength).subtract(120));

        postsScrollpane.prefWidthProperty().bind(windowVBox.widthProperty().subtract(messagesScrollPane.widthProperty()));
        postsScrollpane.prefHeightProperty().bind(feedHBox.heightProperty());
        messagesScrollPane.prefHeightProperty().bind(feedHBox.heightProperty());
        
        postsVBox.prefWidthProperty().bind(postsScrollpane.widthProperty().subtract(10));
        messagesVBox.prefWidthProperty().bind(messagesScrollPane.widthProperty().subtract(10));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/createPost.fxml"));
        VBox createPost = loader.load();
        //PostController controller = loader.getController();
        postsVBox.getChildren().add(createPost);
    }
    
    public void addPost(String username, String body) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/post.fxml"));
        VBox post = loader.load();
        PostController controller = loader.getController();
        
        controller.setContent(username, body);
        
        postsVBox.getChildren().add(post);
    }

    public void addChat(String username) throws IOException{
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/chat.fxml"));
        Hyperlink chat = loader.load();
        ChatController controller = loader.getController();
        
        controller.setUsername(username);
        messagesVBox.getChildren().add(chat);
    }

}
