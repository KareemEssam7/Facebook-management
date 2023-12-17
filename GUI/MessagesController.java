package GUI;

import java.io.IOException;

import System.Conversation;
import System.FBsystem;
import System.user;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import CustomStructures.*;

public class MessagesController {
    @FXML
    BorderPane panel;
    @FXML
    HBox topBar;
    @FXML
    Button backButton;
    @FXML
    Label username;
    @FXML
    ScrollPane messageScrollPane;
    @FXML
    VBox messageVBox;
    @FXML
    HBox bottomBar;
    @FXML
    TextArea inputField;
    @FXML
    Button sendButton;

    Conversation convo;
    FeedController feedController;

    public void init(Conversation convo, FeedController feedController) throws IOException{
        this.feedController = feedController;
        this.convo = convo;

        panel.prefHeightProperty().bind(feedController.searchScrollPane.heightProperty().divide(1.002));
        messageScrollPane.prefHeightProperty().bind(feedController.searchScrollPane.heightProperty().subtract(topBar.heightProperty()).subtract(bottomBar.heightProperty()));
        messageVBox.prefWidthProperty().bind(panel.widthProperty());

        username.setText(convo.convName);
        for(Node<Long> it = convo.messagesId.iteratorToStart(); it != null; it = convo.messagesId.nextValue()) {
            addMessage(it.value());
            // FBsystem.conversations.get(conv).messages.get(it.value()).getMessageID(),FBsystem.conversations.get(conv).messages.get(it.value()).content
        }
    }

    public void setUsername(String username){
        this.username.setText(username);
    }

    @FXML
    public void close() throws IOException{
        feedController.closeChat();
    }

    public void addMessage(Long msgId) throws IOException{
        FXMLLoader loader;
        if(convo.messages.get(msgId).getSenderID() != FBsystem.CurUser.getId()){
            loader = new FXMLLoader(getClass().getResource("FXMLs/recievedMessage.fxml"));
        }
        else
            loader = new FXMLLoader(getClass().getResource("FXMLs/sentMessage.fxml")); // not made yet
        HBox createMessage = loader.load();
        MessageController controller = loader.getController();
        controller.setContent(msgId, convo);
        messageVBox.getChildren().add(createMessage);
    }

    @FXML
    private void sendPressed() throws IOException{
        if(inputField.getText().length() > 0){
            FBsystem.CurUser.SendMessage(inputField.getText(), convo.getUniqueID());
            addMessage(convo.messagesId.getEndNode().value());
            inputField.setText("");
        }
    }

}
