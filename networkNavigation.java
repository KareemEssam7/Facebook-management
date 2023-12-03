import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import CustomStructures.Tuple;
public class networkNavigation {
    /*
     * 
     * This is a direct implementation of BFS, just takes a depth level and a user id. and returns a treeset
     * datastructure that is sorted according to the people the user isn't friends with but have either friends 
     * or friends of friends in common (depending on the depth level taken as a parameter).
     * ------------------------------------------------------------------------------------------------
     * Will also handle post propagation using the same method, taking the depth level and a post object
     * and posting it other users fields that are applicable to the constraints and also not restricted users.
     * ------------------------------------------------------------------------------------------------------ 
     * Also the current intuition to handle restricted users is add a bitset to each user and if a user is 
     * restricted we'll just have his the indx corresponding to his id toggled on and just check while doing
     * the (post propagation / friends suggestions).
     * 
     */

    private int depth;
    private int userID;

    TreeMap<Integer ,Integer> level = new TreeMap<>();

    Set<Integer> currUserFriends;

    public networkNavigation(int userId, int depth){
        
        this.depth = depth;
        this.userID = userId;
        level.put(userId, 0);

        currUserFriends = FBsystem.users.get(userId).friends;
      
        for(Integer friendId : currUserFriends){
            level.put(friendId, 1);
        }

    }

    TreeSet<Tuple> Search(){

        TreeSet<Tuple> ret = new TreeSet<Tuple>();

        Queue<Integer> q = new LinkedList<>();
        
        for(Integer friendID : currUserFriends){
            q.add(friendID);
        }

        TreeMap<Integer, Integer> userDepth = new TreeMap<>();
        TreeMap<Integer, Integer> userFreq = new TreeMap<>();
 
        while(!q.isEmpty()){
            
            int currUser = q.poll();

            for(Integer child : FBsystem.users.get(currUser).friends){

                int currlevel = level.get(currUser);

                boolean check = FBsystem.users.get(userID).blockedUsers.get(child);
                
                if(check != true && !level.containsKey(child)){

                    if(currlevel < 1 + depth){

                        level.put(child, currlevel + 1);
                        userDepth.put(child, currlevel);
                        q.add(child);

                        if(userFreq.containsKey(child)){
                            int last = userFreq.get(child);
                            userFreq.remove(child);
                            userFreq.put(child, last + 1);

                        }else{
                            userFreq.put(child, 1);

                        }
                    }
                }else{
                    if(check != true && level.get(child) > currlevel){
                        int last = userFreq.get(child);
                            userFreq.remove(child);
                            userFreq.put(child, last + 1);

                    }
                }
            }

        }

        for(Integer userID : userDepth.keySet()){
            Tuple tp = new Tuple(userDepth.get(userID), userFreq.get(userID), userID);
            ret.add(tp);
        }

        return ret;

    }


}
