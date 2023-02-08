package com.eth.form.flow;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SaleFlowForm {
    @ApiModelProperty("溯源码/批次号")
    private String batchId;

    @ApiModelProperty("销售端编号")
    private String deptId;

    @ApiModelProperty("销售端全称")
    private String deptName;

    @ApiModelProperty("操作人")
    private String operatorName;

    @ApiModelProperty("身份证号码")
    private String chineseId;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("报价")
    private String price;

    @ApiModelProperty("文件链接列表")
    private String fileUrlList;

    @ApiModelProperty("文件摘要列表")
    private String fileSHA256List;
}
