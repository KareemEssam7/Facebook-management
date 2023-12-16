package GUI;

import System.FBsystem;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;

import java.util.StringJoiner;
import java.util.Vector;
import CustomStructures.Trie;
public class CreatePostController {

    @FXML
    VBox panel;
    @FXML
    Label title;
    @FXML
    TextArea inputField;
    @FXML
    Button postButton;
    @FXML
    private ToggleGroup privacySettings;

    private void fillTags(Vector<Integer> tags, String content) {
        boolean filling = false;
        StringBuilder joiner = new StringBuilder();
        for(int i = 0; i < content.length(); i++) {
            if(content.charAt(i) == '@') {
                filling = true;
                joiner = new StringBuilder();
                continue;
            }
            if(filling) {
                if(content.charAt(i) == ' ' || content.charAt(i) == '\n') {
                    filling = false;
                    String mentionedName = joiner.toString();
                    if(!FBsystem.validName(mentionedName))
                        continue;
                    int mentionedID = Trie.search_user(mentionedName);
                    if(mentionedID != -1)
                        tags.add(mentionedID);
                    continue;
                }
                joiner.append(Character.toString(content.charAt(i)));
            }
        }
    }

    @FXML
    public void postPressed(){

        char privacy;
        RadioButton selection = (RadioButton)privacySettings.getSelectedToggle();
        switch (selection.getText().charAt(0)) {
            case 'P':
                privacy = '+';
                break;
                
            case 'F':
                privacy = '#';
                break;
            default:
                privacy = '-';
                break;
        }
        String content = inputField.getText();
        Vector<Integer> taggedUsers = new Vector<Integer>();

        fillTags(taggedUsers, content);
        FBsystem.CurUser.CreatePost(content, privacy, taggedUsers);
        inputField.setText("");
    }
}
