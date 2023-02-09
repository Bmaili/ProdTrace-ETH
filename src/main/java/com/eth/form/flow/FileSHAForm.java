package com.eth.form.flow;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class FileSHAForm {
    @ApiModelProperty("文件url")
    private String url;

    @ApiModelProperty("文件SHA256摘要信息")
    @JSONField(name="SHA256") //fastjson会将首字母变小写，加此注解解决
    private String SHA256;
}
