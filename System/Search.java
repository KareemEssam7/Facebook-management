 package System;
 import CustomStructures.*;
public abstract class Search {

  public static Integer search(String myString){

        // Count the occurrences of the character '+'
        int cnt_p = 0;
        char charToCount = '+';
        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) == charToCount) {
                cnt_p++;
            }
        }

    // Count the occurrences of the character '+'
        int cnt_e = 0;
        charToCount = '&';
        for (int i = 0; i < myString.length(); i++) {
            if (myString.charAt(i) == charToCount) {
               cnt_e ++;
            }
        }

    if(cnt_e + cnt_p > 1){
       return -1;
    }
    
    if(cnt_p==1){

       String[] users = myString.split("+") ;

       int ID1=Trie.search_user(users[0]) , ID2=Trie.search_user(users[1]);
        
        if(ID1<1||ID2<1){
            return -2;
        }

       //return vector<post>
       FBsystem.users.get(ID1).common_Posts(ID2); 
       
       return 2;
    }
    
    if(cnt_e==1){

       String[] users = myString.split("&") ;

       int ID1=Trie.search_user(users[0]) , ID2=Trie.search_user(users[1]);
        
        if(ID1<1||ID2<1){
            return -3;
        }
        
       //return vector<INT>
       FBsystem.users.get(ID1).common_Friends(ID2);
       
       return 3;
    }


    //return vector<INT>
    Trie.search_users( myString);

    return 1;

  }
    
}