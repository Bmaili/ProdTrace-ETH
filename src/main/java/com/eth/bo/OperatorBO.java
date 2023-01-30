package com.eth.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperatorBO implements Serializable {
    private String operatorId;// 账号ID

    private String operatorName;// 操作员姓名

    private String deptId;//所属企业Id

    private String chineseId; // 身份证号

    private String role;//角色Id

    private Date createTime;

    private String phone;

    private String sex;//(0女,1男）

    private String status;//(0正常,1停用)

    private String avatar;
}
