class main {
    public static void main(String[] args) {

        user tmp = new user("amany@gmail.com", "amany", "amany123", 'F', "1/1/1");
        user tmp2 = new user("ayman@gmail.com", "ayman", "ayman123", 'M', "2/2/2");
        user tmp3 = new user("chinese@gmail.com", "chinese ", "chinese123", 'M', "3/3/3");
        user tmp4 = new user("khater@gmail.com", "khater", "khater123", 'M', "4/4/4");
        user tmp5 = new user("FraudHatem@gmail.com", "kareem", "kareem123", 'M', "5/5/5");
        user tmp6 = new user("joon@gmail.com", "yehia", "yehia123", 'M', "6/6/6");
        user tmp7 = new user("kareemessam@gmail.com", "kareem", "kareem123", 'M', "7/7/7");
        tmp2.addFriend(tmp3);
        tmp2.addFriend(tmp4);
        System.out.println(tmp3.friends.size());
        tmp2.removeFriend(tmp3);
        System.out.println(tmp3.friends.size());
        System.out.println(tmp2.friends.size());
        tmp2.CreatePost("test");
        for (Integer i : tmp2.posts) {
            System.out.println(FBsystem.posts.get(i).content);
            System.out.println(FBsystem.posts.get(i).ReactorsID.size());
            tmp4.like(FBsystem.posts.get(i));
            System.out.println(FBsystem.posts.get(i).ReactorsID.size());
        }
    }
}