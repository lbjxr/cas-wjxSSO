package com.lbj.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Component
public class WjxSSOUtil {

    /**
     * 获取当前时间的10位时间戳
     * @return
     */
    public static String getTimestamp(){
        long time1 = System.currentTimeMillis() / 1000;
        return String.valueOf(time1);
    }

    /**
     *根据接口要求，进行sign参数的加密
     * @param appId1
     * @param appKey1
     * @param uid
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String getSign(String appId1,String appKey1,String uid) throws NoSuchAlgorithmException {

        String ts = getTimestamp();
        String sign = appId1 + appKey1 + uid + ts;

        //将原始sign字符串转成字节流
        byte[] signByte = sign.getBytes();

        MessageDigest messageDigest = MessageDigest.getInstance("SHA1");

        byte[] result = messageDigest.digest(signByte);
        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < result.length; i++) {
            stringBuffer.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }

        return "&ts=" + ts + "&sign=" + stringBuffer.toString();
    }

}