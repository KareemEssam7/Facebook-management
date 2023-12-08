import java.io.*;
import java.util.*;

import CustomStructures.*;

public class comment extends Action {
    static int id2 = 0;
    public JoinedList<Reply> Replies = new JoinedList<Reply>();
    comment(String msg) {
        id2++;
        this.id = id2;
        this.content = msg;
    }
}
