package GUI;
import java.io.IOException;
import java.util.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import System.*;
class SearchController
{
    @FXML
    Button searchButton;
    @FXML
    TextField SearchQuery;
    
    private void searchAction(ActionEvent event ,  VBox postsResults,FeedController feed) throws IOException
    {
            String Query=SearchQuery.getText();
            Vector<Integer>SearchResults=Search.search(Query);
            
            // normal search results
            if(Search.status==1)
            {
                    
            }
            else if(Search.status==2)
            {
                // common friends results
            }
            else if(Search.status == 3)
            {   
                // common posts results
                for(Integer i : SearchResults)
                {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLs/post.fxml"));
                    VBox post = loader.load();
                    PostController controller = loader.getController();
                    controller.init(FBsystem.posts.get(i),feed);
                    postsResults.getChildren().add(post);
                }
            }
    }
}