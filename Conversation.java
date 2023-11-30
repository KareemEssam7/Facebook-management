import java.io.*;
import java.util.*;

public class Conversation {
    static long id = 0;
    long uniqueID;
    Vector<message> messages;
    Vector<user> participants;

    Conversation(Vector<user> par) {
        id++;
        uniqueID = id;
        for (int i = 0; i < par.size(); i++) {
            participants.add(par.get(i));
        }
    }

    void addParticipants(user User) {
        participants.add(User);
    }

    void addParticipants(Vector<user> v) {
        for (int i = 0; i < v.size(); i++) {
            participants.add(v.get(i));
        }
    }
}
