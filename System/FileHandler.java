package System;
import java.io.*;
import java.util.*;

public abstract class FileHandler {
    public final static String FILES_PATH = new String(".\\Facebook-management\\Database\\");
    public final static String USERS_FILE = new String("users.data");
    public final static String POSTS_FILE = new String("posts.data");
    public final static String COMMENTS_FILE = new String("comments.data");
    public final static String REPLIES_FILE = new String("replies.data");
    public final static String CONVERSATIONS_FILE = new String("conversations.data");
    public final static String SEPERATOR = new String("|");
    public final static String SEPERATOR_EXPR = new String("\\|");


    private static void FileIntializer() throws IOException{
        String[] fileNames = new String[5];
        fileNames[0] = new String(USERS_FILE);
        fileNames[1] = new String(POSTS_FILE);
        fileNames[2] = new String(COMMENTS_FILE);
        fileNames[3] = new String(REPLIES_FILE);
        fileNames[4] = new String(CONVERSATIONS_FILE);

        for(String fileName : fileNames) {
            File file = new File(FILES_PATH + fileName);
            file.createNewFile();
        }
    }

    private static String bitsetToString(BitSet bs) {
        StringJoiner content = new StringJoiner(SEPERATOR);
        for(int i = 0; i < bs.size(); i++) {
            if(bs.get(i))
                content.add(Integer.toString(i));
        }

        return content.toString();
    }

    private static BitSet stringToBitSet(String[] s) {
        BitSet bs = new BitSet();
        for(String bitOn : s)
            bs.set(Integer.parseInt(bitOn));
        return bs;
    }

    private static String multiString(Scanner reader, int linesCount) {
        StringJoiner content = new StringJoiner("\n");

        while(linesCount-- != 0)
            content.add(reader.nextLine());

        return content.toString();
    }
    
    public static void save() throws IOException {
        FileIntializer();

        //users
        FileWriter writer = new FileWriter(FILES_PATH+USERS_FILE);
        writer.write(user.userCount + "\n");
        for(Map.Entry<Integer, user> userData : FBsystem.users.entrySet()) {
            user currUser = userData.getValue();
            
            writer.write(currUser.getId() + SEPERATOR);
            writer.write(currUser.getName() + SEPERATOR);
            writer.write(currUser.getEmail() + SEPERATOR);
            currUser.savePassword(writer);
            writer.write(SEPERATOR);
            writer.write(currUser.getBirthdate() + SEPERATOR);
            writer.write(currUser.getGender() + SEPERATOR);
            writer.write(currUser.restrictedUsers.cardinality() + SEPERATOR);
            writer.write(currUser.blockedUsers.cardinality() + SEPERATOR);
            writer.write(currUser.friends.size() + SEPERATOR);
            writer.write(currUser.posts.getSize() + SEPERATOR);
            writer.write(currUser.userConvs.size() + SEPERATOR);
            writer.write(currUser.feed.getSize() + "\n");

            writer.write(bitsetToString(currUser.restrictedUsers) + "\n");

            writer.write(bitsetToString(currUser.blockedUsers) + "\n");

            int ctr = 0;
            for(Integer currFriend : currUser.friends) {
                writer.write(currFriend.toString());
                if(ctr != currUser.friends.size()-1)
                    writer.write(SEPERATOR);
                ctr++;
            }
            writer.write("\n");

            currUser.posts.saveData(writer, SEPERATOR);
            
            ctr = 0;
            for(Long currConvo : currUser.userConvs) {
                writer.write(currConvo.toString());
                if(ctr != currUser.userConvs.size()-1)
                    writer.write(SEPERATOR);
                ctr++;
            }
            writer.write("\n");

            currUser.feed.saveData(writer, SEPERATOR);
        }
        writer.close();

        //posts, comments, and replies
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

        //conversations
        writer = new FileWriter(FILES_PATH + CONVERSATIONS_FILE);
        writer.write(Conversation.id + "\n");
        for(Map.Entry<Long, Conversation> conversation : FBsystem.conversations.entrySet()) {
            Conversation currConversation = conversation.getValue();
            writer.write(currConversation.getUniqueID() + SEPERATOR);
            writer.write(currConversation.participantsID.size() + SEPERATOR);
            writer.write(currConversation.messagesCnt + "\n");

            int ctr = 0;
            for(Integer participantID : currConversation.participantsID) {
                writer.write(participantID.toString());
                if(ctr != currConversation.participantsID.size()-1)
                    writer.write(SEPERATOR);
                ctr++;
            }
            writer.write("\n");

            for(Map.Entry<Long, message> Message : currConversation.messages.entrySet()) {
                message currMessage = Message.getValue();
                writer.write(currMessage.getMessageID() + SEPERATOR);
                writer.write(currMessage.getSenderID() + SEPERATOR);
                writer.write((FBsystem.charCount('\n', currMessage.content)+1) + "\n");

                writer.write(currMessage.content + "\n");
            }
            
        }
        
        writer.close();
        
    }

