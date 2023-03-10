package com.eth.pojo;

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
public class DeptPo {
    private String deptId;

    private String deptName;

    private String role;

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
