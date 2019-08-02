package testcase;

import com.vhklabs.ecdsa.BitcoinTransaction;
import com.vhklabs.ecdsa.ECDSAcore;
import com.vhklabs.ecdsa.utils.PrivateKeyUtil;
import com.vhklabs.ecdsa.utils.BitcoinUtil;
import com.vhklabs.ecdsa.utils.HashUtil;

import java.math.BigInteger;

public class BitcoinTransactionTest {
    public static void main(String[] args) {
        testsendFund();
    }

    /**
     * 成功转账btc
     * https://live.blockcypher.com/btc-testnet/tx/71e28f2dc513c977f2d3787a6b9df956db25ec2e8830f17f1dde7c13feb53875/
     */
    public static void testsendFund(){
        ECDSAcore acore = new ECDSAcore();
        String priv = "3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac";
        String pubkey = PrivateKeyUtil.getCompressedPublicKey(acore.fastMultiply(new BigInteger(priv,16)));
        String[] unspent = new String[]{"3072b8142b91a1028fe8e27be1b29bef9c87b6192c436d6271cd8c3fc8fff3ea","0"};
        String from = "mpZxj9ifHHoLWo7dU8cCb6LFKXjiyqRwTj";
        String[] to = new String[]{"2NBd9iigNW7P7QmFhz61G6vZnTRcXS1Ys1H","0.0071"};
        System.out.println(BitcoinTransaction.sendFund(unspent,from,to,pubkey,priv));
    }

    /**
     * 测试成功转账btc
     *
     */
    public static void testbuildP2PKHTransaction(){
        ECDSAcore acore = new ECDSAcore();
        String priv = "3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac";
        String pubkey = PrivateKeyUtil.getCompressedPublicKey(acore.fastMultiply(new BigInteger(priv,16)));


        String[] unspent = new String[]{"7aad62b89902101914d13f36849ac024a8ae2172c6b565a3e0b857cdc2dc9588","1"};
        String from = "mpZxj9ifHHoLWo7dU8cCb6LFKXjiyqRwTj";
        //,"mkHS9ne12qx9pS9VojpwU5xtRd4T7X7ZUt","0.0001"
        String[] to = new String[]{"2NBd9iigNW7P7QmFhz61G6vZnTRcXS1Ys1H","0.0091"};

        String tx = BitcoinTransaction.buildP2PKHTransaction(unspent,from,to);
        System.out.println(tx);
        String dhash = HashUtil.dhash(tx);
        System.out.println(dhash);

        String[] signature = acore.sign(dhash, priv);
        System.out.println(BitcoinTransaction.signRAWP2PKHTransaction(BitcoinUtil.derSignature(signature), PrivateKeyUtil.getCompressedPublicKey(acore.fastMultiply(new BigInteger(priv,16))),unspent,from,to));


    }

    public static void testbuildP2WPKHSignHash(){
        String priv = "3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac";
        String  sigHash = BitcoinTransaction.buildP2WPKHSignHash(BitcoinUtil.littleEndian("d16816f50b30faaf4cbbd6e2e3805c1ce07d39462faeec90ab580ea27ca59ad9"+BitcoinUtil.TRANSACTION_OUTPUT_INDEX_0),"19"+"76a914344a0f48ca150ec2b903817660b9b68b13a6702688ac",0.00998548,"0014634abdbbc6b4156aded18458d3dcc09c65ad6584",0.008);
        System.out.println(sigHash);
        ECDSAcore acore = new ECDSAcore();
        String[] signature = acore.sign(sigHash, priv);
        System.out.println(BitcoinUtil.derSignature(signature));
        System.out.println(BitcoinUtil.littleEndian("d16816f50b30faaf4cbbd6e2e3805c1ce07d39462faeec90ab580ea27ca59ad9"));
        System.out.println(BitcoinUtil.btc2HEX(0.008));
//        System.out.println(BitcoinUtil);
    }
}
