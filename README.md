# BPIKs
Blockchain Protocol Implements Kits,区块链协议实现参考工具包。

> 助力开发者研发bitcoin,ethereum等公链协议。
>
> Powered by VHKlabs(北京维港区块链科技有限公司区块链协议实验室)



#### 目的

我们旨在给开发者最简洁的实现，方便快速验证协议开发中遇到的问题，我们尽量减少其他第三方的依赖给开发者产生疑惑。

我们劲量将方法写成静态，这样不去依赖其他方法，这样你可以参考只需要在一个方法中看清整个流程处理脉络，方便快速验证自己的协议处理思路。



#### 功能描述

- [secp256k1](https://en.bitcoin.it/wiki/Secp256k1)
- [ECDSA sign & verify](https://en.bitcoin.it/wiki/ECDSA)
- 私钥生成BTC主网，测试网地址，ETH地址
- 公钥压缩非压缩格式化序列化输出
- 两次SHA-256方法
- 以太坊SHA3（ *Keccak*-*256*）方法
- RipeMD160
- BTC DER-encoded 序列化
- ETH RLP 序列化
- BTC地址转换成Script
- big endian&little endian
- BTC raw transaction 构建及签名



#### 代码描述

com.vhklabs.ecdsa.utils.BitcoinUtil.java    bitcoin协议构建工具

```java
String getTxIndex(int index)			unspend tx索引序列化
String getTxNumber(int num)			inputs或outputs数量序列化
String getLength(int num)			长度数量序列化
String satoshi2HEX(long satoshis)			btc数量序列化
String btc2HEX(double btc)			btc数量序列化
String littleEndian(String hex)			返回little endian(低字节序)
String derSignature(String[] signature)			DER-encoded signature
String address2scriptpubkey(String to)			地址解析成OPCODE Script	
```

com.vhklabs.ecdsa.rlp.*   							ethereum rlp

com.vhklabs.ecdsa.utils.HashUtil.java       hash工具

com.vhklabs.ecdsa.ECDSAcore.java           椭圆曲线私钥公钥签名验证

```
String[] sign(String message,String privateKey)		签名
String[] formatSign(String[] signature)		签名DER-encoded标准
boolean isValidSignature(BigInteger r,BigInteger s)		验证签名正确性，兼容Ethereum,符合BIP0062
void verify(String message,String rS,String sS,Point publicKeyPoint)		验证
Point fastMultiply(BigInteger d)		椭圆曲线快速乘法
Point add(Point pointG,Point pointQ)		椭圆曲线加法
Point times2(Point pointG)		椭圆曲线乘法
boolean inPointOnCurve(Point point)		判断坐标点是否在椭圆曲线上
Point fastMultiplyWithPoint(BigInteger d,Point pointG)		椭圆曲线非G点快速乘法
```

com.vhklabs.ecdsa.Point.java					  椭圆曲线点坐标

com.vhklabs.ecdsa.BitcoinTransaction.java	 bitcoin raw transaction build&sign

com.vhklabs.ecdsa.utils.PrivateKeyUtil.java     私钥工具

```java
String getEthereumAddress(String priv)		获取以太坊地址 EIP55
String getBtcMainNetAddress(String priv)		获取btc主网地址
String getBtcTestNetAddress(String priv)		获取btc测试网地址
String getUnCompressedPublicKey(Point publicKey)		转换公钥为非压缩格式输出
String getCompressedPublicKey(Point publicKey)		转换公钥为压缩格式输出		
```



#### 代码示例

##### 对一笔UTXO进行转账

```properties
私钥：3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac
对应btc地址：mpZxj9ifHHoLWo7dU8cCb6LFKXjiyqRwTj
UTXO：3072b8142b91a1028fe8e27be1b29bef9c87b6192c436d6271cd8c3fc8fff3ea
UTXO index:0
转账到比特币地址：2NBd9iigNW7P7QmFhz61G6vZnTRcXS1Ys1H
转账数目：0.0071 btc
```

代码(BitcoinTransactionTest.java)

```java
ECDSAcore acore = new ECDSAcore();
String priv = "3f40fe1c86e3ebcabbd6edd01956e15528639cfe93ae060b73c7cea30a003dac";
String pubkey = PrivateKeyUtil.getCompressedPublicKey(acore.fastMultiply(new BigInteger(priv,16)));
String[] unspent = new String[]{"3072b8142b91a1028fe8e27be1b29bef9c87b6192c436d6271cd8c3fc8fff3ea","0"};
String from = "mpZxj9ifHHoLWo7dU8cCb6LFKXjiyqRwTj";
String[] to = new String[]{"2NBd9iigNW7P7QmFhz61G6vZnTRcXS1Ys1H","0.0071"};
System.out.println(BitcoinTransaction.sendFund(unspent,from,to,pubkey,priv));
```

控制台得到签好名的RAW Transaction

```
0100000001eaf3ffc83f8ccd71626d432c19b6879cef9bb2e17be2e88f02a1912b14b87230000000006a47304402200f6723264b9015d371029da9e00b440af67b920e70bbc8427b68baa4c76a7b15022070a0fd275cf7dac3f337d82d923d400f586257937610a600edb621dbc48aa23001210278f9834dca437a6576e68ef94d01c0c330c09b85f419c84a2c49543c7ae80acfffffffff0170d50a000000000017a914c997da2b67f597cbc6a0512e0768bacc865854628700000000
```

将RAW tx在比特币网络广播，得到txid

```
71e28f2dc513c977f2d3787a6b9df956db25ec2e8830f17f1dde7c13feb53875
```

通过区块链浏览器可以查看转账详情

```http
https://live.blockcypher.com/btc-testnet/tx/71e28f2dc513c977f2d3787a6b9df956db25ec2e8830f17f1dde7c13feb53875
```



#### 项目依赖

除依赖bcprov-ext-jdk15on-161.jar  使用org.bouncycastle.crypto的RIPEMD160Digest和Keccak外，基本都是纯java实现。你可以不依赖该包，将RIPEMD160和Keccak-256自己原生实现。



#### 测试用例

```java
com.vhklabs.ecdsa.testcase 包中为各种方法的测试用例
```



#### 下一步计划

- 已实现部分rust,php,python等实现版本
- 零知识证明协议实现

