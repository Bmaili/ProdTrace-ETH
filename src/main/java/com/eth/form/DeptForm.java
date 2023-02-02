package com.eth.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeptForm {
    @ApiModelProperty("企业ID")
    private String deptId;

    @ApiModelProperty("企业名称")
    private String deptName;

    @ApiModelProperty("企业角色ID")
    private String role;

    @ApiModelProperty("企业简称")
    private String shortName;

    @ApiModelProperty("地址")
    private String address;

    @ApiModelProperty("联系人")
    private String linkman;

    @ApiModelProperty("联系电话")
    private String linkphone;

    @ApiModelProperty("经营许可号")
    private String license;

    @ApiModelProperty("备注")
    private String notes;

    @ApiModelProperty("资料图片url列表")
    private String picture;
}
