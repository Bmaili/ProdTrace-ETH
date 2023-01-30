package com.eth.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProdListForm {
    @ApiModelProperty("产品编号")
    private String prodId;

    @ApiModelProperty("产品全称")
    private String prodName;

    @ApiModelProperty("所属企业Id")
    private String deptId;

    @ApiModelProperty("计算单位")
    private String unit;

    @ApiModelProperty("状态(0正常,1停用)")
    private String status;

    @ApiModelProperty("类别")
    private String category;

    @ApiModelProperty("开始时间")
    private String beginTime;

    @ApiModelProperty("结束时间")
    private String endTime;

    @ApiModelProperty("页码")
    private Integer pageNum = 1;

    @ApiModelProperty("每页数量")
    private Integer pageSize = 10;
}
