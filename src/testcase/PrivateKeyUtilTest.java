package testcase;

import com.vhklabs.ecdsa.utils.PrivateKeyUtil;

public class PrivateKeyUtilTest {
    public static void main(String[] args) {
        testgetBtcMainNetAddress();
    }

    public static void testgetBtcMainNetAddress(){
        //私钥 18e14a7b6a307f426a94f8114701e7c8e774e7f9a47e2c2035db29a206321725
        //地址 1PMycacnJaSqwwJqjawXBErnLsZ7RkXUAs
        //3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac
        //1A41S6dgUGN5jge1kZdpmB7vTY929fRQpo
        System.out.println(PrivateKeyUtil.getBtcMainNetAddress("305af9a6057ebeee969dab8f57ca357d2f61a6ee11340fb0879dc76ac1654968"));
        //私钥 3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac
        //地址 mpZxj9ifHHoLWo7dU8cCb6LFKXjiyqRwTj
        System.out.println(PrivateKeyUtil.getBtcTestNetAddress("3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac"));
        //私钥 3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac
        //地址 0xFCD261DB0F70ff4114EFD5Fb1FfA4147Ce8563AD
        System.out.println(PrivateKeyUtil.getEthereumAddress("3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac"));
    }
}
