package com.eth.form.flow;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CreateFlowForm {
    @ApiModelProperty("产品编号")
    private String prodId;

    @ApiModelProperty("产品名称")
    private String prodName;

    @ApiModelProperty("产品类别")
    private String category;

    @ApiModelProperty("生产商编号")
    private String deptId;

    @ApiModelProperty("生产商")
    private String deptName;

    @ApiModelProperty("计量单位")
    private String unit;

    @ApiModelProperty("操作人")
    private String operatorName;

    @ApiModelProperty("身份证号码")
    private String chineseId;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("备注")
    private String notes = "无";

    @ApiModelProperty("文件摘要列表")
    private List<FileSHAForm> fileList;

    @ApiModelProperty("数量")
    private String num;

    @ApiModelProperty("源产地")
    private String origin;

    @ApiModelProperty("保质期")
    private String quality;
}
