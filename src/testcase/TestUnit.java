package testcase;

import com.vhklabs.ecdsa.ECDSAcore;
import com.vhklabs.ecdsa.Point;
import com.vhklabs.ecdsa.utils.Base58;
import com.vhklabs.ecdsa.utils.HEX;
import com.vhklabs.ecdsa.utils.HashUtil;

import java.math.BigInteger;

public class TestUnit {
    /**
     * 测试 ECDSA
     * @param args
     */
    public static void main(String[] args) {

//        //check out is satisfy the Bitcoin G point
        ECDSAcore acore = new ECDSAcore();


        Point pointG = new Point(new BigInteger("79BE667EF9DCBBbC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798",16),new BigInteger("483ADA7726A3C4655DA4FBFC0E1108A8FD17B448A68554199C47D08FFB10D4B8",16));
//        System.out.println(acore.inPointOnCurve(pointG));
//
        //this maybe a bitcoin private key
//        String privateKey = "0x79BE667EF9DCBBAC55A06295CE870B07029BFCDB2DCE28D959F2815B16F81798";
//        //so we fast calculate a public key
//        Point r1 = acore.fastMultiply(new BigInteger("2"));
//
//        Point r2 = acore.fastMultiply(new BigInteger("3"));
//        //Point Add
//        System.out.println(acore.add(r1,r2));
//
//        Point r3 = acore.fastMultiply(new BigInteger("5"));
//        System.out.println(r3);
//
//        //TEST ADD AND MULTI
//        Point A = acore.times2(pointG);
//        Point B = acore.add(A,pointG);
//        System.out.println(B.getX().toString(16));
//
        String message = "what's your name";

        //bitcoin sign
//        acore.sign(message,"888",r);

//        acore.verify("what's your name","103118741554904166099415461986422295381639424360161428629979510531057770939640","39963258286450301240790965649009481829365546214028056817606659598007810920669",r);




//        String pkey = "359d88771ebbbdefd2356a805af66b4243ab5ca30bb34fe154a0bd49fc4b9b40";


//       私钥生成比特币地址和以太坊地址
//        String pkey = "ecbcd9838f7f2afa6e809df8d7cdae69aa5dfc14d563ee98e97effd3f6a652f2";
//        Point pk = acore.fastMultiply(new BigInteger("888"));
//        System.out.println(pk.getX().toString(16));
//        String address = acore.publicKey2Address(pk);
//
////        f9a7699410c9f7c85c54aa8fef638a2876c2f7db0dcbe361e3dde8b0825c0fc1
////        c2c699f9e180bb06fda64f05dda055e4462fbea08733f4fdcc1a6886c8983955fe333c1f1f8ee995a2cc54a938826d5ea92af97f3375a82df67209ab97e17441
////        70af5d4076d755d15ac658a28d733b5d96ef2b79
//
//        //Bitcoin Address
//        System.out.println(address);
//        //Ethereum Address
//        System.out.println(pk);
//
//        System.out.println(acore.publicKey2ETHAddress(pk));


//        比特币私钥导入格式
//        System.out.println(acore.bitcoinWIF("Ky1w1yie629vcmHVB1V4jnEZLCSUN14USjyLmi9HMywZqeKFE4at"));

//        构建RAW tx
//        String tx = "00"+"0"+ new BigInteger("20000000000").toString(16)+"0"+ new BigInteger("43092000").toString(16)+"1f633815d413d55ae65494768a8ca287573cee51"+"0"+new BigInteger("1").toString(16)+"c0de";
//        acore.sign(acore.hexStringToBytes(acore.getSHA(RlpEncoder.encode(RlpString.create(acore.hexStringToBytes(tx))),"SHA-256")),"359d88771ebbbdefd2356a805af66b4243ab5ca30bb34fe154a0bd49fc4b9b40",pk);
//        String r = new BigInteger("20694884568225431318140277903774850116350559821322120592748898802466728091894").toString(16);
//        String s = new BigInteger("49140182262409718729404370070372862429887596549518159792606630151456327967607").toString(16);
//        System.out.println(r);
//        System.out.println(s);
//        System.out.println();
//        String txRaw = tx+"2b"+r+s;
//        System.out.println(acore.bytesToHexString(RlpEncoder.encode(RlpString.create(acore.hexStringToBytes(txRaw)))));

//        构建raw
//        List<RlpType> result = new ArrayList<>();
//        result.add(RlpString.create(new BigInteger("15")));  //nonce 0
//        result.add(RlpString.create(new BigInteger("20000000000")));
//        result.add(RlpString.create(new BigInteger("300000")));
//        result.add(RlpString.create(acore.hexStringToBytes("ce31a19193d4b23f4e9d6163d7247243bAF801c3")));
//        result.add(RlpString.create(new BigInteger("99"))); //value
//        result.add(RlpString.create(acore.hexStringToBytes(" ")));
//        System.out.println(acore.bytesToHexString(acore.sha3(RlpEncoder.encode(new RlpList(result)))));
//        acore.signeth(acore.sha3(RlpEncoder.encode(new RlpList(result))),pkey,pk);
//        result.add(RlpString.create(new BigInteger("28"))); //Ropsten net
//        result.add(RlpString.create(new BigInteger("f7c9a35f2f5000c4a39b321eab13ea7db1a4a7a65b4c45cc37014ad951cc8ac4",16)));
//        result.add(RlpString.create(new BigInteger("e344da1d9d9c3f4e5fbb7cab99477537e35818210d71e46a277e03ea19473975",16)));
//        System.out.println(acore.bytesToHexString(RlpEncoder.encode(new RlpList(result))));
        String a = "0100000001be66e10da854e7aea9338c1f91cd489768d1d6d7189f586d7a3613f2a24d5396000000001976a914dd6cce9f255a8cc17bda8ba0373df8e861cb866e88acffffffff0123ce0100000000001976a9142bc89c2702e0e618db7d59eb5ce2f0f147b4075488ac0000000001000000";
        String constru = "01000000b0287b4a252ac05af83d2dcef00ba313af78a3e9c329afa216eb3aa2a7b4613a18606b350cd8bf565266bc352f0caddcf01e8fa789dd8a15386327cf8cabe198db6b1b20aa0fd7b23880be2ecbd4a98130974cf4748fb66092ac4d3ceb1a5477010000001976a91479091972186c449eb1ded22b78e40d009bdf008988ac00ca9a3b00000000feffffffde984f44532e2173ca0d64314fcefe6d30da6f8cf27bafa706da61df8a226c839204000001000000";
        String dsha = HashUtil.getSHA(HashUtil.getSHA(constru,"SHA-256"),"SHA-256");
//        System.out.println(dsha);
//        System.out.println(HashUtil.dhash(constru));

        System.out.println("私钥"+ HEX.decode(Base58.decode("KxqhyxvUNATvLjcQvGU24u6Q72SH98BordnkqgUvMfyxo5ws1wxj")));


        String  siyao = "3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac";
        Point gongyao = acore.fastMultiply(new BigInteger(siyao,16));
        //System.out.println(gongyao);
//        acore.sign("e66b08f94ace20cfb18ca7ff502e08ec7a4c2d8956c4230cb0b726b469a35d57",siyao,gongyao,HashUtil.getSHA(siyao+constru,"SHA-256"));


        String dercode  = "ad0cccc88994f8b2186d74b0b330c516f8103f2a2b1740d47e375412ca5368fb";



//        Point pubkey = new Point(new BigInteger("11db93e1dcdb8a016b49840f8c53bc1eb68a382e97b1482ecad7b148a6909a5c",16),new BigInteger("b2e0eaddfb84ccf9744464f82e160bfa9b8b64f9d4c03f999b8643f656b412a3",16));


        //acore.verify("c37af31116d1b27caf68aae9e3ac82f1477929014d5b917657d0eb49478cb670","3609e17b84f6a7d30c80bfa610b5b4542f32a8a0d5447a12fb1366d7f01cc44a","573a954c4518331561406f90300e8f3358f51928d43c212a8caed02de67eebee",gongyao);


        //acore.verify("64f3b0f4dd2bb3aa1ce8566d220cc74dda9df97d8490cc81d89d735c92e59fb6","47ac8e878352d3ebbde1c94ce3a10d057c24175747116f8288e5d794d12d482f","217f36a485cae903c713331d877c1f64677e3622ad4010726870540656fe9dcb",gongyao);

    }


}
