package com.eth.service;

import com.eth.vo.ResponseResult;
import org.springframework.web.multipart.MultipartFile;

public interface UpFileService {
    ResponseResult upPicture (MultipartFile file);
}
