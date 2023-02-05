package com.eth.service.impl;

import com.eth.pojo.BlockchainTransaction;
import com.eth.service.FlowService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;

@Slf4j
@Service
public class FlowServiceImpl implements FlowService {
    @Autowired
    private Web3j web3j;

    private String fromAddr = "0xd4B4dE1c00E5041B73858a34748c72352528C236";
    private String fromPrivateKey = "872bc8c6c80452951761412d886b8c925ba0dd6d8fbc6dca14044fddcff843a7";
    private String toAddr = "0x793DB15A39dD1d848B9E32C11Dc20Ab0BB40F04C";

    public void testt() throws IOException {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545/"));
        String address = "0xd4B4dE1c00E5041B73858a34748c72352528C236";
        EthGetBalance ethGetBalance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send();
        if(ethGetBalance!=null){
            // 打印账户余额
            System.out.println(ethGetBalance.getBalance());
            // 将单位转为以太，方便查看
            System.out.println(Convert.fromWei(ethGetBalance.getBalance().toString(), Convert.Unit.ETHER));
        }
    }


    @Override
    public BlockchainTransaction process(BlockchainTransaction trx) throws IOException {
        EthAccounts accounts = web3j.ethAccounts().send();
        EthGetTransactionCount transactionCount = web3j.ethGetTransactionCount(accounts.getAccounts().get(trx.getFromId()), DefaultBlockParameterName.LATEST).send();
        Transaction transaction = Transaction.createEtherTransaction(accounts.getAccounts().get(trx.getFromId()), transactionCount.getTransactionCount(), BigInteger.valueOf(trx.getValue()), BigInteger.valueOf(21_000), accounts.getAccounts().get(trx.getToId()), BigInteger.valueOf(trx.getValue()));
        EthSendTransaction response = web3j.ethSendTransaction(transaction).send();
        if (response.getError() != null) {
            trx.setAccepted(false);
            return trx;
        }
        trx.setAccepted(true);
        String txHash = response.getTransactionHash();
        log.info("Tx hash: {}", txHash);
        trx.setId(txHash);
        EthGetTransactionReceipt receipt = web3j.ethGetTransactionReceipt(txHash).send();
        if (receipt.getTransactionReceipt().isPresent()) {
            log.info("Tx receipt: {}", receipt.getTransactionReceipt().get().getCumulativeGasUsed().intValue());
        }
        return trx;
    }
}
