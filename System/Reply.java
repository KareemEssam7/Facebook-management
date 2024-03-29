package System;
import java.io.*;
public class Reply extends Action {
    public static int id2 = 0;

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

     @Override
     public void showContent() {
        System.out.println(FBsystem.users.get(userId).getName());
        System.out.println(content);
        System.out.println("Reactors: ");
        for(Integer it : ReactorsID) {
           System.out.print(FBsystem.users.get(it).getName() + " ");
       }
       System.out.println("");
     }
}
