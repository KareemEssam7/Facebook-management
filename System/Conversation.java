package System;

import java.util.*;
import CustomStructures.JoinedList;

public class Conversation {
    public static long id = 0;
    private long uniqueID;

    public HashMap<Long, message> messages = new HashMap<Long, message>();
    public JoinedList<Long> messagesId = new JoinedList<Long>();
    public Set<Integer> participantsID = new HashSet<Integer>();
    long messagesCnt;
    public String convName;

    Conversation(Vector<Integer> par,String convName) {
        uniqueID = id;
        id++;
        for (int i = 0; i < par.size(); i++) {
            participantsID.add(par.get(i));
        }
        this.messagesCnt = 0;
        this.convName=convName;
    }

    Conversation(Vector<Integer> par, int convoId) {
        uniqueID = convoId;

        for (int i = 0; i < par.size(); i++) {
            participantsID.add(par.get(i));
        }
        this.messagesCnt = 0;
    }

    void addParticipants(user User) {
        participantsID.add(User.getId());
    }

    void addParticipants(Vector<user> users) {
        for(user User : users){
            participantsID.add(User.getId());
        }
    }
    public long getUniqueID() {
        return uniqueID;
    }

    public long getMessagesCnt() {
        return messagesCnt;
    }
}