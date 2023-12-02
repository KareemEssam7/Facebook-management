import java.io.*;
import java.util.*;
import CustomStructures.*;

public class FBsystem {
    // id and user
    public static HashMap<Integer, user> users = new HashMap<Integer, user>();
    public static HashMap<Long, Conversation> conversations = new HashMap<Long, Conversation>();
    public static JoinedList<Post> posts = new JoinedList<Post>();
    public static JoinedList<Reply> Replies = new JoinedList<Reply>();
    public static JoinedList<comment> Comments = new JoinedList<comment>();
}
