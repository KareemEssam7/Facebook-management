import CustomStructures.*;
import java.util.TreeSet;
class main {
    public static void main(String[] args) {

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