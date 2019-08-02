package com.vhklabs.ecdsa.utils;

import org.bouncycastle.crypto.digests.RIPEMD160Digest;
import org.bouncycastle.jcajce.provider.digest.Keccak;

import java.security.MessageDigest;

public class HashUtil {
    /**
     * double SHA-256
     * @param str
     * @return
     */
    public static String dhash(String str){
        return hash(hash(HEX.encode(str)));
    }
    /**
     * SHA-256
     * @author William Liu
     * @param str
     * @return
     */
    public static String hash(String str){
        return hash(HEX.encode(str));
    }
    public static String hash(byte[] str){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(str);
            encodestr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodestr;
    }
    public static String getSHA(byte[] str,String pattern){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance(pattern);
            messageDigest.update(str);
            encodestr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    public static String getSHA(String str,String pattern){
        MessageDigest messageDigest;
        String encodestr = "";
        try {
            messageDigest = MessageDigest.getInstance(pattern);
            messageDigest.update(HEX.encode(str));
            encodestr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    public static String keccak256String(String hexInput) {
        byte[] bytes = hexInput.getBytes();
        byte[] result = keccak256(bytes);
        return HEX.decode(result);
    }

    /**
     * SHA3标准
     * @param hexInput
     * @return
     */
    public static String keccak256(String hexInput) {
        byte[] bytes = HEX.encode(hexInput);
        byte[] result = keccak256(bytes);
        return HEX.decode(result);
    }

    public static byte[] keccak256(byte[] input) {
        return keccak256(input, 0, input.length);
    }
    public static byte[] keccak256(byte[] input, int offset, int length) {
        Keccak.DigestKeccak kecc = new Keccak.Digest256();
        kecc.update(input, offset, length);
        return kecc.digest();
    }


    public static String getRipeMD160(byte[] str){

        RIPEMD160Digest digest = new RIPEMD160Digest();
        String encodestr = "";
        try {
            digest.update(str,0,str.length);
            byte[] out = new byte[20];
            digest.doFinal(out, 0);
            encodestr = byte2Hex(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodestr;
    }

    /**
     * 仅能用在字符串的hash上，16进制字符串勿用
     * @param str
     * @return
     */
    public static String getRipeMD160(String str){
        RIPEMD160Digest digest = new RIPEMD160Digest();
        String encodestr = "";
        try {
            byte[] strByte = str.getBytes("UTF-8");
            digest.update(strByte,0,strByte.length);
            byte[] out = new byte[20];
            digest.doFinal(out, 0);
            encodestr = byte2Hex(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodestr;
    }




    private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }
}
