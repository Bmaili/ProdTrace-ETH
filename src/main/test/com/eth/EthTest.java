package com.eth;

import ch.qos.logback.core.joran.spi.SimpleRuleStore;
import com.alibaba.fastjson.JSONObject;
import com.eth.pojo.BlockchainTransaction;
import com.eth.pojo.DeptPo;
import com.eth.service.FlowService;
import com.eth.solidity.ProductFlowTrace;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.utils.Convert;
import springfox.documentation.spring.web.json.Json;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@SpringBootTest
public class EthTest {
    @Autowired
    private FlowService flowService;
    // @Autowired
    // private Web3j web3j;

    @Test
    public void test0() throws Exception {
        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));
        String address = "0xb75f6b0918E25c51D9b1774BA3C5E5C506E7155a";
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        if(ethGetBalance!=null){
            // 打印账户余额
            System.out.println(ethGetBalance.getBalance());
            // 将单位转为以太，方便查看
            System.out.println(Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER));
        }
    }

    @Test
    public void test() throws Exception {
        DeptPo po = new DeptPo();
        po.setDeptId("23423");
        po.setDeptName("娃哈哈生产责任有限公司");
        po.setRole("1");
        po.setPicture("elavgh://svjslehv,alvhelv//vlhselvb.com");
        String str = JSONObject.toJSONString(po);//将java对象转换为str字符串

        Web3j web3j = Web3j.build(new HttpService("http://127.0.0.1:7545"));
        // 填入刚才部署后打印出来的合约地址
        String contractAddress = "0xf2C06639B8FA91837a2fb954119b43388E4376e5";
        // 获取第一个账户
        String minerBaseAccount = web3j.ethAccounts().send().getAccounts().get(0);
        System.out.println("minerBaseAccount:" + minerBaseAccount);
        Credentials credentials = Credentials.create(minerBaseAccount);
        // 合约对象
        // SimpleStorage simpleStorage = new SimpleStorage(contractAddress, web3j, credentials, Contract.GAS_PRICE, Contract.GAS_PRICE);
        ProductFlowTrace simpleStorage = new ProductFlowTrace(contractAddress, web3j, credentials, BigInteger.valueOf(0), BigInteger.valueOf(3000000));
        TransactionReceipt send = simpleStorage.addTrace("2233",str).send();
        // System.out.println(send);
        List list = simpleStorage.getTraceInfo("2233").send();
        System.out.println(list);

    }

    @Test
    public void test2() throws Exception {
        DeptPo po = new DeptPo();
        po.setDeptId("23423");
        po.setDeptName("娃哈哈生产责任有限公司");
        po.setPicture("elavgh://svjslehv,alvhelv//vlhselvb.com");
        String str = JSONObject.toJSONString(po);//将java对象转换为str字符串
        System.out.println(str);
        DeptPo deptPo = JSONObject.parseObject(str, DeptPo.class);
        System.out.println(deptPo);
    }
}