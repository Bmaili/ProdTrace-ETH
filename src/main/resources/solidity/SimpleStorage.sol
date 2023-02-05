pragma solidity ^0.7.0;
pragma experimental ABIEncoderV2;
contract SimpleStorage {
   string[] traceDatas;//溯源信息

     function addTrace(string memory traceXml)public returns(bool){
        traceDatas.push(traceXml);
        return true;
    }

    //查看溯源信息
    function getTraceInfo()public view returns(string[] memory){
        return traceDatas;
    }
}