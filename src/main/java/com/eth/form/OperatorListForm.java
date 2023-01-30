package com.eth.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OperatorListForm {
    @ApiModelProperty("页码")
    private Integer pageNum = 1;

    @ApiModelProperty("每页数量")
    private Integer pageSize = 10;

    @ApiModelProperty("操作员姓名")
    private String operatorName;

    @ApiModelProperty("操作员电话")
    private String phone;

    @ApiModelProperty("操作员账号状态")
    private String status;

    @ApiModelProperty("所属企业ID")
    private String deptId;

    @ApiModelProperty("开始时间")
    private String beginTime;

    @ApiModelProperty("结束时间")
    private String endTime;

    @ApiModelProperty("操作员性别")
    private String sex;

    @ApiModelProperty("角色")
    private String role;

    @ApiModelProperty("身份证号")
    private String chineseId;

    @ApiModelProperty("操作员账号ID")
    private String operatorId;
}

