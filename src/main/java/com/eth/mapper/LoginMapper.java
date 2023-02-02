package com.eth.mapper;


import com.eth.pojo.LoginUserPo;
import org.apache.ibatis.annotations.*;


@Mapper
public interface LoginMapper {
    @Select("select * from login where operator_id=#{username}")
    LoginUserPo getLoginUser(String username);

    @Insert("insert into login (operator_id,  password)values (#{operatorId}, #{password})")
    int addLoginUser( String operatorId, String password);

    //INSERT INTO operator_info set operator_id='admin',operator_name='超级管理员',create_time=NOW();
    // @Insert("INSERT INTO operator_info set operator_id=#{operatorId},operator_name= #{operatorName},create_time=NOW()")
    // @Options(useGeneratedKeys = true, keyProperty = "operator_id", keyColumn = "operator_id")
    // int addUser(User operator);

}
