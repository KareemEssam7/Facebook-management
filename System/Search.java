 package System;
 import java.util.*;
 import CustomStructures.*;

public abstract class Search {
        public static int status=-1;
  public static Vector<Integer> search(String myString){

    // Count the occurrences of the character '+'
        int cnt_p = 0;
        char charToCount = '+';
        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) == charToCount) {
                cnt_p++;
            }
        }
    // Count the occurrences of the character '&'
        int cnt_e = 0;
        charToCount = '&';
        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) == charToCount) {
               cnt_e ++;
            }
        }

    if(cnt_e + cnt_p > 1){
        status=-1;
       return null;
    }
    
    if(cnt_e==1){

       String[] users = myString.split("&") ;

       int ID1=Trie.search_user(users[0]) , ID2=Trie.search_user(users[1]);
        
        if(ID1<1||ID2<1){
            status=-2;
            return null;
        }
        
       status=2;
       return common_Friends(ID1,ID2) ;
    }

    if(cnt_p==1){

       String[] users = myString.split("+") ;

       int ID1=Trie.search_user(users[0]) , ID2=Trie.search_user(users[1]);
        
        if(ID1<1||ID2<1){
            status=-3;
            return null;
        }
        
       status=3;
       return common_Posts(ID1,ID2);

    }


    status=1;
    return Trie.search_users( myString);

  }

  public static Vector<Integer> common_Posts(int ID1 , int ID2){  
        Vector<Integer> post=new Vector<Integer>();
        user curUser=FBsystem.users.get(ID2);
        for( Node<Integer> it = curUser.posts.iteratorToStart(); it != null; it = curUser.posts.nextValue()) {
          Post cur=FBsystem.posts.get(it.value());
          if(cur.TaggedId.contains(ID1) || cur.userId == ID1){
            post.add(cur.id);
          }
        }

        return post;
    }


   public static Vector<Integer> common_Friends(int ID1 , int ID2){

        Vector<Integer> common_Friends_ID=new Vector<Integer>();

        for( Integer id : FBsystem.users.get(ID2).friends) {
          user cur = FBsystem.users.get(id);
          if(cur.friends.contains(ID1)){
            common_Friends_ID.add(id);
          }
        }

        return common_Friends_ID;
    }

}