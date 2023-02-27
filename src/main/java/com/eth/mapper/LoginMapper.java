package com.eth.mapper;

import com.eth.pojo.LoginUserPo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface LoginMapper {
    @Select("select * from login where operator_id=#{operatorId}")
    LoginUserPo getLoginUser(String operatorId);

    @Insert("insert into login (operator_id,  password)values (#{operatorId}, #{password})")
    int addLoginUser( String operatorId, String password);

    @Update("update login set password = #{password} where operator_id = #{operatorId} ")
    int updateLoginUserPassword( String operatorId, String password);
}
