package System;

import java.util.*;

// Post , Comment,  Reply
abstract class Action {
    // id of the action
    protected final int id;
    protected int linesCount;
    // id of the poster / commenter / replier
    protected final int userId;
    public Set<Integer> ReactorsID = new HashSet<Integer>();
    public String content;
    public int pageId = -1;

    protected Action(int id, String content, int userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        linesCount = FBsystem.charCount('\n', content) + 1;
    }

    public abstract void showContent();

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public int getLinesCount() {
        return linesCount;
    }

    public int getUserId() {
        return userId;
    }

}
