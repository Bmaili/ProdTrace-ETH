package com.eth.service.impl;

import com.eth.form.OperatorListForm;
import com.eth.service.OperatorService;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OperatorServiceImpl implements OperatorService {
    @Override
    public ResponseResult getOperatorList(OperatorListForm form) {
        return null;
    }
}

