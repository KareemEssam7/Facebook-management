import java.io.*;
import java.util.*;

import CustomStructures.*;

public class Post extends Action {
    static int postID = 0;
    char privacy;
    Set<Integer> TaggedId = new HashSet<Integer>();
    public JoinedList<comment> Comments = new JoinedList<comment>();
    Post(String msg, char priv, int userId, Vector<Integer> TaggedId) {
        super(postID, msg, userId);
        this.privacy = priv;
        postID++;
        for(Integer Tagged:TaggedId)
        {
            this.TaggedId.add(Tagged);
        }
    }

     public void saveData(FileWriter writer, String SEPERATOR) throws IOException {
            writer.write(userId+SEPERATOR);
            writer.write(id + SEPERATOR);
            writer.write(privacy + SEPERATOR + "\n");
            writer.write(linesCount+  SEPERATOR);
            writer.write(TaggedId.size() + SEPERATOR);
            writer.write(Comments.getSize() + SEPERATOR);
            writer.write(ReactorsID.size() + "\n");
            writer.write(content + "\n");
            for(Integer taggedId: TaggedId)
            {
                writer.write(taggedId + SEPERATOR);
            }
            writer.write("\n");
            Comments.saveData(writer,SEPERATOR);
            for(Integer reactorId: ReactorsID)
            {
                writer.write(reactorId + SEPERATOR);
            }
            writer.write("\n");

            
            for(Node<comment> it = Comments.iteratorToStart(); it != null; it = Comments.nextValue()) {
                it.value().saveData(new FileWriter(FileHandler.FILES_PATH + FileHandler.COMMENTS_FILE, true), SEPERATOR);
            }

     }

}
