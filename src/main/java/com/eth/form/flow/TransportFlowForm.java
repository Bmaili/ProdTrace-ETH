package com.eth.form.flow;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class TransportFlowForm {
    @ApiModelProperty("溯源码/批次号")
    private String batchId;

    @ApiModelProperty("运输厂商编号")
    private String deptId;

    @ApiModelProperty("物流运输")
    private String deptName;

    @ApiModelProperty("操作人")
    private String operatorName;

    @ApiModelProperty("身份证号码")
    private String chineseId;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("备注")
    private String notes;

    @ApiModelProperty("文件摘要列表")
    private List<FileSHAForm> fileList;

    @ApiModelProperty("始发地")
    private String origin;

    @ApiModelProperty("目的地")
    private String destination;

}
