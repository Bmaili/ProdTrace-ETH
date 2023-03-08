package com.eth.mapper;

import com.eth.pojo.ConfigPo;
import com.eth.pojo.LoginUserPo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ConfigMapper {
    @Select("select * from config")
    ConfigPo getConfig();

    @Update("update config set contract_address = #{contractAddress}")
    int updateContractAddress( String contractAddress);
}
