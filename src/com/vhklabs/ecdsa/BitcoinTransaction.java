package com.vhklabs.ecdsa;

import com.vhklabs.ecdsa.utils.BitcoinUtil;
import com.vhklabs.ecdsa.utils.HashUtil;

import java.util.BitSet;

public class BitcoinTransaction {
    /**
     * bitcoin转账
     * @param unspent
     * @param from
     * @param to
     * @param pubKey
     * @param priv
     * @return
     */
    public static String sendFund(String[] unspent,String from,String[] to,String pubKey,String priv){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BitcoinUtil.TRANSACTION_VERSION_1);
        int unspentNum = unspent.length/2;
        stringBuilder.append(BitcoinUtil.getTxNumber(unspentNum));
        for (int i = 0; i < unspentNum; i++) {
            stringBuilder.append(BitcoinUtil.littleEndian(unspent[i*2]));
            stringBuilder.append(BitcoinUtil.getTxIndex(Integer.parseInt(unspent[i*2+1])));
        }

        String tmp = stringBuilder.toString();

        String scriptpubkey = BitcoinUtil.address2scriptpubkey(from);

        stringBuilder.append(BitcoinUtil.getLength(scriptpubkey.length()));
        stringBuilder.append(scriptpubkey);
        stringBuilder.append(BitcoinUtil.TRANSACTION_EIGHT_F);

        StringBuilder lastStr = new StringBuilder();
        int toNum = to.length/2;
        lastStr.append(BitcoinUtil.getTxNumber(toNum));
        for (int i = 0; i < toNum; i++) {
            lastStr.append(BitcoinUtil.btc2HEX(Double.parseDouble(to[i*2+1])));
            String output = BitcoinUtil.address2scriptpubkey(to[i*2]);
            lastStr.append(BitcoinUtil.getLength(output.length()));
            lastStr.append(output);
        }

        lastStr.append(BitcoinUtil.TRANSACTION_EIGHT_ZERO);
        ECDSAcore acore = new ECDSAcore();
        String toSignStr = stringBuilder.toString()+lastStr+BitcoinUtil.TRANSACTION_HASH_TYPE_1;

        String[] signature = acore.sign(HashUtil.dhash(toSignStr), priv);
        String signatureDER = BitcoinUtil.derSignature(signature);
        String sigpub=BitcoinUtil.getLength(signatureDER.length())+signatureDER+BitcoinUtil.getLength(pubKey.length()) + pubKey;

        return tmp+BitcoinUtil.getLength(sigpub.length())+sigpub+BitcoinUtil.TRANSACTION_EIGHT_F+lastStr.toString();
    }

    /**
     * P2PKH类型钱包Tx构建
     * see https://bitcoin.stackexchange.com/questions/3374/how-to-redeem-a-basic-tx
     * @return
     */
    public static String buildP2PKHTransaction(String[] unspent,String from,String[] to){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BitcoinUtil.TRANSACTION_VERSION_1);
        int unspentNum = unspent.length/2;
        stringBuilder.append(BitcoinUtil.getTxNumber(unspentNum));
        for (int i = 0; i < unspentNum; i++) {
            stringBuilder.append(BitcoinUtil.littleEndian(unspent[i*2]));
            stringBuilder.append(BitcoinUtil.getTxIndex(Integer.parseInt(unspent[i*2+1])));
        }

        String scriptpubkey = BitcoinUtil.address2scriptpubkey(from);

        stringBuilder.append(BitcoinUtil.getLength(scriptpubkey.length()));
        stringBuilder.append(scriptpubkey);
        stringBuilder.append(BitcoinUtil.TRANSACTION_EIGHT_F);

        int toNum = to.length/2;
        stringBuilder.append(BitcoinUtil.getTxNumber(toNum));
        for (int i = 0; i < toNum; i++) {
            stringBuilder.append(BitcoinUtil.btc2HEX(Double.parseDouble(to[i*2+1])));
            String output = BitcoinUtil.address2scriptpubkey(to[i*2]);
            stringBuilder.append(BitcoinUtil.getLength(output.length()));
            stringBuilder.append(output);
        }

        stringBuilder.append(BitcoinUtil.TRANSACTION_EIGHT_ZERO);
        stringBuilder.append(BitcoinUtil.TRANSACTION_HASH_TYPE_1);
        return stringBuilder.toString();
    }

    /**
     * P2PKH类型钱包Tx签名
     * @param signatureDER
     * @param pubKey
     * @return
     */
    public static String signRAWP2PKHTransaction(String signatureDER,String pubKey,String[] unspent,String from,String[] to){
        String sigpub=BitcoinUtil.getLength(signatureDER.length())+signatureDER+BitcoinUtil.getLength(pubKey.length()) + pubKey;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BitcoinUtil.TRANSACTION_VERSION_1);
        int unspentNum = unspent.length/2;
        stringBuilder.append(BitcoinUtil.getTxNumber(unspentNum));
        for (int i = 0; i < unspentNum; i++) {
            stringBuilder.append(BitcoinUtil.littleEndian(unspent[i*2]));
            stringBuilder.append(BitcoinUtil.getTxIndex(Integer.parseInt(unspent[i*2+1])));
        }

        stringBuilder.append(BitcoinUtil.getLength(sigpub.length()));
        stringBuilder.append(sigpub);
        stringBuilder.append(BitcoinUtil.TRANSACTION_EIGHT_F);

        int toNum = to.length/2;
        stringBuilder.append(BitcoinUtil.getTxNumber(toNum));
        for (int i = 0; i < toNum; i++) {
            stringBuilder.append(BitcoinUtil.btc2HEX(Double.parseDouble(to[i*2+1])));
            String output = BitcoinUtil.address2scriptpubkey(to[i*2]);
            stringBuilder.append(BitcoinUtil.getLength(output.length()));
            stringBuilder.append(output);
        }

        stringBuilder.append(BitcoinUtil.TRANSACTION_EIGHT_ZERO);
        return stringBuilder.toString();


