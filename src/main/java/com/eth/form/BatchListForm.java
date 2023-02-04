package com.eth.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BatchListForm {
    @ApiModelProperty("页码")
    private Integer pageNum = 1;

    @ApiModelProperty("每页数量")
    private Integer pageSize = 10;

    @ApiModelProperty("批次编号")
    private String batchId;

    @ApiModelProperty("企业ID")
    private String deptId;

    @ApiModelProperty("产品编号")
    private String prodId;

    @ApiModelProperty("流程状态")
    private String status;

    @ApiModelProperty("开始时间")
    private String beginTime;

    @ApiModelProperty("结束时间")
    private String endTime;
}
