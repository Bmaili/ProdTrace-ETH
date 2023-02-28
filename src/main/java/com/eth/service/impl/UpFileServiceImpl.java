package com.eth.service.impl;

import com.eth.enums.ResultEnum;
import com.eth.service.UpFileService;
import com.eth.utils.AliOss;
import com.eth.utils.FileUtils;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UpFileServiceImpl implements UpFileService {

    @Autowired
    AliOss aliOss;

    @Override
    public Map upPicture(MultipartFile upload) throws IOException {
        String fileName = upload.getOriginalFilename();

        InputStream stream = null;
        stream = upload.getInputStream();
        // 调用OSS服务上传图片
        String path = aliOss.ossUpInputStream(stream, fileName);
        HashMap<String, String> map = new HashMap<>();
        map.put("picPath", path);
        return map;
    }

    @Override
    public Map<String, String> upPicture(File file) throws IOException {
        // 调用OSS服务上传图片
        String path = aliOss.ossUpFile(file);
        HashMap<String, String> map = new HashMap<>();
        map.put("picPath", path);
        return map;
    }

    @Override
    public ResponseResult upFlowFile(MultipartFile upload) {
        String fileName = upload.getOriginalFilename();
        InputStream stream = null;
        try {
            stream = upload.getInputStream();
        } catch (IOException e) {
            return new ResponseResult(ResultEnum.RUNTIME_ERROR);
        }

        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));

        File file = null;
        try {
            file = File.createTempFile(fileName, prefix);
            upload.transferTo(file);
        } catch (IOException e) {
            return new ResponseResult(ResultEnum.RUNTIME_ERROR);
        }
        String fileSHA256 = FileUtils.getFileSHA256(file);

        // 调用OSS服务上传图片
        String path = aliOss.ossUpInputStream(stream, fileName);
        HashMap<Object, Object> map = new HashMap<>();
        map.put("filePath", path);
        map.put("SHA256", fileSHA256);
        return new ResponseResult(ResultEnum.SUCCESS_GET, map);
    }
}
