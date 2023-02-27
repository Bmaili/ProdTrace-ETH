package com.eth.service;

import com.eth.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public interface UpFileService {
    Map<String,String> upPicture(MultipartFile file) throws IOException;

    Map<String,String> upPicture(File file) throws IOException;

    ResponseResult upFlowFile(MultipartFile file);
}
