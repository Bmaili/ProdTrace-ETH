pragma solidity >=0.7.0 <0.8.0;
pragma experimental ABIEncoderV2;

contract ProductFlowTrace {
    //因Solidity转Java时,不支持返回结构体类型的函数,故此处皆使用mapping
    //溯源信息,mapping(溯源码=>Json型溯源数据)
    mapping(string=>string[]) traceDatas;
    //溯源码使用标记
    mapping(string=>bool) mark;
    //log
    event NewTrace(address sender, string traceId, string traceJson);

    //添加溯源信息,参数(溯源码,Json溯源数据)
    function addTrace(string memory traceId, string memory traceJson)public returns(bool){
        traceDatas[traceId].push(traceJson);
        mark[traceId] = true;
        emit NewTrace(msg.sender, traceId, traceJson);
        return true;
    }

    //查看溯源信息,参数(溯源码),返回(Json溯源数据)
    function getTraceInfo(string memory traceId)public view returns(string[] memory){
       require(mark[traceId], "traceId not exists!");
       return traceDatas[traceId];
    }
}