package com.eth.ethereum;

import com.alibaba.fastjson.JSONObject;
import com.eth.solidity.ProductFlowTrace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class EthUtils implements InitializingBean {
    @Value("${ETH.client-address}")
    private String clientAddress;

    @Value("${ETH.contract-address}")
    private String contractAddress;

    private ProductFlowTrace productFlowTrace;

    @Override
    public void afterPropertiesSet(){
        // 获取第一个账户
        String minerBaseAccount = null;
        Web3j web3j = null;
        try {
            web3j = Web3j.build(new HttpService(clientAddress));
            minerBaseAccount = web3j.ethAccounts().send().getAccounts().get(0);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        Credentials credentials = Credentials.create(minerBaseAccount);
        // 合约对象
        productFlowTrace = new ProductFlowTrace(contractAddress, web3j, credentials, BigInteger.valueOf(0), BigInteger.valueOf(3000000));
    }


    public  boolean uploadToBlock(String traceId, Object data)  {

            String jsonStr = JSONObject.toJSONString(data);
        try {
            TransactionReceipt send = productFlowTrace.addTrace(traceId, jsonStr).send();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            throw new RuntimeException(e);
        }
        return true;
    }

    public List downloadFromBlock(String traceId) {
        List jsonList = new ArrayList();
        try {
            jsonList = productFlowTrace.getTraceInfo(traceId).send();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return jsonList;
    }


}
