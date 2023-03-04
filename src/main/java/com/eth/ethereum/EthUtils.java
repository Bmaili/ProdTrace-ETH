package com.eth.ethereum;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class EthUtils implements InitializingBean {
    @Value("${ETH.http-service}")
    private String HTTP_SERVICE;

    @Value("${ETH.contract-address}")
    private String CONTRACT_ADDRESS;

    @Value("${eth.keystore-folder-path}")
    private String KEYSTORE_FOLDER_PATH;

    @Value("${eth.keystore-password}")
    private String KEYSTORE_PASSWORD;

    private Web3j web3j;
    private Credentials credentials;
    private String accountAddress;

    private ProductFlowTrace productFlowTrace;

    @Override
    public void afterPropertiesSet() throws CipherException, IOException {
        File file = new File(KEYSTORE_FOLDER_PATH);
        File[] files = file.listFiles();

        assert files != null;
        String[] split = files[0].getName().split("--");
        accountAddress = split[split.length - 1];

        web3j = Web3j.build(new HttpService(HTTP_SERVICE));
        credentials = WalletUtils.loadCredentials(KEYSTORE_PASSWORD, files[0]);

        // 合约对象
        productFlowTrace = new ProductFlowTrace(CONTRACT_ADDRESS, web3j, credentials, BigInteger.valueOf(999999), BigInteger.valueOf(3000000));
    }


    public boolean uploadToBlock(String traceId, Object data) {
        String jsonStr = JSONObject.toJSONString(data);
        TransactionReceipt send = null;
        try {
            send = productFlowTrace.addTrace(traceId, jsonStr).send();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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
            throw new RuntimeException(e);
        }
        return jsonList;
    }


}
