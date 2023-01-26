package com.eth.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@AllArgsConstructor
@ToString
public class LoginUser implements Serializable {
    private String username;
    private String password;
}

