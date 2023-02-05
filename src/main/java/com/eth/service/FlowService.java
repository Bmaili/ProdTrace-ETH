package com.eth.service;

import com.eth.pojo.BlockchainTransaction;

import java.io.IOException;

public interface FlowService {
      BlockchainTransaction process(BlockchainTransaction trx) throws IOException;

}
