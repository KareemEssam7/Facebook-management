import java.io.*;
import java.util.*;

public class user {
    String email;
    String name;
    private String password;
    char gender;
    String Birthdate;
    Set<Integer> posts = new HashSet<Integer>();
    Set<Long> userConvs = new HashSet<Long>();
    static int userCount = 0;
    private final int id;
    Set<Integer> friends = new HashSet<Integer>();
    BitSet restrictedUsers = new BitSet();
    BitSet blockedUsers = new BitSet();
    Set<Integer> feedId = new HashSet<Integer>();

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
        Reply test = new Reply(reply);
        FBsystem.Replies.pushBack(test);
        com.ReplyId.add(test.id);
    }

    void addComment(Post post, String com) {
        comment test = new comment(com);
        FBsystem.Comments.pushBack(test);
        post.CommentId.add(test.id);
    }

    void CreatePost(String content, char privacy) {
        Post test = new Post(content, privacy);
        FBsystem.posts.pushBack(test);
        this.posts.add(test.id);
        for (Integer Cur : friends) {
            Boolean Restricted = restrictedUsers.get(Cur);
            if (Restricted && privacy == '-')
                continue;
            FBsystem.users.get(Cur).feedId.add(test.id);
        }
        for (Integer tagged : test.TaggedId) {
            FBsystem.users.get(tagged).feedId.add(test.id);
        }
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

    void restrictUser(int userID) {
        restrictedUsers.set(userID);
        for (Integer myPost : posts) {
            // add condition to check if post is restricted then
            if (FBsystem.users.get(userID).feedId.contains(myPost)) {
                FBsystem.users.get(userID).feedId.remove(myPost);
            }
        }
    }

    void unrestrictUser(int userID) {
        restrictedUsers.clear(userID);
    }

    void block(int userID) {
        if (FBsystem.users.get(this.id).friends.contains(userID)) {
            removeFriend(userID);
        }
        for (Integer myPost : posts) {
            if (FBsystem.users.get(userID).feedId.contains(myPost)) {
                FBsystem.users.get(userID).feedId.remove(myPost);
            }
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

}
