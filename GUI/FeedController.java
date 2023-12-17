package GUI;
import java.io.IOException;

import CustomStructures.Node;
import javafx.beans.binding.DoubleBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
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

        for(Long convo : FBsystem.CurUser.userConvs) {
            FBsystem.conversations.get(convo);
            FXMLLoader ConvoLoader = new FXMLLoader(getClass().getResource("FXMLs/chat.fxml"));
            Hyperlink addConversation = ConvoLoader.load();
            
            ChatController convoController = ConvoLoader.getController();

            convoController.init(FBsystem.conversations.get(convo), this);
            messagesVBox.getChildren().add(addConversation);
        }

    }

    @FXML
    private void logout(ActionEvent event) throws IOException{
        FBsystem.CurUser = null;
        Parent root = FXMLLoader.load(getClass().getResource("FXMLs/Login.fxml"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
        FBsystem.CurUser.feed.setReversedIteration(true);
        for (Node<Integer> it = FBsystem.CurUser.feed.iteratorToStart(); it != null; it = FBsystem.CurUser.feed
        .nextValue()) {
            addPost(FBsystem.posts.get(it.value()));
        }
        FBsystem.CurUser.feed.setReversedIteration(false);
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

    public void openChat(Conversation convo) throws IOException {
        postsScrollpane.prefWidthProperty().bind(windowPane.widthProperty().subtract(searchVBox.widthProperty()));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/messages.fxml"));
        messages = loader.load();
        messagesController = loader.getController();

        messagesController.init(convo, this);

        windowPane.setRight(messages);

    }

    public void closeChat() throws IOException {
        messages = null;
        messagesController = null;
        windowPane.setRight(conversationsPane);
    }
}