package System;

import java.io.*;
import java.util.*;

// Post , Comment,  Reply
abstract class Action {
    // id of the action
    public final int id, linesCount;
    // id of the poster / commenter / replier
    public final int userId;
    Set<Integer> ReactorsID = new HashSet<Integer>();
    public String content;

    protected Action(int id, String content, int userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        linesCount = FBsystem.charCount('\n', content) + 1;
    }

    public String getContent() {
        return content;

    }
}
