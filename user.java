import java.io.*;
import java.util.*;
import CustomStructures.*;

public class user {
    String email;
    String name;
    private String password;
    char gender;
    String Birthdate;
    public JoinedList<Integer> posts = new JoinedList<Integer>();
    Set<Long> userConvs = new HashSet<Long>();
    static int userCount = 0;
    private final int id;
    Set<Integer> friends = new HashSet<Integer>();
    BitSet restrictedUsers = new BitSet();
    BitSet blockedUsers = new BitSet();
    public JoinedList<Integer> feed = new JoinedList<Integer>();

    user(String email, String name, String password, char gender, String Birthdate) {
        userCount++;
        this.id = userCount;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.Birthdate = Birthdate;
        FBsystem.users.put(this.id, this);
    }

    public void addFriend(user friend, Boolean check) {
        this.friends.add(friend.id);
    }

    public void addFriend(user friend) {
        this.friends.add(friend.id);
        FBsystem.users.get(friend.id).addFriend(this, false);
    }

    void removeFriend(int userID, Boolean check) {
        friends.remove(userID);
    }

    void removeFriend(int userID) {
        friends.remove(userID);
        FBsystem.users.get(userID).removeFriend(this.id, false);
    }

    void like(comment com) {
        com.ReactorsID.add(this.id);
    }

    void like(Reply rep) {
        rep.ReactorsID.add(this.id);
    }

    void like(Post post) {
        post.ReactorsID.add(this.id);
    }

    void addReply(comment com, String reply) {
        Reply newReply = new Reply(reply, id);
        com.Replies.pushBack(newReply);
    }

    void addComment(Post post, String com) {
        comment newComment = new comment(com,id);
       post.Comments.pushBack(newComment);
    }

    void CreatePost(String content, char privacy, Vector<Integer> TaggedId) {
        Post newPost = new Post(content, privacy, id,TaggedId);
        FBsystem.posts.put(newPost.id, newPost);
        posts.pushBack(newPost.id);
        for (Integer Cur : friends) {
            Boolean Restricted = restrictedUsers.get(Cur);
            if (Restricted && privacy == '-')
                continue;
            FBsystem.users.get(Cur).feed.pushBack(newPost.id);
        }
        for (Integer tagged : newPost.TaggedId) {
            FBsystem.users.get(tagged).feed.pushBack(newPost.id);
        }
    }
    void deletePost(Node<Integer> post) {
          FBsystem.posts.remove(post.value());
          posts.deleteNode(post);
    }

    void MakeConversation(Vector<Integer> usersID) {
        Conversation newconv = new Conversation(usersID);
        for (int i = 0; i < usersID.size(); i++) {
            FBsystem.users.get(usersID.get(i)).userConvs.add(newconv.uniqueID);
        }
        FBsystem.conversations.put(newconv.uniqueID, newconv);
    }

    void SendMessage(String content, Long conversationId) {
        message msg = new message(content, this.id, conversationId);
        FBsystem.conversations.get(conversationId).messages.put(msg.messageID, msg);
    }

    void LeaveConversation(Long convID) {
        this.userConvs.remove(convID);
        FBsystem.conversations.get(convID).participantsID.remove(this.id);
        if (FBsystem.conversations.get(convID).participantsID.size() == 0) {
            FBsystem.conversations.remove(convID);
        }
    }
    // hidden easteregg
    void restrictUser(int userID) {
        restrictedUsers.set(userID);
        JoinedList<Integer> userPostList = FBsystem.users.get(userID).feed;
        Node<Integer> p = userPostList.iteratorToStart();
        while (p != null) {
            Post cur=FBsystem.posts.get(p.value());
            if( cur==null || (cur.privacy=='-' && cur.userId == this.id) )
                p = userPostList.deleteNode(p);
            else
                p = userPostList.nextValue();
            
        }
    }

    void unrestrictUser(int userID) {
        restrictedUsers.clear(userID);
    }

    void block(int userID) {
        if (FBsystem.users.get(this.id).friends.contains(userID)) {
            removeFriend(userID);
        }
          JoinedList<Integer> userPostList = FBsystem.users.get(userID).feed;
        Node<Integer> p = userPostList.iteratorToStart();
        while (p != null) {
            Post cur=FBsystem.posts.get(p.value());
            if( cur==null || cur.userId == this.id )
                p = userPostList.deleteNode(p);
            else
                p = userPostList.nextValue();
            
        }
        blockedUsers.set(userID, true);
    }

    void unblock(int userID) {
        blockedUsers.set(userID, false);
    }

    public int getId() {
        // getter for id
        return id;
    }

    // compares a given password with the user's password
    boolean comparePassword(String toCompare) {
        return toCompare.equals(password);
    }
    
    //saves password to file
    public void savePassword(FileWriter writer) throws IOException {
        writer.write(password);
    }

}
