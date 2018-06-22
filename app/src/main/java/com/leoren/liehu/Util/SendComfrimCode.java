package com.leoren.liehu.Util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 发送短信验证码的工具类
 * Create by Leoren on 18/4/21 18:00
 */
public class SendComfrimCode {
    public static final String queryUrl = "https://api.miaodiyun.com/20150822/industrySMS/sendSMS";
    public static final String titile = "Content-type:application/x-www-form-urlencoded";
    public static final String phone_num = "15735659106";
    public static final String ACCOUNT_SID = "be89ea680a054677a6fab2873884e929";
    public static final String AUTH_TOKEN = "dda8494bc146458ba2c7487033ba9b58";

    //获取时间戳
    public static String getTimeStamp() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(date);
    }

    //生成验证码
    public static String getRandom() {
        String random = new Random().nextInt(1000000)+"";
        return (random.length() != 6) ? getRandom():random;
    }

    //Md5加密
    public static String getMd5(String...strings){
        StringBuffer result = new StringBuffer();
        if(strings == null || strings.length == 0) {
            return "";
        }else {
            StringBuilder sb = new StringBuilder();
            for(String string : strings) {
                sb.append(string);
            }
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] output = md.digest(sb.toString().getBytes());
                for(byte bt : output) {
                    String hex = Integer.toHexString(bt&0xff);
                    if(hex.length() == 1) {
                        result.append("0" + hex);
                    }else {
                        result.append(hex);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result.toString();
    }

    public static String queryArguments(String sid, String token, String smsContent, String to) {
        String timeStamp = getTimeStamp();
        String md5 = getMd5(ACCOUNT_SID,AUTH_TOKEN,timeStamp);
        String str = "accountSid=" + ACCOUNT_SID + "&smsContent=" + smsContent+"&to="+to+"&timestamp="+timeStamp+"&sig="+md5;
        return str;
    }

    public static String Send_Message_Code(String to){
        String randNum = getRandom();
        String smsContent = "【烈火科技】尊敬的用户，您的验证码为"+ randNum;
        String args = queryArguments(ACCOUNT_SID, AUTH_TOKEN, smsContent, to);
        StringBuilder sb = new StringBuilder();
        OutputStream out = null;
        BufferedReader br = null;

        try {
            URL url = new URL(queryUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            connection.setRequestProperty("Content-type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Charset", "utf-8");
            out = connection.getOutputStream();
            out.write(args.getBytes());
            out.flush();

            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String temp = "";
            while((temp = br.readLine())!=null) {
                sb.append(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(br != null) {
                    br.close();
                }
                if(out != null) {
                    out.close();
                }
            } catch (IOException e2) {
                e2.printStackTrace();
            }

        }
        return randNum;
    }

}
