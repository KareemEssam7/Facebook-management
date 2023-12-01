import java.io.*;
import java.util.*;

public class Post extends Action {
    static int postID = 0;

    Set<Integer> TaggedId = new HashSet<Integer>();
    Set<Integer> CommentId = new HashSet<Integer>();

    Post(String msg) {
        postID++;
        this.id = postID;
        this.content = msg;
    }

}
