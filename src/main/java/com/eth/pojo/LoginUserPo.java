package com.eth.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class LoginUserPo implements Serializable {
    private String username;
    private String password;
}

