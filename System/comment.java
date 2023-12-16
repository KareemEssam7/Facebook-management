package System;
import java.io.*;

import CustomStructures.*;

public class comment extends Action {
    public static int id2 = 0;
    public JoinedList<Reply> Replies = new JoinedList<Reply>();
    
    comment(String msg,int userId) {
        super(id2, msg, userId);
        id2++;
    }
    comment(String msg,int userId, int commentId) {
        super(commentId, msg, userId);
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

            int ctr = 0;
            for(Integer reactorId: ReactorsID)
            {
                writer.write(reactorId.toString());
                if(ctr != ReactorsID.size())
                    writer.write(SEPERATOR);
                ctr++;
            }
            writer.write("\n");
            writer.close();
             for(Node<Reply> it = Replies.iteratorToStart(); it != null; it = Replies.nextValue()) {
                it.value().saveData(new FileWriter(FileHandler.FILES_PATH + FileHandler.REPLIES_FILE, true), SEPERATOR);
            }
    }
}