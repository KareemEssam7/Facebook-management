import CustomStructures.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.TreeSet;
class main {
    public static void main(String[] args) {

        // user tmp = new user("amany@gmail.com", "amany", "amany123", 'F', "1/1/1");
        // user tmp2 = new user("ayman@gmail.com", "ayman", "ayman123", 'M', "2/2/2");
        // user tmp3 = new user("chinese@gmail.com", "chinese ", "chinese123", 'M', "3/3/3");
        // user tmp4 = new user("khater@gmail.com", "khater", "khater123", 'M', "4/4/4");
        // user tmp5 = new user("FraudHatem@gmail.com", "kareem", "kareem123", 'M', "5/5/5");
        // user tmp6 = new user("joon@gmail.com", "yehia", "yehia123", 'D', "6/6/6");
        // user tmp7 = new user("kareemessam@gmail.com", "kareem", "kareem123", 'M', "7/7/7");
        // tmp2.addFriend(tmp3);
        // tmp2.addFriend(tmp4);
        // System.out.println(tmp3.friends.size());
        // tmp2.removeFriend(tmp3);
        // System.out.println(tmp3.friends.size());
        // System.out.println(tmp2.friends.size());
        // tmp2.CreatePost("test");
        // for (Node<Post> it = FBsystem.posts.iteratorToStart(); it != null; it = FBsystem.posts.nextValue()) {
        //     System.out.println(it.value().content);
        //     System.out.println(it.value().ReactorsID.size());
        //     tmp4.like(it.value());
        //     System.out.println(it.value().ReactorsID.size());
        // }
        // Vector<Integer> v = new Vector<>();
        // v.add(tmp.id);
        // v.add(tmp2.id);
        // v.add(tmp3.id);
        // tmp.MakeConversation(v);
        // System.out.println(FBsystem.conversations.get(1L).participantsID.size());
        // System.out.println(FBsystem.conversations.get(1L).messagesCnt);
        // tmp.SendMessage("Test123", 1L);
        // System.out.println(FBsystem.conversations.get(1L).messagesCnt);
        // tmp.LeaveConversation(1L);
        // System.out.println(FBsystem.conversations.get(1L).participantsID.size());
        // tmp2.LeaveConversation(1L);
        // System.out.println(FBsystem.conversations.get(1L).participantsID.size());
        // tmp3.LeaveConversation(1L);
        // System.out.println(FBsystem.conversations.size());
        //
        user amany = new user("amany@gmail.com", "amany", "amany123", 'F', "1/1/1");
        user ayman = new user("ayman@gmail.com", "ayman", "ayman123", 'M', "2/2/2");
        user jiangly = new user("chinese@gmail.com", "chinese ", "chinese123", 'M', "3/3/3");
        user khater = new user("khater@gmail.com", "khater", "khater123", 'M', "4/4/4");
        user wisdom = new user("FraudHatem@gmail.com", "kareem", "kareem123", 'M', "5/5/5");
        user joon = new user("joon@gmail.com", "yehia", "yehia123", 'D', "6/6/6");
        user kareem = new user("kareemessam@gmail.com", "kareem", "kareem123", 'M', "7/7/7");
        
        jiangly.addFriend(ayman);
        jiangly.addFriend(khater);
        ayman.addFriend(amany);
        khater.addFriend(amany);
        ayman.addFriend(wisdom);

        networkNavigation obj = new networkNavigation(jiangly.id , 3);

        TreeSet<Tuple> s = obj.BFS();

        for(Tuple i : s){
            System.out.println(i.getFirst() + " " + i.getSecond() + " " + i.getThird());
        }

    }
}