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
public class FeedbackForm {
    @ApiModelProperty("反馈ID")
    private Long feedbackId;

    @ApiModelProperty("反馈人姓名")
    private String name;

    @ApiModelProperty("反馈人地址")
    private String address;

    @ApiModelProperty("反馈人电话")
    private String phone;

    @ApiModelProperty("反馈人邮箱")
    private String email;

    @ApiModelProperty("反馈标题")
    private String title;

    @ApiModelProperty("反馈内容")
    private String info;

    @ApiModelProperty("评价(0满意，1良好，2不满意)")
    private String eval;

    @ApiModelProperty("图片地址")
    private String picture;
}