    public static void load() throws IOException {
        FileIntializer();
        FBsystem.clearData();

        //replies
        File file = new File(FILES_PATH + REPLIES_FILE);
        Scanner reader = new Scanner(file);
        int repliesCount = Integer.parseInt(reader.nextLine());
        HashMap<Integer, Reply> replies = null;
        if(repliesCount != 0) {
            replies = new HashMap<Integer, Reply>();
            for(int i = 0; i < repliesCount; i++) {
                String[] mainDetails = reader.nextLine().split(SEPERATOR_EXPR, 4);
                int linesCount = Integer.parseInt(mainDetails[2]);
                
                String content = multiString(reader, linesCount);
                int id = Integer.parseInt(mainDetails[1]);
                replies.put(id, new Reply(content, Integer.parseInt(mainDetails[0]), id));

                int reactorsCount = Integer.parseInt(mainDetails[3]);
                if(reactorsCount != 0) {
                    String[] ReactorIDs = reader.nextLine().split(SEPERATOR_EXPR, reactorsCount);
                    for(String reactorID : ReactorIDs) {
                        replies.get(i).ReactorsID.add(Integer.parseInt(reactorID));
                    }
                }
                else
                    reader.nextLine();
            }
        }

        reader.close();
        
        //comments
        file = new File(FILES_PATH + COMMENTS_FILE);
        reader = new Scanner(file);
        int commentsCount = Integer.parseInt(reader.nextLine());
        HashMap<Integer, comment> comments = null;
        if(commentsCount != 0) {
            comments = new HashMap<Integer, comment>();
            for(int i = 0; i < commentsCount; i++) {
                String[] mainDetails = reader.nextLine().split(SEPERATOR_EXPR, 5);
                
                int linesCount = Integer.parseInt(mainDetails[2]);
                String content = multiString(reader, linesCount);
                int id = Integer.parseInt(mainDetails[1]);

                comments.put(id, new comment(content, Integer.parseInt(mainDetails[0]), id));

                int reactorsCount = Integer.parseInt(mainDetails[4]);
                int currRepliesCount = Integer.parseInt(mainDetails[3]);

                if(currRepliesCount != 0) {
                    String[] RepliesIDs = reader.nextLine().split(SEPERATOR_EXPR, currRepliesCount);
                    for(String replyID : RepliesIDs) {
                        comments.get(i).Replies.pushBack(replies.get(Integer.parseInt(replyID)));
                    }
                }
                else
                    reader.nextLine();

                if(reactorsCount != 0) {
                    String[] ReactorIDs = reader.nextLine().split(SEPERATOR_EXPR, reactorsCount);
                    for(String reactorID : ReactorIDs) {
                        comments.get(i).ReactorsID.add(Integer.parseInt(reactorID));
                    }
                }
                else
                    reader.nextLine();

            }
            reader.close();
            replies.clear();

            //posts
            file = new File(FILES_PATH + POSTS_FILE);
            reader = new Scanner(file);
            int postsCount = Integer.parseInt(reader.nextLine());
            for(int i = 0; i < postsCount; i++) {
                String[] mainDetails = reader.nextLine().split(SEPERATOR_EXPR, 7);
                int linesCount = Integer.parseInt(mainDetails[3]);
                String content = multiString(reader, linesCount);

                int id = Integer.parseInt(mainDetails[1]);

                int taggedCount = Integer.parseInt(mainDetails[4]);

                Vector<Integer> TaggedIds = new Vector<Integer>(taggedCount+1);
                if(taggedCount != 0) {
                    String[] TaggedStrings = reader.nextLine().split(SEPERATOR_EXPR, taggedCount);
                    for(String taggedID : TaggedStrings) {
                        TaggedIds.add(Integer.parseInt(taggedID));
                    }
                }
                else
                    reader.nextLine();

                char privacy = mainDetails[2].charAt(0);
                FBsystem.posts.put(id, new Post(content, privacy, Integer.parseInt(mainDetails[0]), TaggedIds));

                int currCommentsCount = Integer.parseInt(mainDetails[5]);

                if(currCommentsCount != 0) {
                    String[] commentStrings = reader.nextLine().split(SEPERATOR_EXPR, currCommentsCount);
                    for(String commID : commentStrings) {
                        FBsystem.posts.get(id).Comments.pushBack(comments.get(Integer.parseInt(commID)));
                    }
                }
                else
                    reader.nextLine();

                int reactorsCount = Integer.parseInt(mainDetails[6]);
                if(reactorsCount != 0) {
                    String[] ReactorIDs = reader.nextLine().split(SEPERATOR_EXPR, reactorsCount);
                    for(String reactorID : ReactorIDs) {
                        FBsystem.posts.get(i).ReactorsID.add(Integer.parseInt(reactorID));
                    }
                }
                else
                    reader.nextLine();

            }
            reader.close();
            comments.clear();

            //user
            file = new File(FILES_PATH + USERS_FILE);
            reader = new Scanner(file);
            int usersCount = Integer.parseInt(reader.nextLine());
            for(int i = 0; i < usersCount; i++) {
                String[] mainDetails = reader.nextLine().split(SEPERATOR_EXPR, 12);
                String userMail = mainDetails[2];
                int id = Integer.parseInt(mainDetails[0]);

                FBsystem.accounts.put(userMail, id);
                FBsystem.users.put(id, new user(userMail, mainDetails[1], mainDetails[3], mainDetails[5].charAt(0), mainDetails[4], id));
                int restrictedSize = Integer.parseInt(mainDetails[6]);
                int blockedSize = Integer.parseInt(mainDetails[7]);
                int friendsSize = Integer.parseInt(mainDetails[8]);
                int postsSize = Integer.parseInt(mainDetails[9]);
                int convosSize = Integer.parseInt(mainDetails[10]);
                int feedSize = Integer.parseInt(mainDetails[11]);

                if(restrictedSize != 0)
                    FBsystem.users.get(id).restrictedUsers = stringToBitSet(reader.nextLine().split(SEPERATOR_EXPR, restrictedSize));
                else
                    reader.nextLine();

                if(blockedSize != 0)
                    FBsystem.users.get(id).blockedUsers = stringToBitSet(reader.nextLine().split(SEPERATOR_EXPR, blockedSize));
                else
                    reader.nextLine();

                if(friendsSize != 0) {
                    String[] friendStrings = reader.nextLine().split(SEPERATOR_EXPR, friendsSize);
                    for(String friend : friendStrings)
                        FBsystem.users.get(id).friends.add(Integer.parseInt(friend));
                }
                else
                    reader.nextLine();
                    
                if(postsSize != 0) {
                    String[] postStrings = reader.nextLine().split(SEPERATOR_EXPR, postsSize);
                    for(String currPost : postStrings)
                        FBsystem.users.get(id).posts.pushBack(Integer.parseInt(currPost));
                }
                else
                    reader.nextLine();
                    
                    if(convosSize != 0) {
                        String[] convoStrings = reader.nextLine().split(SEPERATOR_EXPR, convosSize);
                        for(String convo : convoStrings)
                            FBsystem.users.get(id).userConvs.add(Long.parseLong(convo));
                    }
                    else
                        reader.nextLine();

                    if(feedSize != 0) {
                        String[] feedStrings = reader.nextLine().split(SEPERATOR_EXPR, feedSize);
                        for(String currFeed : feedStrings)
                            FBsystem.users.get(id).posts.pushBack(Integer.parseInt(currFeed));
                    }
                    else
                        reader.nextLine();
                
            }

            reader.close();

            //conversations
            file = new File(FILES_PATH + CONVERSATIONS_FILE);
            reader = new Scanner(file);
            int conversationsCount = Integer.parseInt(reader.nextLine());
            for(int i = 0; i < conversationsCount; i++) {
                String[] mainDetails = reader.nextLine().split(SEPERATOR_EXPR, 3);
                long id = Long.parseLong(mainDetails[0]);
                int participantsCount = Integer.parseInt(mainDetails[1]);
                int msgCount = Integer.parseInt(mainDetails[2]);
                
                Vector<Integer> participants = new Vector<Integer>(participantsCount+1);
                String[] participantStrings = reader.nextLine().split(SEPERATOR_EXPR, participantsCount);
                for(String participant : participantStrings)
                    participants.add(Integer.parseInt(participant));

                FBsystem.conversations.put(id, new Conversation(participants));

                for(int j = 0; j < msgCount; j++) {
                    String[] messageDetails = reader.nextLine().split(SEPERATOR_EXPR);
                    String content = multiString(reader, Integer.parseInt(messageDetails[2]));
                    message msg = new message(content, Integer.parseInt(messageDetails[1]), id, Integer.parseInt(messageDetails[0]));
                    FBsystem.conversations.get(id).messages.put(msg.getMessageID(), msg);
                }
            }
        }
    }

    public static void clearDB() throws IOException {
        String[] fileNames = new String[5];
        fileNames[0] = new String(USERS_FILE);
        fileNames[1] = new String(POSTS_FILE);
        fileNames[2] = new String(COMMENTS_FILE);
        fileNames[3] = new String(REPLIES_FILE);
        fileNames[4] = new String(CONVERSATIONS_FILE);

        for(String fileName : fileNames) {
            File file = new File(FILES_PATH + fileName);
            if(file.exists()) {
                file.delete();
                file.createNewFile();
            }
        }
    }
}