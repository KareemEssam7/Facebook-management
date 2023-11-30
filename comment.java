import java.io.*;
import java.util.*;

public class comment extends Action {
    static int id2 = 0;

    comment(String msg) {
        id2++;
        this.id = id2;
        this.content = msg;
    }

    Set<Integer> ReplyId = new HashSet<Integer>();
}
