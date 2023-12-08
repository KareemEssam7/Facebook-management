import java.io.*;
import java.util.*;

import CustomStructures.*;

public class Post extends Action {
    static int postID = 0;
    int user_id;
    char privacy;
    Set<Integer> TaggedId = new HashSet<Integer>();
    public JoinedList<comment> Comments = new JoinedList<comment>();
    Post(String msg, char priv) {
        this.privacy = priv;
        postID++;
        this.id = postID;
        this.content = msg;
    }

}
