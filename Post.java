import java.io.*;
import java.util.*;

public class Post extends Action {
    static int id2 = 0;

    Post(String msg) {
        id2++;
        this.id = id2;
        this.content = msg;
    }

    Set<Integer> TaggedId = new HashSet<Integer>();
    Set<Integer> CommentId = new HashSet<Integer>();
}
