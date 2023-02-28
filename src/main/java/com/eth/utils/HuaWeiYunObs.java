package com.eth.utils;

import com.obs.services.ObsClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;

@Slf4j
@Configuration
public class HuaWeiYunObs {
    @Value("${OBS.end-point}")
    private String ENDPOINT;

    @Value("${OBS.access-key}")
    private String ACCESSKEYID;

    @Value("${OBS.access-key-secret}")
    private String ACCESSKEYSECRET;

    @Value("${OBS.bucket}")
    private String BUCKETNAME;

    @Value("${OBS.folder-path}")
    private String FOLDERPATH;

    private String BASEURL = "https://bmalimarkdown.obs.cn-south-1.myhuaweicloud.com/";

    public String obsUpFile(File upFile) throws IOException {
        String fileName = upFile.getName();

        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = FOLDERPATH + finalFileName;
        ObsClient obsClient = new ObsClient(ACCESSKEYID, ACCESSKEYSECRET, ENDPOINT);
        obsClient.putObject(BUCKETNAME, objectName, upFile);

        String url = BASEURL + FOLDERPATH + finalFileName;
        log.info("上传图片至华为云OBS：{}", url);
        obsClient.close();
        return url;
    }

    public String obsUpInputStream(InputStream inputStream, String fileName) throws IOException {
        String[] split = fileName.split("\\.");
        if (split.length <= 1) {
            fileName += ".jpg";
        }
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        // 生成上传文件名
        String finalFileName = System.currentTimeMillis() + "" + new SecureRandom().nextInt(0x0400) + suffixName;
        String objectName = FOLDERPATH + finalFileName;
        ObsClient obsClient = new ObsClient(ACCESSKEYID, ACCESSKEYSECRET, ENDPOINT);
        obsClient.putObject(BUCKETNAME, objectName, inputStream);

        String url = BASEURL + FOLDERPATH + finalFileName;
        log.info("上传图片至华为云OBS：{}", url);
        obsClient.close();
        return url;
    }
}
