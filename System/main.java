package System;

import CustomStructures.*;
import System.*;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import java.util.TreeSet;

class main {
    public static void main(String[] args) {

        // // user tmp = new user("amany@gmail.com", "amany", "amany123", 'F', "1/1/1");
        // // user tmp2 = new user("ayman@gmail.com", "ayman", "ayman123", 'M', "2/2/2");
        // // user tmp3 = new user("chinese@gmail.com", "chinese ", "chinese123", 'M',
        // // "3/3/3");
        // // user tmp4 = new user("khater@gmail.com", "khater", "khater123", 'M',
        // // "4/4/4");
        // // user tmp5 = new user("FraudHatem@gmail.com", "kareem", "kareem123", 'M',
        // // "5/5/5");
        // // user tmp6 = new user("joon@gmail.com", "yehia", "yehia123", 'D', "6/6/6");
        // // user tmp7 = new user("kareemessam@gmail.com", "kareem", "kareem123", 'M',
        // // "7/7/7");
        // // tmp2.addFriend(tmp3);
        // // tmp2.addFriend(tmp4);
        // // System.out.println(tmp3.friends.size());
        // // tmp2.removeFriend(tmp3);
        // // System.out.println(tmp3.friends.size());
        // // System.out.println(tmp2.friends.size());
        // // tmp2.CreatePost("test");
        // // for (Node<Post> it = FBsystem.posts.iteratorToStart(); it != null; it =
        // // FBsystem.posts.nextValue()) {
        // // System.out.println(it.value().content);
        // // System.out.println(it.value().ReactorsID.size());
        // // tmp4.like(it.value());
        // // System.out.println(it.value().ReactorsID.size());
        // // }
        // // Vector<Integer> v = new Vector<>();
        // // v.add(tmp.id);
        // // v.add(tmp2.id);
        // // v.add(tmp3.id);
        // // tmp.MakeConversation(v);
        // // System.out.println(FBsystem.conversations.get(1L).participantsID.size());
        // // System.out.println(FBsystem.conversations.get(1L).messagesCnt);
        // // tmp.SendMessage("Test123", 1L);
        // // System.out.println(FBsystem.conversations.get(1L).messagesCnt);
        // // tmp.LeaveConversation(1L);
        // // System.out.println(FBsystem.conversations.get(1L).participantsID.size());
        // // tmp2.LeaveConversation(1L);
        // // System.out.println(FBsystem.conversations.get(1L).participantsID.size());
        // // tmp3.LeaveConversation(1L);
        // // System.out.println(FBsystem.conversations.size());
        // // testcommit123

        // // jiangly.addFriend(ayman);
        // // jiangly.addFriend(khater);
        // // ayman.addFriend(amany);
        // // khater.addFriend(amany);
        // // amany.addFriend(wisdom);
        // // khater.addFriend(kareem);
        // // kareem.addFriend(wisdom);
        // // joon.addFriend(mahrous);

        // // jiangly.block(amany.getId());
        // // jiangly.unblock(amany.getId());
        // // jiangly.block(ayman.getId());

        // // networkNavigation obj = new networkNavigation(jiangly.getId());

        // // TreeSet<Tuple> s = obj.suggestFriends(3);

        // // for(Tuple i : s){
        // // System.out.println(i.getFirst() + " " + i.getSecond() + " " + i.getThird());
        // // }
        // // byte error_code = FBsystem.Register("juneara@meow.asu.edu.eg", "June~Chan",
        // // "6Ara_Ara9", 'A', "9/1/1");
        // // if(error_code == 0)
        // // System.out.println("User Registered Successfully.\n");
        // // else
        // // System.out.println("Error code occured: " + error_code);

        // // int currUser = FBsystem.Login("juneara@meow.com", "6Ara_Ara9");
        // // if(currUser == -1)
        // // System.out.println("incorrect username or password\n");
        // // else
        // // System.out.println("Logged in successfully\n");

        // // Trie.insert(ayman.name,ayman.getId());
        // // Trie.insert(amany.name,amany.getId());
        // // Vector<Integer> vc=new Vector<Integer>();
        // // vc=Trie.search("A");
        // // System.out.println(vc.get(0));
        // // System.out.println(vc.get(1));
        // user amany = new user("amany@gmail.com", "amany", "amany123", 'F', "1/1/1");
        // user ayman = new user("ayman@gmail.com", "ayman", "ayman123", 'M', "2/2/2");
        // user jiangly = new user("chinese@gmail.com", "chinese ", "chinese123", 'M', "3/3/3");
        // user khater = new user("khater@gmail.com", "khater", "khater123", 'M', "4/4/4");
        // user wisdom = new user("FraudHatem@gmail.com", "kareem", "kareem123", 'M', "5/5/5");
        // user joon = new user("joon@gmail.com", "yehia", "yehia123", 'D', "6/6/6");
        // user kareem = new user("kareemessam@gmail.com", "kareem", "kareem123", 'M', "7/7/7");
        // user mahrous = new user("mahrousElNayem", "Mahrous", "Mahrous123", 'L', "8/8/8");
        // jiangly.addFriend(ayman);
        // jiangly.addFriend(khater);
        // jiangly.addFriend(amany);
        // kareem.block(ayman.getId());
        // ayman.block(kareem.getId());
            
     
        // ayman.addFriend(amany);
        // khater.addFriend(amany);
        // amany.addFriend(wisdom);
        // khater.addFriend(kareem);
        // kareem.addFriend(wisdom);
        // joon.addFriend(mahrous);
        // mahrous.addFriend(kareem);

        // jiangly.block(mahrous.getId());
        // // jiangly.block(amany.getId());
        // // jiangly.unblock(amany.getId());
        // jiangly.block(ayman.getId());
        // jiangly.CreatePost("Cats are really good", '+', new Vector<Integer>());
        // jiangly.CreatePost("cats are bad", '-', new Vector<Integer>());
        // amany.addComment(FBsystem.posts.get(amany.feed.getStartNode().value()), "i hate clean code");
        // khater.addReply(FBsystem.posts.get(amany.feed.getStartNode().value()).Comments.getStartNode().value(),
        //         "i love segment tree");

        // Vector<Integer> convo = new Vector<Integer>();
        // convo.add(jiangly.getId());
        // convo.add(mahrous.getId());

        // jiangly.MakeConversation(convo,"Graph practise");
        // jiangly.SendMessage("I hate Mahrous", jiangly.userConvs.iterator().next());
        // try {
        //     FileHandler.load();
        //     FileHandler.clearDB();
        //     FileHandler.save();
        //     System.out.println("SUCCESS");
        // } catch (IOException e) {
        //     System.out.println("FAIL");
        // }
        // Vector<Integer> t = new Vector<Integer>();
        // t.add(kareem.getId());
        // khater.CreatePost("I love segment tree",'+',t);
        
        // Vector<Integer> ids = new Vector<Integer>();
        // ids.add(ayman.getId());
        // ids.add(mahrous.getId());
        // ayman.CreatePost("string",'+',ids);

        // try {
        //     FileHandler.load();
        // }
        // catch(IOException e) {
        //     System.out.println("Failed to load");
        // }
        // Vector<Integer> Idss = new Vector<Integer>();
        // Idss.add(2);
        // FBsystem.users.get(1).MakeConversation(Idss, "ConvTest");
        // try {
        //     FileHandler.save();
        // }
        // catch(IOException e) {
        //     System.out.println("Failed to save");
        // }

    }
}
