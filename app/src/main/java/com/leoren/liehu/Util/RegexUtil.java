package com.leoren.liehu.Util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author Leoren
 * @Date 2018/6/9 17:26
 */
public class RegexUtil {

    /*
    public static int STRING_USERNAME = 1;
    public static int STRING_PASSWORD = 2;
    public static int STRING_EMAIL = 3;
    public static int STRING_PHONE = 4;
    */
    public static String STRING_USERNAME = "[A-Za-z0-9_\\\\-\\\\u4e00-\\\\u9fa5]+";
    //public static String STRING_PASSWORD = 2;
    public static String STRING_EMAIL = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
    public static String STRING_PHONE = "0?(13|14|15|18|17)[0-9]{9}";



    public static boolean regexEmail(String email){
        String regex = STRING_EMAIL;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean regexPhone(String phonenum){
        String regex = STRING_PHONE;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phonenum);
        return matcher.matches();
    }

    public static boolean regexUsername(String username){
        String regex = STRING_USERNAME;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean regexPassword(String password){

        return true;
    }




}
