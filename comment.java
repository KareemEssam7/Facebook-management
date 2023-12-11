import java.io.*;
import java.util.*;

import CustomStructures.*;

public class comment extends Action {
    static int id2 = 0;
    public JoinedList<Reply> Replies = new JoinedList<Reply>();
    comment(String msg,int userId) {
        super(id2, msg, userId);
        id2++;
    }

    @Override
    public String toString() {
        return Integer.toString(id);
    }

    public void saveData(FileWriter writer, String SEPERATOR) throws IOException {
            writer.write(userId+SEPERATOR);
            writer.write(id + SEPERATOR);
            writer.write(linesCount + SEPERATOR);
            writer.write(Replies.getSize() + SEPERATOR);
            writer.write(ReactorsID.size() + "\n");
            writer.write(content + "\n");
            Replies.saveData(writer,SEPERATOR);
            for(Integer reactorId: ReactorsID)
            {
                writer.write(reactorId + SEPERATOR);
            }
            writer.write("\n");
            writer.close();
             for(Node<Reply> it = Replies.iteratorToStart(); it != null; it = Replies.nextValue()) {
                it.value().saveData(new FileWriter(FileHandler.FILES_PATH + FileHandler.REPLIES_FILE, true), SEPERATOR);
            }
    }
}
