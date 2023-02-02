package com.eth.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OperatorForm {
    @ApiModelProperty("操作员Id")
    private String operatorId;

    @ApiModelProperty("操作员姓名")
    private String operatorName;

    @ApiModelProperty("所属企业ID")
    private String deptId;

    @ApiModelProperty("身份证号")
    private String chineseId;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("操作员电话")
    private String phone;

    @ApiModelProperty("电子邮件")
    private String email;

    @ApiModelProperty("操作员性别")
    private String sex;

    @ApiModelProperty("操作员账号状态")
    private String status;

}
