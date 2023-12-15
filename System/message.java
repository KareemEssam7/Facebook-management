package System;
import java.io.*;
import java.util.*;

import CustomStructures.*;
public class message {
    final int senderID;
    final long messageID;
    String content;
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
}
