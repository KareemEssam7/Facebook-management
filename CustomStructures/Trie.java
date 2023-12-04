package CustomStructures;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.TreeSet;
public class Trie {
     
    // Alphabet size (# of symbols)
   public  static final int ALPHABET_SIZE = 26;
     
    // trie node
    public static class TrieNode
    {
        TrieNode[] children = new TrieNode[ALPHABET_SIZE];
       
        // isEndOfWord is true if the node represents
        // end of a word
          Vector<Integer>User_ID=new Vector<Integer>();

    };
      
     static TrieNode root=new TrieNode(); 
     
    // If not present, inserts key into trie
    // If the key is prefix of trie node, 
    // just marks leaf node

    public static void insert(String key , int ID)
    {
        int level;
        int length = key.length();
        int index;
      
        TrieNode pCrawl = root;

        for (level = 0; level < length; level++)
        {
            if ( key.charAt(level) < 'a') {
               index = key.charAt(level) - 'A';   
             }else{
               index = key.charAt(level) - 'a';
             }
            if (pCrawl.children[index] == null)
                pCrawl.children[index] = new TrieNode();
            pCrawl = pCrawl.children[index];
             pCrawl.User_ID.add(ID);
        }
    }
      
    // Returns true if key presents in trie, else false
    public static Vector search(String key)
    {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;
      
        for (level = 0; level < length; level++)
        {
           if ( key.charAt(level) < 'a') {
               index = key.charAt(level) - 'A';   
             }else{
               index = key.charAt(level) - 'a';
             }
      
            if (pCrawl.children[index] == null){
                Vector<Integer>Emp =new Vector<Integer>();
                return Emp;
            }
            pCrawl = pCrawl.children[index];
        }
      
        return (pCrawl. User_ID);
    }
}
      