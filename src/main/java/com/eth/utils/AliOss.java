package com.eth.utils;

import com.aliyun.oss.OSSClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;
import java.security.SecureRandom;

@Slf4j
@Configuration
public class AliOss {
    // Endpoint
    @Value("${OSS.end-point}")
    private String ENDPOINT;

    // AccessKey 在AccessKey管理获取 -->登录后-->头像-->AccessKey管理
    @Value("${OSS.access-key}")
    private String ACCESSKEYID;

    // accessKeySecret 在AccessKey管理获取 -->登录后-->头像-->AccessKey管理
    @Value("${OSS.access-key-secret}")
    private String ACCESSKEYSECRET;

    // Bucket
    @Value("${OSS.bucket}")
    private String BUCKETNAME;

    @Value("${OSS.folder-path}")
    private String FOLDERPATH;

    private String BASEURL = "https://bmalimarkdown.oss-cn-beijing.aliyuncs.com/";


    public String ossUpFile(File upFile) {
        String fileName = upFile.getName();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = FOLDERPATH + finalFileName;
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
        ossClient.putObject(BUCKETNAME, objectName, upFile);

        String url = BASEURL + FOLDERPATH + finalFileName;
        log.info("上传图片至阿里云：{}", url);
        ossClient.shutdown();
        return url;
    }

    public String ossUpInputStream(InputStream inputStream, String fileName) {
        String[] split = fileName.split("\\.");
        if (split.length <= 1) {
            fileName += ".jpg";
        }
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = FOLDERPATH + finalFileName;
        OSSClient ossClient = new OSSClient(ENDPOINT, ACCESSKEYID, ACCESSKEYSECRET);
        ossClient.putObject(BUCKETNAME, objectName, inputStream);

        String url = BASEURL + FOLDERPATH + finalFileName;
        log.info("上传图片至阿里云：{}", url);
        ossClient.shutdown();
        return url;
    }
}

