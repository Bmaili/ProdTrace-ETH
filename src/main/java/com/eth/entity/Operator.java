package com.eth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Operator implements Serializable {
    private String operatorId;// 账号ID

    private String operatorName;// 操作员姓名

    private String deptId;//所属企业Id
    private String deptName;//所属企业名称

    private String chineseId; // 身份证号

    private String role;//角色Id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String phone;
    private String email;

    private String sex;//(0女,1男）

    private String status;//(0正常,1停用)

    private String avatar;
}
