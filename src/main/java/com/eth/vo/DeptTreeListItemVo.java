package com.eth.vo;

import lombok.Data;

import java.util.List;

@Data
public class DeptTreeListItemVo {
    private String role;
    private String roleName;
    private List<DeptTreeListItemVo> children;
}
