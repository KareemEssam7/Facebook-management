import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;

import CustomStructures.*;

public class FBsystem {
    // id and user
    public static HashMap<Integer, user> users = new HashMap<Integer, user>();
    public static HashMap<Long, Conversation> conversations = new HashMap<Long, Conversation>();
    public static HashMap<String, Integer> accounts = new HashMap<String, Integer>();
    public static JoinedList<Post> posts = new JoinedList<Post>();
    public static JoinedList<Reply> Replies = new JoinedList<Reply>();
    public static JoinedList<comment> Comments = new JoinedList<comment>();
    private final static String emailConstraints = "^(?=.{1,64}@)[A-Za-z\\d_-]+(\\.[A-Za-z\\d_-]+)*@[A-Za-z\\d][A-Za-z\\d-]+(\\.[A-Za-z\\d-]+)*(\\.[A-Za-z]{2,})$";
    private final static String passwordConstraints = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\p{Punct})[A-Za-z\\d\\p{Punct}]{8,32}$";
    

    //function to hash strings using sha3-256 and returns it as a base-64 string.
    public static String hashString(String stringToHash) {
        try {
            final MessageDigest hash = MessageDigest.getInstance("SHA3-256");
            final byte[] hashedBytes = hash.digest(
            stringToHash.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Unable to hash, algorithm undefined");
            return null;
        }
    }

    public static boolean patternMatches(String stringToMatch, String pattern) {
        return Pattern.compile(pattern).matcher(stringToMatch).matches();
    }

    //checks if a password is valid
    private static boolean validPassword(String password) {
        return patternMatches(password, passwordConstraints);
    }

    //check if email is valid
    private static boolean validEmail(String email) {
        return patternMatches(email, emailConstraints);
    }

    //allows new users to register, returns 0 if the account was created successfully and returns one of the following error codes otherwise:
    //1: invalid E-mail.
    //2: Invalid/Weak Password.
    //3: E-mail already taken.
    public static byte Register(String email, String name, String password, char gender, String birthdate) {
        if(!validEmail(email)) return 1;
        if(!validPassword(password)) return 2;
        if(!accounts.containsKey(email)) {
            String hashedPassword = new String(hashString(password));
            user newUser = new user(email, name, hashedPassword, gender, birthdate);
            users.put(newUser.getId(), newUser);
            accounts.put(email, newUser.getId());
            return 0;
        }
        
        return 3;
    }

    //allows currently registered users to login, reutrns the user id if the login was successful, and -1 otherwise.
    public static int Login(String email, String password) {
        if(!accounts.containsKey(email)) return -1;
        String hashedPassword = new String(hashString(password));
        if(users.get(accounts.get(email)).comparePassword(hashedPassword)) {
            return users.get(accounts.get(email)).getId();
        }
        else
            return -1;
    }
}
