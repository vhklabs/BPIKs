package com.vhklabs.ecdsa.utils;

import java.math.BigInteger;

/**
 * bitcoin协议构建工具包
 * @author William Liu
 */
public class BitcoinUtil {
    public static String TRANSACTION_VERSION_1 = "01000000";
    public static String TRANSACTION_MARKER = "00";
    public static String TRANSACTION_FLAG = "01";
    public static String TRANSACTION_OUTPUT_INDEX_0 = "00000000";
    public static String TRANSACTION_OUTPUT_INDEX_1 = "01000000";
    public static String TRANSACTION_OUTPUT_INDEX_2 = "02000000";
    public static String TRANSACTION_OUTPUT_INDEX_3 = "03000000";
    public static String TRANSACTION_OUTPUT_INDEX_4 = "04000000";
    public static String TRANSACTION_D_ZERO = "00";
    public static String TRANSACTION_EIGHT_F = "ffffffff";
    public static String TRANSACTION_EIGHT_ZERO = "00000000";
    public static String TRANSACTION_HASH_TYPE_1 = "01000000";

    /**
     * 输入output index
     * @author William Liu
     * @param index
     * @return
     */
    public static String getTxIndex(int index){
        int len = 8;
        String hex = Integer.toHexString(index);
        if(hex.length() == 1 || hex.length() == 3 || hex.length() == 5 || hex.length() == 7){
            hex = "0"+hex;
        }

        return hex = hex+String.format("%1$0"+(len-hex.length())+"d",0);
    }

    /**
     * 输入inputs或outputs数量
     * @author William Liu
     * @param num
     * @return
     */
    public static String getTxNumber(int num){
        String hex = Integer.toHexString(num);
        if(hex.length() == 1){
            hex = "0"+hex;
        }

        return hex;
    }

    /**
     * 输入长度
     * @author William Liu
     * @param num
     * @return
     */
    public static String getLength(int num){
        String hex = Integer.toHexString(num/2);
        if(hex.length() == 1){
            hex = "0"+hex;
        }

        return hex;
    }

    /**
     * btc数量标准化输出
     * @author William Liu
     * @param satoshis
     * @return
     */
    public static String satoshi2HEX(long satoshis){
        int len = 16;
        String hex = Long.toHexString(satoshis);
        if(hex.length() % 2 != 0){
            hex = "0"+hex;
        }

        hex = littleEndian(hex);
        return hex = hex+String.format("%1$0"+(len-hex.length())+"d",0);
    }

    /**
     * btc数量标准化输出
     * @author William Liu
     * @param btc
     * @return
     */
    public static String btc2HEX(double btc){
        int len = 16;
        String hex = new BigInteger(String.format("%.0f",btc * 100000000)).toString(16);
        if(hex.length() % 2 != 0){
            hex = "0"+hex;
        }

        hex = littleEndian(hex);
        return hex = hex+String.format("%1$0"+(len-hex.length())+"d",0);
    }

    /**
     * 返回little endian(低字节序)
     * @author William Liu
     * @param hex
     * @return
     */
    public static String littleEndian(String hex){
        StringBuilder stringBuilder = new StringBuilder();
        int i = hex.length();
        while (i>0){
            stringBuilder.append(hex.substring(i-2,i));
            i = i -2;
        }
        return  stringBuilder.toString();
    }

    /**
     * DER-encoded signature
     * see https://github.com/bitcoin/bips/blob/master/bip-0062.mediawiki#DER_encoding
     * @param signature
     * @return
     */
    public static String derSignature(String[] signature){
        StringBuilder stringBuilder= new StringBuilder();
        stringBuilder.append("30440220");
        stringBuilder.append(signature[0]);
        stringBuilder.append("0220");
        stringBuilder.append(signature[1]);
        stringBuilder.append("01");
        return stringBuilder.toString();
    }

    /**
     * 地址解析成OPCODE
     * 6f和c4分别表示测试网派p2pkh和p2sh,主网分别是00和05
     * see https://en.bitcoin.it/wiki/Address
     * @author William Liu
     * @param to
     * @return
     */
    public static String address2scriptpubkey(String to) {
        String opcode = HEX.decode(Base58.decode(to));
        opcode = opcode.substring(0,42);
        if("6f".equals(opcode.substring(0,2))){
            opcode = "76a914"+opcode.substring(2,42)+"88ac";
        }else if ("c4".equals(opcode.substring(0,2))){
            opcode = "a914"+opcode.substring(2,42)+"87";
        }else if("00".equals(opcode.substring(0,2))){
            opcode = "76a914"+opcode.substring(2,42)+"88ac";
        }else if ("05".equals(opcode.substring(0,2))){
            opcode = "a914"+opcode.substring(2,42)+"87";
        }
        return opcode;
    }
}
