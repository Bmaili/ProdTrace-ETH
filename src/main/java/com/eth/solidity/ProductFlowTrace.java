package com.eth.solidity;

import io.reactivex.Flowable;
import io.reactivex.functions.Function;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.6.4.
 */
@SuppressWarnings("rawtypes")
public class ProductFlowTrace extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b506105b0806100206000396000f3fe608060405234801561001057600080fd5b50600436106100365760003560e01c8063c8bb45741461003b578063ca4bd8e214610064575b600080fd5b61004e6100493660046103c9565b610084565b60405161005b9190610512565b60405180910390f35b61007761007236600461038e565b61013f565b60405161005b91906104b2565b600080836040516100959190610456565b90815260405160209181900382019020805460018101825560009182529082902084516100ca9391909201919085019061027d565b50600180846040516100dc9190610456565b908152604051908190036020018120805492151560ff19909316929092179091557ff6b683c0a9b15e631d9784d3cc4eab8c997068b478b1d1369adb3c1596e50eee9061012e90339086908690610472565b60405180910390a150600192915050565b60606001826040516101519190610456565b9081526040519081900360200190205460ff166101895760405162461bcd60e51b81526004016101809061051d565b60405180910390fd5b6000826040516101999190610456565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020016000905b828210156102725760008481526020908190208301805460408051601f600260001961010060018716150201909416939093049283018590048502810185019091528181529283018282801561025e5780601f106102335761010080835404028352916020019161025e565b820191906000526020600020905b81548152906001019060200180831161024157829003601f168201915b5050505050815260200190600101906101c7565b505050509050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f106102be57805160ff19168380011785556102eb565b828001600101855582156102eb579182015b828111156102eb5782518255916020019190600101906102d0565b506102f79291506102fb565b5090565b5b808211156102f757600081556001016102fc565b600082601f830112610320578081fd5b813567ffffffffffffffff80821115610337578283fd5b604051601f8301601f191681016020018281118282101715610357578485fd5b60405282815292508284830160200186101561037257600080fd5b8260208601602083013760006020848301015250505092915050565b60006020828403121561039f578081fd5b813567ffffffffffffffff8111156103b5578182fd5b6103c184828501610310565b949350505050565b600080604083850312156103db578081fd5b823567ffffffffffffffff808211156103f2578283fd5b6103fe86838701610310565b93506020850135915080821115610413578283fd5b5061042085828601610310565b9150509250929050565b6000815180845261044281602086016020860161054a565b601f01601f19169290920160200192915050565b6000825161046881846020870161054a565b9190910192915050565b6001600160a01b03841681526060602082018190526000906104969083018561042a565b82810360408401526104a8818561042a565b9695505050505050565b6000602080830181845280855180835260408601915060408482028701019250838701855b8281101561050557603f198886030184526104f385835161042a565b945092850192908501906001016104d7565b5092979650505050505050565b901515815260200190565b60208082526013908201527274726163654964206e6f74206578697374732160681b604082015260600190565b60005b8381101561056557818101518382015260200161054d565b83811115610574576000848401525b5050505056fea26469706673582212204685d40bbce467fe6191a72c8772f9bdf972a7a04941908267e35d81a7dc8d5b64736f6c63430007010033";

    public static final String FUNC_ADDTRACE = "addTrace";

    public static final String FUNC_GETTRACEINFO = "getTraceInfo";

    public static final Event NEWTRACE_EVENT = new Event("NewTrace", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    public ProductFlowTrace(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ProductFlowTrace(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ProductFlowTrace(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ProductFlowTrace(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public List<NewTraceEventResponse> getNewTraceEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(NEWTRACE_EVENT, transactionReceipt);
        ArrayList<NewTraceEventResponse> responses = new ArrayList<NewTraceEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            NewTraceEventResponse typedResponse = new NewTraceEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.traceId = (String) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.traceJson = (String) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<NewTraceEventResponse> newTraceEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new Function<Log, NewTraceEventResponse>() {
            @Override
            public NewTraceEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(NEWTRACE_EVENT, log);
                NewTraceEventResponse typedResponse = new NewTraceEventResponse();
                typedResponse.log = log;
                typedResponse.sender = (String) eventValues.getNonIndexedValues().get(0).getValue();
                typedResponse.traceId = (String) eventValues.getNonIndexedValues().get(1).getValue();
                typedResponse.traceJson = (String) eventValues.getNonIndexedValues().get(2).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<NewTraceEventResponse> newTraceEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(NEWTRACE_EVENT));
        return newTraceEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addTrace(String traceId, String traceJson) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(
                FUNC_ADDTRACE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(traceId), 
                new org.web3j.abi.datatypes.Utf8String(traceJson)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<List> getTraceInfo(String traceId) {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_GETTRACEINFO, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(traceId)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Utf8String>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    @Deprecated
    public static ProductFlowTrace load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProductFlowTrace(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ProductFlowTrace load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProductFlowTrace(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ProductFlowTrace load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ProductFlowTrace(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ProductFlowTrace load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ProductFlowTrace(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ProductFlowTrace> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ProductFlowTrace.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ProductFlowTrace> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ProductFlowTrace.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ProductFlowTrace> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ProductFlowTrace.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ProductFlowTrace> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ProductFlowTrace.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class NewTraceEventResponse extends BaseEventResponse {
        public String sender;

        public String traceId;

        public String traceJson;
    }
}
