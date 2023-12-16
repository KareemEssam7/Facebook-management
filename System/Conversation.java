package System;

import java.util.*;

public class Conversation {
    public static long id = 0;
    private long uniqueID;

    public HashMap<Long, message> messages = new HashMap<Long, message>();
    public Set<Integer> participantsID = new HashSet<Integer>();
    long messagesCnt;

    Conversation(Vector<Integer> par) {
        uniqueID = id;
        id++;
        for (int i = 0; i < par.size(); i++) {
            participantsID.add(par.get(i));
        }
        this.messagesCnt = 0;
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

    void addParticipants(Vector<user> v) {

    }
    public long getUniqueID() {
        return uniqueID;
    }

    public long getMessagesCnt() {
        return messagesCnt;
    }
}