package testcase;

import com.vhklabs.ecdsa.utils.BitcoinUtil;

/**
 * 非标准测试案例
 * @author William Liu
 */
public class BitcoinUtilTest {
    public static void main(String[] args) {
        testaddress2scriptpubkey();
    }

    public static void testaddress2scriptpubkey(){
        //地址 mpZxj9ifHHoLWo7dU8cCb6LFKXjiyqRwTj
        //scriptpubkey 76a914634abdbbc6b4156aded18458d3dcc09c65ad658488ac
        //地址 2NBd9iigNW7P7QmFhz61G6vZnTRcXS1Ys1H
        //scriptpubkey a914c997da2b67f597cbc6a0512e0768bacc8658546287
        System.out.println(BitcoinUtil.address2scriptpubkey("2NBd9iigNW7P7QmFhz61G6vZnTRcXS1Ys1H"));
    }

    public static void testbtc2HEX(){
        double satoshi1 = 0.00000001;
        double satoshi2 = 0.00000055;
        double satoshi3 = 1;
        double satoshi4 = 50;
        double satoshi5 = 500.00008;
        double satoshi6 = 21000000;
        System.out.println(BitcoinUtil.btc2HEX(satoshi1));
        System.out.println(BitcoinUtil.btc2HEX(satoshi2));
        System.out.println(BitcoinUtil.btc2HEX(satoshi3));
        System.out.println(BitcoinUtil.btc2HEX(satoshi4));
        System.out.println(BitcoinUtil.btc2HEX(satoshi5));
        System.out.println(BitcoinUtil.btc2HEX(satoshi6));
    }

    public static void testsatoshi2HEX(){
        long satoshi1 = 1;
        long satoshi2 = 55;
        long satoshi3 = 100000000; // 1 btc
        long satoshi4 = 5000000000l;// 50 btc
        long satoshi5 = 50000008000l;
        long satoshi6 = 2100000000000000l; //2100万btc
        System.out.println(BitcoinUtil.satoshi2HEX(satoshi1));
        System.out.println(BitcoinUtil.satoshi2HEX(satoshi2));
        System.out.println(BitcoinUtil.satoshi2HEX(satoshi3));
        System.out.println(BitcoinUtil.satoshi2HEX(satoshi4));
        System.out.println(BitcoinUtil.satoshi2HEX(satoshi5));
        System.out.println(BitcoinUtil.satoshi2HEX(satoshi6));
    }
}
