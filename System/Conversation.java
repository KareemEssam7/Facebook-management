package System;

import java.io.*;
import java.util.*;

import CustomStructures.*;

public class Conversation {
    static long id = 0;
    long uniqueID;

    HashMap<Long, message> messages = new HashMap<Long, message>();
    Set<Integer> participantsID = new HashSet<Integer>();
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
}