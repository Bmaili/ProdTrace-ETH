package com.eth;

import com.eth.ethereum.EthUtils;
import com.eth.service.FlowService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.utils.Convert;
import java.math.BigInteger;
import java.util.List;

@SpringBootTest
public class EthTest {
    @Autowired
    private FlowService flowService;
    @Autowired
    private EthUtils ethUtils;

    @Test
    public void test0() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        String address = "0x00297723cbcda232705d07db5d7ecc7fa26f2cbb";
        // 获取第一个账户
        // String minerBaseAccount = web3j.ethAccounts().send().getAccounts().get(0);
        // System.out.println("minerBaseAccount:" + minerBaseAccount);
        // Credentials credentials = Credentials.create("",minerBaseAccount);
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        if (ethGetBalance != null) {
            // 打印账户余额
            System.out.println(ethGetBalance.getBalance());
            // 将单位转为以太，方便查看
            System.out.println(Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER));
        }
    }

    @Test
    public void test3() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:8545"));
        Credentials credentials = WalletUtils.loadCredentials("2228921046", "D:\\Desktop\\privateChain\\data\\keystore\\UTC--2023-03-03T04-40-45.513689400Z--73e0535c9cc2da20d2fe85b8503fbc4b56478182");

        // 获取 nonce 值
        EthGetTransactionCount ethGetTransactionCount = web3j
                .ethGetTransactionCount("0x97c7b14932bbbd81b1a55d26b73ede1d48a71c3e", DefaultBlockParameterName.PENDING)
                .sendAsync().get();
        BigInteger nonce = ethGetTransactionCount.getTransactionCount();
        System.out.println(nonce);

        // 构建交易
        RawTransaction etherTransaction = RawTransaction.createEtherTransaction(
                nonce,
                web3j.ethGasPrice().sendAsync().get().getGasPrice(),
                DefaultGasProvider.GAS_LIMIT,
                "0x97c7b14932bbbd81b1a55d26b73ede1d48a71c3e",
                Convert.toWei("0.001", Convert.Unit.ETHER).toBigInteger()
        );
        System.out.println(etherTransaction);
        EthSendTransaction ethSendTransaction = web3j.ethSendRawTransaction("60e278c473b0ce72eb4759078b0e4c2ef8d8f3e3c138ab802cab060458752acc").sendAsync().get();
        System.out.println(ethSendTransaction.getResult());


    }

    @Test
    public void test2() throws Exception {
        List list = ethUtils.downloadFromBlock("123");
        list.forEach(System.out::println);
        boolean sdikgh = ethUtils.uploadToBlock("123", "sdikgh");
        List list2 = ethUtils.downloadFromBlock("123");
        list2.forEach(System.out::println);
    }
}