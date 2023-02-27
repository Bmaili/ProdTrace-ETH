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
public class FeedbackPo {
    private Long feedbackId;

    private String name;

    private String address;

    private String phone;

    private String email;

    private String title;

    private String info;

    private String eval;

    private String picture;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
