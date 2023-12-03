import java.io.*;
import java.util.*;

public class user {
    String email;
    String name;
    String password;
    char gender;
    // waiting for date data type
    String Birthdate;
    Set<Integer> posts = new HashSet<Integer>();
    Vector<Long> userConvs;
    static int userCount = 0;
    int id;
    Set<Integer> friends = new HashSet<Integer>();
    BitSet restrictedUsers = new BitSet();
    BitSet blockedUsers = new BitSet();

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
        // mp[friend].insert(this.id);
        // map<int , user>mp;
        // FBsystem.users.get(friend.id).addFriend(this);
    }

    public void addFriend(user friend) {
        this.friends.add(friend.id);
        // mp[friend].insert(this.id);
        // map<int , user>mp;
        // FBsystem.users.get(friend.id).addFriend(this);
        FBsystem.users.get(friend.id).addFriend(this, false);
    }

    void removeFriend(int userID, Boolean check) {
        // id of friend and his info
        // set<pair<id,user>>s;
        // s.erase(s.lower_bound({id,friend}));
        /*
         * ahmed -> mohsen
         * ahmed - > null
         * mohsen-> ahmed
         * mp[mohsen.id].erase(ahmed);
         * 
         */
        //
        friends.remove(userID);
    }

    void removeFriend(int userID) {
        // id of friend and his info
        // set<pair<id,user>>s;
        // s.erase(s.lower_bound({id,friend}));
        /*
         * ahmed -> mohsen
         * ahmed - > null
         * mohsen-> ahmed
         * mp[mohsen.id].erase(ahmed);
         * 
         */
        //
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

    void CreatePost(String content) {
        Post test = new Post(content);
        FBsystem.posts.pushBack(test);
        this.posts.add(test.id);
    }
    void MakeConversation(Vector<user> users){
        Conversation newconv = new Conversation(users);
        userConvs.add(newconv.uniqueID);
        FBsystem.conversations.put(newconv.uniqueID, newconv);
    }
    void SendMessage(String content,int conversationId){
        message msg = new message(content, conversationId, this.id);
    }

    void restrictUser(int userID){
        restrictedUsers.set(userID);
    }

    void unrestrictUser(int userID){
        restrictedUsers.clear(userID);
    }

    void block(int userID){
        if(FBsystem.users.get(this.id).friends.contains(userID)){
            removeFriend(userID);
        }
        blockedUsers.set(userID, true);
    }

    void unblock(int userID){
        blockedUsers.set(userID, false);
    }

}
