package com.eth.service.impl;

import com.eth.enums.ResultEnum;
import com.eth.service.UpFileService;
import com.eth.utils.AliOss;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

@Slf4j
@Service
public class UpFileServiceImpl implements UpFileService {

    @Autowired
    AliOss aliOss;

    @Override
    public ResponseResult upPicture(MultipartFile upload) {
        String fileName = upload.getOriginalFilename();
        InputStream stream = null;
        try {
            stream = upload.getInputStream();
        } catch (IOException e) {
            return new ResponseResult(ResultEnum.RUNTIME_ERROR);
        }
        // 调用OSS服务上传图片
        String path = aliOss.ossUpInputStream(stream, fileName);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("picPath", path);
        return new ResponseResult(ResultEnum.SUCCESS_GET, map);
    }
}