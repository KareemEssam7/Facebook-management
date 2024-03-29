package System;

import java.io.*;
import java.util.*;
import CustomStructures.*;

public class user {
    private String email;
    private String name;
    private String password;
    private char gender;
    private String Birthdate;
    public JoinedList<Integer> posts = new JoinedList<Integer>();
    public Set<Long> userConvs = new HashSet<Long>();
    public static int userCount = 0;
    private final int id;
    public Set<Integer> friends = new HashSet<Integer>();
    public BitSet restrictedUsers = new BitSet();
    public BitSet blockedUsers = new BitSet();
    public JoinedList<Integer> feed = new JoinedList<Integer>();
    public Set<Long> Pages;
    public Set<Long> Groups;
    public Set<Long> pagesManaged;

    user(String email, String name, String password, char gender, String Birthdate) {
        userCount++;
        this.id = userCount;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.Birthdate = Birthdate;
        Trie.insert(name, this.id);
        FBsystem.users.put(this.id, this);
        FBsystem.accounts.put(email, this.id);
    }

    user(String email, String name, String password, char gender, String Birthdate, int userId) {
        userCount++;
        this.id = userId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.Birthdate = Birthdate;
        Trie.insert(name, this.id);
        FBsystem.users.put(this.id, this);
        FBsystem.accounts.put(email, this.id);
    }

    public void addFriend(user friend, Boolean check) {
        this.friends.add(friend.id);
    }

    public void addFriend(user friend) {
        this.friends.add(friend.id);
        FBsystem.users.get(friend.id).addFriend(this, false);
    }

    public void removeFriend(int userID, Boolean check) {
        friends.remove(userID);
    }

    public void removeFriend(int userID) {
        friends.remove(userID);
        FBsystem.users.get(userID).removeFriend(this.id, false);
    }

    public void like(comment com) {
        com.ReactorsID.add(this.id);
    }

    public void like(Reply rep) {
        rep.ReactorsID.add(this.id);
    }

    public void like(Post post) {
        post.ReactorsID.add(this.id);
    }

    public void addReply(comment com, String reply) {
        Reply newReply = new Reply(reply, id);
        com.Replies.pushBack(newReply);
    }

    public void addComment(Post post, String com) {
        comment newComment = new comment(com, id);
        post.Comments.pushBack(newComment);
    }

    public void CreatePost(String content, char privacy, Vector<Integer> TaggedId) {
        // ! [+ Public (for all users)], [# default (for all friends)] ,[- Private(for
        // non-restricted friends)].
        Post newPost = new Post(content, privacy, id, TaggedId);
        FBsystem.posts.put(newPost.id, newPost);
        posts.pushBack(newPost.id);
        // FeedController.addPost();
        // get gud bro
        for (Integer Tagged : TaggedId) {
            FBsystem.users.get(Tagged).posts.pushBack(newPost.id);
        }

        if (newPost.getPrivacy() == '+') {
            for (Map.Entry<Integer, user> userData : FBsystem.users.entrySet())
                FBsystem.users.get(userData.getKey()).feed.pushBack(newPost.id);
        } else if (newPost.getPrivacy() == '#') {
            for (Integer curr : friends)
                FBsystem.users.get(curr).feed.pushBack(newPost.id);
        } else {

            for (Integer curr : friends) {

                Boolean isRestricted = restrictedUsers.get(curr);

                if (isRestricted)
                    continue;

                FBsystem.users.get(curr).feed.pushBack(newPost.id);
            }
        }

    }

    public void deletePost(Node<Integer> post) {
        FBsystem.posts.remove(post.value());
        posts.deleteNode(post);
    }

    public void MakeConversation(Vector<Integer> usersID, String Name) {
        usersID.add(this.id);
        Conversation newconv = new Conversation(usersID, Name);
        for (int i = 0; i < usersID.size(); i++) {
            FBsystem.users.get(usersID.get(i)).userConvs.add(newconv.getUniqueID());
        }
        FBsystem.conversations.put(newconv.getUniqueID(), newconv);
    }

    public void SendMessage(String content, Long conversationId) {
        message msg = new message(content, this.id, conversationId);
        FBsystem.conversations.get(conversationId).messages.put(msg.getMessageID(), msg);
    }

    public void LeaveConversation(Long convID) {
        this.userConvs.remove(convID);
        FBsystem.conversations.get(convID).participantsID.remove(this.id);
        if (FBsystem.conversations.get(convID).participantsID.size() == 0) {
            FBsystem.conversations.remove(convID);
        }
    }

    // hidden easteregg
    public void restrictUser(int userID) {
        restrictedUsers.set(userID);
        JoinedList<Integer> userPostList = FBsystem.users.get(userID).feed;
        Node<Integer> p = userPostList.iteratorToStart();
        while (p != null) {
            Post cur = FBsystem.posts.get(p.value());
            if (cur == null || (cur.getPrivacy() == '-' && cur.userId == this.id))
                p = userPostList.deleteNode(p);
            else
                p = userPostList.nextValue();

        }
    }

    public void unrestrictUser(int userID) {
        restrictedUsers.clear(userID);
    }

    public void block(int userID) {
        if (FBsystem.users.get(this.id).friends.contains(userID)) {
            removeFriend(userID);
        }
        JoinedList<Integer> userPostList = FBsystem.users.get(userID).feed;
        Node<Integer> p = userPostList.iteratorToStart();
        while (p != null) {
            Post cur = FBsystem.posts.get(p.value());
            if (cur == null || cur.userId == this.id)
                p = userPostList.deleteNode(p);
            else
                p = userPostList.nextValue();

        }
        blockedUsers.set(userID, true);
    }

    public void unblock(int userID) {
        blockedUsers.set(userID, false);
    }

    public int getId() {
        // getter for id
        return id;
    }

    public String getName() {
        return this.name;
    }

    // compares a given password with the user's password
    boolean comparePassword(String toCompare) {
        return toCompare.equals(password);
    }

    // saves password to file
    void savePassword(FileWriter writer) throws IOException {
        writer.write(password);
    }

    public Vector<Integer> common_Friends(int ID) {

        Vector<Integer> common_Friends_ID = new Vector<Integer>();

        for (Integer id : friends) {
            user cur = FBsystem.users.get(id);
            if (cur.friends.contains(ID)) {
                common_Friends_ID.add(id);
            }
        }

        return common_Friends_ID;
    }

    public String getBirthdate() {
        return Birthdate;
    }

    public String getEmail() {
        return email;
    }

    public char getGender() {
        return gender;
    }

}