//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(BitcoinUtil.TRANSACTION_VERSION_1);
//        stringBuilder.append(BitcoinUtil.getTxNumber(1));
//        stringBuilder.append(BitcoinUtil.littleEndian("51e27efec8973fbaf166197212b09e9adf7318b942da75abd4f2f7238d2ac3dc"));
//        stringBuilder.append(BitcoinUtil.TRANSACTION_OUTPUT_INDEX_0);
//        stringBuilder.append(BitcoinUtil.getLength(sigpub.length()));
//        stringBuilder.append(sigpub);
//        stringBuilder.append(BitcoinUtil.TRANSACTION_EIGHT_F);
//        stringBuilder.append(BitcoinUtil.getTxNumber(1));
//        stringBuilder.append(BitcoinUtil.btc2HEX(0.003));
//        String output = "76a914344a0f48ca150ec2b903817660b9b68b13a6702688ac";
//        stringBuilder.append(BitcoinUtil.getLength(output.length()));
//        stringBuilder.append(output);
//        stringBuilder.append(BitcoinUtil.TRANSACTION_EIGHT_ZERO);
//        return stringBuilder.toString();
    }

    /**
     * P2SH-P2WPKH
     * see https://github.com/bitcoin/bips/blob/master/bip-0143.mediawiki#p2sh-p2wpkh
     * @param prevouts
     * @param outputs
     * @param outputsvalue
     * @param redeemScript
     * @param value
     * @return
     */
    public static String buildP2WPKHSignHash(String prevouts,String outputs,double outputsvalue,String redeemScript,double value){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BitcoinUtil.TRANSACTION_VERSION_1);
        stringBuilder.append(HashUtil.dhash(prevouts));
        stringBuilder.append(HashUtil.dhash(BitcoinUtil.TRANSACTION_EIGHT_F));
        stringBuilder.append(prevouts);
        stringBuilder.append("1976a9");
        stringBuilder.append(redeemScript.substring(2,redeemScript.length()));
        stringBuilder.append("88ac");
        stringBuilder.append(BitcoinUtil.btc2HEX(outputsvalue));
        stringBuilder.append(BitcoinUtil.TRANSACTION_EIGHT_F);
        stringBuilder.append(HashUtil.dhash(BitcoinUtil.btc2HEX(value)+outputs));
        stringBuilder.append(BitcoinUtil.TRANSACTION_EIGHT_ZERO);
        stringBuilder.append(BitcoinUtil.TRANSACTION_HASH_TYPE_1);
        System.out.println(stringBuilder.toString());
        return HashUtil.dhash(stringBuilder.toString());
    }
}
