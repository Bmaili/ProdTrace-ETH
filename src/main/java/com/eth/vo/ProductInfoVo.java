package com.eth.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductInfoVo {
    private String prodId;// 产品编号

    private String prodName;// 产品全称

    private String deptId;//所属企业Id

    private String deptName;//所属企业全称

    private String unit;//计算单位

    private String status;//(0正常,1停用)

    private String category;//类别

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
