package com.eth.ethereum;

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
    public static final String BINARY = "608060405234801561001057600080fd5b50600280546001600160a01b0319163317905561064e806100326000396000f3fe608060405234801561001057600080fd5b50600436106100415760003560e01c80638da5cb5b14610046578063c8bb457414610064578063ca4bd8e214610084575b600080fd5b61004e6100a4565b60405161005b91906104cd565b60405180910390f35b610077610072366004610424565b6100b3565b60405161005b9190610581565b6100976100923660046103e9565b6101a3565b60405161005b9190610521565b6002546001600160a01b031681565b6002546000906001600160a01b031633146100e95760405162461bcd60e51b81526004016100e0906105b9565b60405180910390fd5b6000836040516100f991906104b1565b908152604051602091819003820190208054600181018255600091825290829020845161012e939190920191908501906102d8565b506001808460405161014091906104b1565b908152604051908190036020018120805492151560ff19909316929092179091557ff6b683c0a9b15e631d9784d3cc4eab8c997068b478b1d1369adb3c1596e50eee90610192903390869086906104e1565b60405180910390a150600192915050565b60606001826040516101b591906104b1565b9081526040519081900360200190205460ff166101e45760405162461bcd60e51b81526004016100e09061058c565b6000826040516101f491906104b1565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020016000905b828210156102cd5760008481526020908190208301805460408051601f60026000196101006001871615020190941693909304928301859004850281018501909152818152928301828280156102b95780601f1061028e576101008083540402835291602001916102b9565b820191906000526020600020905b81548152906001019060200180831161029c57829003601f168201915b505050505081526020019060010190610222565b505050509050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061031957805160ff1916838001178555610346565b82800160010185558215610346579182015b8281111561034657825182559160200191906001019061032b565b50610352929150610356565b5090565b5b808211156103525760008155600101610357565b600082601f83011261037b578081fd5b813567ffffffffffffffff80821115610392578283fd5b604051601f8301601f1916810160200182811182821017156103b2578485fd5b6040528281529250828483016020018610156103cd57600080fd5b8260208601602083013760006020848301015250505092915050565b6000602082840312156103fa578081fd5b813567ffffffffffffffff811115610410578182fd5b61041c8482850161036b565b949350505050565b60008060408385031215610436578081fd5b823567ffffffffffffffff8082111561044d578283fd5b6104598683870161036b565b9350602085013591508082111561046e578283fd5b5061047b8582860161036b565b9150509250929050565b6000815180845261049d8160208601602086016105e8565b601f01601f19169290920160200192915050565b600082516104c38184602087016105e8565b9190910192915050565b6001600160a01b0391909116815260200190565b6001600160a01b038416815260606020820181905260009061050590830185610485565b82810360408401526105178185610485565b9695505050505050565b6000602080830181845280855180835260408601915060408482028701019250838701855b8281101561057457603f19888603018452610562858351610485565b94509285019290850190600101610546565b5092979650505050505050565b901515815260200190565b60208082526013908201527274726163654964206e6f74206578697374732160681b604082015260600190565b602080825260159082015274796f75206172656e277420746865206f776e65722160581b604082015260600190565b60005b838110156106035781810151838201526020016105eb565b83811115610612576000848401525b5050505056fea2646970667358221220757b2468c8c2238579ed2267c3a4012a07552519b39b037172d3c5c158350d0664736f6c63430007010033";

    public static final String FUNC_ADDTRACE = "addTrace";

    public static final String FUNC_GETTRACEINFO = "getTraceInfo";

    public static final String FUNC_OWNER = "owner";

    public static final Event NEWTRACE_EVENT = new Event("NewTrace", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Utf8String>() {}));
    ;

    @Deprecated
    protected ProductFlowTrace(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
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

    public RemoteFunctionCall<String> owner() {
        final org.web3j.abi.datatypes.Function function = new org.web3j.abi.datatypes.Function(FUNC_OWNER, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
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

    public static RemoteCall<ProductFlowTrace> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ProductFlowTrace.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ProductFlowTrace> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ProductFlowTrace.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
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
