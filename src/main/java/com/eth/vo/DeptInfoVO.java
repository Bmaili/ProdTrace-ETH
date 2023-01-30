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
public class DeptInfoVO {
    private String deptId;// 企业ID

    private String deptName;// 操作员姓名

    private String role;//企业角色ID

    private String shortName;

    private String address;

    private String linkman;

    private String linkphone;

    private String license;

    private String notes;

    private String picture;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}

