package CustomStructures;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.TreeSet;

public class Trie {
  
  // trie node
  public static class TrieNode {
    Map<Character,TrieNode> children = new HashMap<Character , TrieNode>();
    Vector<Integer> User_ID = new Vector<Integer>();
    int id=-1;

  };

  static TrieNode root = new TrieNode();

  public static boolean insert(String key, int ID) {
    int level;
    int length = key.length();

    TrieNode pCrawl = root;

    for (level = 0; level < length; level++) {

      if (!pCrawl.children.containsKey(key.charAt(level)))
        pCrawl.children.put(key.charAt(level) ,new TrieNode());

        pCrawl = pCrawl.children.get(key.charAt(level));
        pCrawl.User_ID.add(ID);
    }
    if(pCrawl.id == -1)
     { 
      pCrawl.id=ID;
      return true;
     }
     return false;
  }

  public static Vector<Integer> search_users(String key) {
    int level;
    int length = key.length();
    TrieNode pCrawl = root;

    for (level = 0; level < length; level++) {
      if (!pCrawl.children.containsKey(key.charAt(level))) {
        Vector<Integer> Emp = new Vector<Integer>();
        return Emp;
      }
      pCrawl = pCrawl.children.get(key.charAt(level));
    }

    return (pCrawl.User_ID);
  }
   public static Integer search_user(String key) {
    int level;
    int length = key.length();
    TrieNode pCrawl = root;

    for (level = 0; level < length; level++) {
      if (!pCrawl.children.containsKey(key.charAt(level))) {
        return -1;
      }
      pCrawl = pCrawl.children.get(key.charAt(level));
    }

    return pCrawl.id;
  }
}
