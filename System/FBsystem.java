package System;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;
import CustomStructures.Trie;

public abstract class FBsystem {
    // id and user
    public static HashMap<Integer, user> users = new HashMap<Integer, user>();
    public static HashMap<Long, Conversation> conversations = new HashMap<Long, Conversation>();
    public static HashMap<String, Integer> accounts = new HashMap<String, Integer>();
    public static HashMap<Integer, Post> posts = new HashMap<Integer, Post>();
    public static HashMap<Integer, Page> pages = new HashMap<Integer, Page>();
    private final static String emailConstraints = "^(?=.{1,64}@)[A-Za-z\\d_-]+(\\.[A-Za-z\\d_-]+)*@[A-Za-z\\d][A-Za-z\\d-]+(\\.[A-Za-z\\d-]+)*(\\.[A-Za-z]{2,})$";
    private final static String passwordConstraints = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\p{Punct})[A-Za-z\\d\\p{Punct}]{8,32}$";
    private final static String nameConstraints = "^[A-Za-z][A-Za-z\\d_]{3,20}$";
    public static user CurUser = null;

    // function to hash strings using sha3-256 and returns it as a base-64 string.
    public static String hashString(String stringToHash) {
        try {
            final MessageDigest hash = MessageDigest.getInstance("SHA3-256");
            final byte[] hashedBytes = hash.digest(
                    stringToHash.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    public static boolean patternMatches(String stringToMatch, String pattern) {
        return Pattern.compile(pattern).matcher(stringToMatch).matches();
    }

    // checks if a password is valid
    private static boolean validPassword(String password) {
        return patternMatches(password, passwordConstraints);
    }

    // check if email is valid
    private static boolean validEmail(String email) {
        return patternMatches(email, emailConstraints);
    }

    // check if name is valid
    public static boolean validName(String name) {
        return patternMatches(name, nameConstraints);
    }

    // allows new users to register, returns 0 if the account was created
    // successfully and returns one of the following error codes otherwise:
    // 1: invalid E-mail.
    // 2: Invalid/Weak Password.
    // 3: Invalid Name
    // 4: E-mail already taken.
    // 5: Username already taken.
    // -2: Hashing Error! Algorithm Undefined.
    public static byte Register(String email, String name, String password, char gender, String birthdate) {
        if (!validEmail(email))
            return 1;
        if (!validPassword(password))
            return 2;
        if (!validName(name))
            return 3;
        if (Trie.search_user(name) != -1)
            return 5;
        if (!accounts.containsKey(email)) {
            String hashedPassword = hashString(password);
            if (hashedPassword == null)
                return -2;
            new user(email, name, hashedPassword, gender, birthdate);
            return 0;
        }

        return 4;
    }

    // allows currently registered users to login, reutrns the user id if the login
    // was successful
    // -1 when email/password don't match.
    // -2 Hashing Error, Algorithm Not Found
    public static int Login(String email, String password) {
        if (!accounts.containsKey(email))
            return -1;
        String hashedPassword = hashString(password);
        if (hashedPassword == null)
            return -2;
        if (users.get(accounts.get(email)).comparePassword(hashedPassword)) {
            return users.get(accounts.get(email)).getId();
        } else
            return -1;
    }

    // counts how much the character 'c' have occured
    public static int charCount(char c, String s) {
        int ctr = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == c)
                ctr++;
        }
        return ctr;
    }

    public static void clearData() {
        accounts.clear();
        conversations.clear();
        posts.clear();
        users.clear();
    }
}