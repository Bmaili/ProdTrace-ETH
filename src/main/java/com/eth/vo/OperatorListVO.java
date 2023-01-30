
package com.eth.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OperatorListVO {
    private String operatorId;// 账号ID

    private String operatorName;// 操作员姓名

    private String deptId;//所属企业Id

    private String deptName;//所属企业名称

    private String chineseId; // 身份证号

    private String roleId;//角色Id

    private Date createTime;

    private String phone;
    private String email;

    private String sex;//(0女,1男）

    private String status;//(0正常,1停用)
}
