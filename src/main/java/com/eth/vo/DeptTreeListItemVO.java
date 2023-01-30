package com.eth.vo;

import lombok.Data;

import java.util.List;

@Data
public class DeptTreeListItemVO {
    private String role;
    private String roleName;
    private List<DeptTreeListItemVO> children;
}
