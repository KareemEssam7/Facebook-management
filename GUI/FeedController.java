package GUI;

import java.io.IOException;

import CustomStructures.Node;
import System.FBsystem;
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
    VBox messagesVBox;
    @FXML
    ScrollPane searchScrollPane;
    @FXML
    VBox searchVBox;
    @FXML
    BorderPane conversationsPane;
    @FXML
    ScrollPane postsScrollpane;
    @FXML
    VBox postsVBox;

    BorderPane messages = null;
    MessagesController messagesController = null;

    public void init() throws IOException {
        DoubleBinding itemsLength = searchBar.widthProperty().add(searchButton.widthProperty())
                .add(logoutButton.widthProperty());
        whiteSpacePane.prefWidthProperty().bind(windowPane.widthProperty().subtract(itemsLength).subtract(120));

        postsVBox.prefWidthProperty().bind(postsScrollpane.widthProperty().subtract(10));
        messagesVBox.prefWidthProperty().bind(messagesScrollPane.widthProperty().subtract(10));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/createPost.fxml"));
        VBox createPost = loader.load();
        // PostController controller = loader.getController();

        postsVBox.getChildren().add(createPost);
    }

    public void setContent(String usernameString, String bodyString) throws IOException {
        for (Node<Integer> it = FBsystem.CurUser.feed.iteratorToStart(); it != null; it = FBsystem.CurUser.feed
                .nextValue()) {
            int PosterId = FBsystem.posts.get(it.value()).userId;
            String PosterName = FBsystem.users.get(PosterId).getName();
            String PostContent = FBsystem.posts.get(it.value()).getContent();
            addPost(PosterName, PostContent);
        }
    }

    public void addPost(String username, String body) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/post.fxml"));
        VBox post = loader.load();
        PostController controller = loader.getController();

        controller.setContent(username, body);

        postsVBox.getChildren().add(post);
    }

    public void addChat(String username) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/chat.fxml"));
        Hyperlink chat = loader.load();
        ChatController controller = loader.getController();

        controller.setContainer(this);
        controller.setUsername(username);
        messagesVBox.getChildren().add(chat);
    }

    public void openChat(String username) throws IOException {
        postsScrollpane.prefWidthProperty().bind(windowPane.widthProperty().subtract(searchVBox.widthProperty()));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/messages.fxml"));
        messages = loader.load();
        messagesController = loader.getController();

        messagesController.init(this);
        messagesController.setUsername(username);

        windowPane.setRight(messages);

        addMessageToChat("Person #1", "test123");
        addMessageToChat(null, "123test");
        addMessageToChat(null, "test321\ntest123");
        addMessageToChat("Person #2", "321tset");
    }

    public void addMessageToChat(String username, String body) throws IOException {
        messagesController.addMessage(username, body);
        messagesController.messageScrollPane.setVvalue(messagesController.messageScrollPane.getVmax());
    }

    public void closeChat() throws IOException {
        messages = null;
        messagesController = null;
        windowPane.setRight(conversationsPane);
    }
}