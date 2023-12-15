package GUI;

import java.io.IOException;

import javafx.beans.binding.DoubleBinding;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class FeedController {
    
    @FXML
    BorderPane windowPane;
    @FXML
    HBox barHBox;
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
    ScrollPane searchScrollPane;
    @FXML
    public VBox searchVBox;
    @FXML
    ScrollPane postsScrollpane;
    @FXML
    public VBox postsVBox;

    private VBox messages = null;

    public void init() throws IOException{
        DoubleBinding itemsLength = searchBar.widthProperty().add(searchButton.widthProperty()).add(logoutButton.widthProperty());
        whiteSpacePane.prefWidthProperty().bind(windowPane.widthProperty().subtract(itemsLength).subtract(120));
        
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
        
        controller.setContainer(this);
        controller.setUsername(username);
        messagesVBox.getChildren().add(chat);
    }

    public void openChat(String username) throws IOException{
        postsScrollpane.prefWidthProperty().bind(windowPane.widthProperty().subtract(searchVBox.widthProperty()));
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/messages.fxml"));
        messages = loader.load();
        MessagesController controller = loader.getController();

        controller.init(this);
        controller.setUsername(username);

        windowPane.setRight(messages);
    }

    public void closeChat() throws IOException{
        messages = null;
        windowPane.setRight(messagesScrollPane);
    }
}
