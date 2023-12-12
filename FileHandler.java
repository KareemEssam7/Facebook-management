import java.io.*;
import java.util.*;


public class FileHandler {
    public final static String FILES_PATH = new String(".\\Facebook-management\\Database\\");
    public final static String USERS_FILE = new String("users.data");
    public final static String POSTS_FILE = new String("posts.data");
    public final static String COMMENTS_FILE = new String("comments.data");
    public final static String REPLIES_FILE = new String("replies.data");
    public final static String SEPERATOR = new String("|");


    
    public static void save() throws IOException {
        FileWriter writer = new FileWriter(FILES_PATH+USERS_FILE);
        
        writer.write(user.userCount + "\n");
        for(Map.Entry<Integer, user> userData : FBsystem.users.entrySet()) {
            user currUser = userData.getValue();
            
            writer.write(currUser.getId() + SEPERATOR);
            writer.write(currUser.name + SEPERATOR);
            writer.write(currUser.email + SEPERATOR);
            currUser.savePassword(writer);
            writer.write(SEPERATOR);
            writer.write(currUser.Birthdate + SEPERATOR);
            writer.write(currUser.gender + SEPERATOR);
            writer.write(currUser.restrictedUsers.size() + SEPERATOR);
            writer.write(currUser.blockedUsers.size() + SEPERATOR);
            writer.write(currUser.friends.size() + SEPERATOR);
            writer.write(currUser.posts.getSize() + SEPERATOR);
            writer.write(currUser.userConvs.size() + SEPERATOR);
            writer.write(currUser.feed.getSize() + "\n");
            writer.write(currUser.restrictedUsers.toString() + "\n");
            writer.write(currUser.blockedUsers.toString() + "\n");
            for(Integer currFriend : currUser.friends) writer.write(currFriend + SEPERATOR);
            writer.write("\n");
            currUser.posts.saveData(writer, SEPERATOR);
            for(Long currFriend : currUser.userConvs) writer.write(currFriend + SEPERATOR);
            writer.write("\n");
            currUser.feed.saveData(writer, SEPERATOR);
        }
        writer.close();

        FileWriter tempWriter = new FileWriter(FILES_PATH + COMMENTS_FILE);
        tempWriter.write(comment.id2 + "\n");
        tempWriter.close();
        tempWriter = new FileWriter(FILES_PATH + REPLIES_FILE);
        tempWriter.write(Reply.id2 + "\n");
        tempWriter.close();
        writer = new FileWriter(FILES_PATH + POSTS_FILE);
        writer.write(Post.postID + "\n");
        for(Map.Entry<Integer, Post> postsData : FBsystem.posts.entrySet()) {
            postsData.getValue().saveData(writer, SEPERATOR);
        }
        writer.write("\n");
        writer.close();
        

    }
}
