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
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import System.*;

public class FeedController {

    @FXML
    StackPane windowStack;
    @FXML
    BorderPane windowPane;
    @FXML
    HBox barHBox;
    @FXML
    TextField searchBar;
    @FXML
    Button searchButton;
    @FXML
    Button createConversationButton;
    @FXML
    Button logoutButton;
    @FXML
    Button homeButton;
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
    @FXML
    VBox postsResults;
    BorderPane messages = null;
    MessagesController messagesController = null;

    public void init() throws IOException {
        DoubleBinding itemsLength = searchBar.widthProperty().add(searchButton.widthProperty())
                .add(logoutButton.widthProperty()).add(homeButton.widthProperty());
        whiteSpacePane.prefWidthProperty().bind(windowPane.widthProperty().subtract(itemsLength).subtract(120));

        postsVBox.prefWidthProperty().bind(postsScrollpane.widthProperty().subtract(10));
        messagesVBox.prefWidthProperty().bind(messagesScrollPane.widthProperty().subtract(10));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/createPost.fxml"));
        VBox createPost = loader.load();
        // PostController controller = loader.getController();

        postsVBox.getChildren().add(createPost);
    }
    public void SetResults() throws IOException
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/PostsResults.fxml"));
        postsResults = loader.load();
        PostResultsController controller = loader.getController();
        controller.init(this);
        postsScrollpane.setContent(postsResults);
        postsScrollpane.setVvalue(postsScrollpane.getVmin());
    }
    public void setContent() throws IOException {
        System.out.println("meow");
        for (Node<Integer> it = FBsystem.CurUser.feed.iteratorToStart(); it != null; it = FBsystem.CurUser.feed
                .nextValue()) {
            addPost(FBsystem.posts.get(it.value()));
        }
    }

    public void goHome(){
        postsScrollpane.setContent(postsVBox);
        postsScrollpane.setVvalue(postsScrollpane.getVmin());
    }

    public void addPost(Post post_obj) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/post.fxml"));
        VBox post = loader.load();
        PostController controller = loader.getController();
        
        controller.init(post_obj, this);

        postsVBox.getChildren().add(post);
    }

    public void addChat(Conversation c) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/chat.fxml"));
        Hyperlink chat = loader.load();
        ChatController controller = loader.getController();

        controller.init(c, this);
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

        // addMessageToChat("Person #1", "test123");
        // addMessageToChat(null, "123test");
        // addMessageToChat(null, "test321\ntest123");
        // addMessageToChat("Person #2", "321tset");
    }

    public void addMessageToChat(Long msgID, Long convId, String body) throws IOException {
        messagesController.addMessage(msgID, convId, body);
        messagesController.messageScrollPane.setVvalue(messagesController.messageScrollPane.getVmax());
    }

    public void closeChat() throws IOException {
        messages = null;
        messagesController = null;
        windowPane.setRight(conversationsPane);
    }
}