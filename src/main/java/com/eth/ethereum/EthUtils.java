package com.eth.ethereum;

import com.alibaba.fastjson.JSONObject;
import com.eth.mapper.ConfigMapper;
import com.eth.pojo.ConfigPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.util.StringUtils;
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
@DependsOn("configMapper")
public class EthUtils implements InitializingBean {
    @Autowired
    private ConfigMapper configMapper;

    @Value("${ETH.http-service}")
    private String HTTP_SERVICE;

    @Value("${ETH.keystore-folder-path}")
    private String KEYSTORE_FOLDER_PATH;

    @Value("${ETH.keystore-password}")
    private String KEYSTORE_PASSWORD;

    private Web3j web3j;
    private Credentials credentials;
    private String accountAddress;
    private String contractAddress;

    private ProductFlowTrace productFlowTrace;

    @Override
    public void afterPropertiesSet() throws CipherException, IOException {
        File file = new File(KEYSTORE_FOLDER_PATH);
        File[] files = file.listFiles();

        if (files == null || files.length == 0) {
            throw new RuntimeException("keystore-folder-path配置项有误！");
        }
        String[] split = files[0].getName().split("--");
        accountAddress = split[split.length - 1];

        web3j = Web3j.build(new HttpService(HTTP_SERVICE));
        credentials = WalletUtils.loadCredentials(KEYSTORE_PASSWORD, files[0]);

        ConfigPo config = configMapper.getConfig();
        String configContractAddress = config.getContractAddress();
        if (!StringUtils.hasText(configContractAddress)) {//没有数据，为第一次部署合约
            log.info("未查询到合约地址，新合约部署中...");
            contractAddress = this.deployCont();
            //更新config
            configMapper.updateContractAddress(contractAddress);
            log.info("合约部署完成！合约地址: \n" + contractAddress);
        } else {
            log.info("查询到已有合约！合约地址: \n" + configContractAddress);
            contractAddress = configContractAddress;
        }

        // 合约对象
        productFlowTrace = new ProductFlowTrace(contractAddress, web3j, credentials, BigInteger.valueOf(999999), BigInteger.valueOf(3000000));
    }


    public boolean uploadToBlock(String traceId, Object data) {
        String jsonStr = JSONObject.toJSONString(data);
        TransactionReceipt send = null;
        try {
            send = productFlowTrace.addTrace(traceId, jsonStr).send();
            log.info(send.toString());
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

    private String deployCont() {
        try {
            // 部署合约
            ProductFlowTrace deployContract = ProductFlowTrace.deploy(web3j, credentials, BigInteger.valueOf(999999), BigInteger.valueOf(3000000)).send();

            if (deployContract.isValid()) {// 判断部署的合约是否可用
                return deployContract.getContractAddress();
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.error("合约部署失败", e);
        }
        return null;
    }

}
