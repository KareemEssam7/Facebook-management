package System;

import java.io.*;
import java.util.*;

import CustomStructures.*;

public class Page {
    private String contactEmail;
    private String name;
    public JoinedList<Integer> posts = new JoinedList<Integer>();
    public Set<Long> pageConvs = new HashSet<Long>();
    public static int pageCount = 0;
    private final int pageID;
    public BitSet blockedUsers = new BitSet();
    public Set<Long> followers;

    Page(String name, String contactEmail) {
        this.name = name;
        this.contactEmail = contactEmail;
        pageID = ++pageCount;
    }

    public void block(int userID) {
        JoinedList<Integer> userPostList = FBsystem.users.get(userID).feed;
        Node<Integer> p = userPostList.iteratorToStart();
        while (p != null) {
            Post cur = FBsystem.posts.get(p.value());
            if (cur == null || cur.getId() == pageID)
                p = userPostList.deleteNode(p);
            else
                p = userPostList.nextValue();

        }
        blockedUsers.set(userID, true);
    }

    public void unblock(int userID) {
        blockedUsers.set(userID, false);
    }

}
