package com.eth.form;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductForm {
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
}
