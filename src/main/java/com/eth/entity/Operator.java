package com.eth.entity;

import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
public class Operator implements Serializable {
    private String operatorId;// 账号ID

    private String operatorName;// 操作员姓名

    private String deptId;//所属企业Id

    private String chineseId; // 身份证号

    private String roleId;//角色Id

    private Timestamp createTime;

    private String phone;

    private String sex;//(0女,1男）

    private String status;//(0正常,1停用)
}
