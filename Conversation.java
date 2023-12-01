import java.io.*;
import java.util.*;

public class Conversation {
    static long id = 0;
    long uniqueID;
    Set<Long>messagesID = new HashSet<Long>();
    Set<Integer> participantsID = new HashSet<Integer>();
    long messagesCnt;

    Conversation(Vector<user> par) {
        id++;
        uniqueID = id;
        for (int i = 0; i < par.size(); i++) {
            participantsID.add(par.get(i).id);
        }
    }

    void addParticipants(user User) {
        participantsID.add(User.id);
    }   

    void addParticipants(Vector<user> v) {
        for (int i = 0; i < v.size(); i++) {
            participantsID.add(v.get(i).id);
        }
    }
}
