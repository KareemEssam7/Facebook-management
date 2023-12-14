import java.io.*;
public class Reply extends Action {
    static int id2 = 0;

    Reply(String msg, int userId) {
        super(id2, msg, userId);
        id2++;
    }

    Reply(String msg, int userId, int replyId) {
        super(replyId, msg, userId);
        id2++;
    }
     @Override
    public String toString() {
        return Integer.toString(id);
    }
     public void saveData(FileWriter writer, String SEPERATOR) throws IOException {
           writer.write(userId+SEPERATOR);
           writer.write(id + SEPERATOR);
           writer.write(linesCount+  SEPERATOR);
           writer.write(ReactorsID.size() + "\n");
           writer.write(content + "\n");

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
     }
}
