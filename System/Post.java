package System;
import java.io.*;
import java.util.*;

import CustomStructures.*;

public class Post extends Action {
    public static int postID = 0;
    private char privacy;
    public Set<Integer> TaggedId = new HashSet<Integer>();
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
            writer.write(privacy + SEPERATOR);
            writer.write(linesCount+  SEPERATOR);
            writer.write(TaggedId.size() + SEPERATOR);
            writer.write(Comments.getSize() + SEPERATOR);
            writer.write(ReactorsID.size() + "\n");
            writer.write(content + "\n");

            int ctr = 0;
            for(Integer taggedId: TaggedId)
            {
                writer.write(taggedId.toString());
                if(ctr != TaggedId.size())
                    writer.write(SEPERATOR);
                ctr++;
            }
            writer.write("\n");
            Comments.saveData(writer,SEPERATOR);

            ctr = 0;
            for(Integer reactorId: ReactorsID)
            {
                writer.write(reactorId.toString());
                if(ctr != ReactorsID.size())
                    writer.write(SEPERATOR);
                ctr++;
            }
            writer.write("\n");

            
            for(Node<comment> it = Comments.iteratorToStart(); it != null; it = Comments.nextValue()) {
                it.value().saveData(new FileWriter(FileHandler.FILES_PATH + FileHandler.COMMENTS_FILE, true), SEPERATOR);
            }

     }

     public char getPrivacy() {
         return privacy;
     }

     public void setPrivacy(char privacy) {
         this.privacy = privacy;
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
        System.out.println("Commenters:");
        for(Node<comment> it = Comments.iteratorToStart(); it != null; it = Comments.nextValue()) {
             System.out.print(FBsystem.users.get(it.value().getUserId()).getName() + " ");  
         }
         System.out.println("");
     }

}