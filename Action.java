import java.io.*;
import java.util.*;

// Post , Comment,  Reply
abstract class Action {
    // id of the action
    public int id;
    // id of the poster / commenter / replier
    int userId;
    Set<Integer> ReactorsID = new HashSet<Integer>();
    String content;
}
