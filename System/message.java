package System;
public class message {
    private final int senderID;
    private final long messageID;
    public String content;
    message(String content,int senderID,long conversationId){
        this.content = content;
        this.senderID = senderID;
        FBsystem.conversations.get(conversationId).messagesCnt++;
        this.messageID = FBsystem.conversations.get(conversationId).messagesCnt;
    }
    message(String content,int senderID,long conversationId, int messageID){
        this.content = content;
        this.senderID = senderID;
        FBsystem.conversations.get(conversationId).messagesCnt++;
        this.messageID = messageID;
    }

    public int getSenderID() {
        return senderID;
    }

    public long getMessageID() {
        return messageID;
    }
}
