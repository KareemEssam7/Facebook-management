public class message {
    int senderID;
    int conversationId;
    long messageID;
    String content;
    message(String content,int senderID,long conversationId){
        this.content = content;
        this.senderID = senderID;
        FBsystem.conversations.get(conversationId).messagesCnt++;
        this.messageID = FBsystem.conversations.get(conversationId).messagesCnt;
        FBsystem.conversations.get(conversationId).messagesID.add(this.messageID);
    }
}
