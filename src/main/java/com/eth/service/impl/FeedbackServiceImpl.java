package com.eth.service.impl;

import com.eth.enums.ResultEnum;
import com.eth.form.FeedbackForm;
import com.eth.form.FeedbackListForm;
import com.eth.mapper.FeedbackMapper;
import com.eth.pojo.FeedbackPo;
import com.eth.service.FeedbackService;
import com.eth.vo.FeedbackInfoVo;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackMapper feedbackMapper;

    @Override
    public List<FeedbackPo> selectFeedbackList(FeedbackListForm form) {
        return feedbackMapper.selectFeedbackList(form);
    }

    @Override
    public FeedbackInfoVo getFeedbackById(Long id) {
        FeedbackPo feedbackPo = feedbackMapper.selectById(id);
        FeedbackInfoVo feedbackInfoVo = new FeedbackInfoVo();
        BeanUtils.copyProperties(feedbackPo, feedbackInfoVo);
        return feedbackInfoVo;
    }

    @Override
    public ResponseResult delFeedbackById(Long id) {
        feedbackMapper.delById(id);
        return new ResponseResult(ResultEnum.SUCCESS_OF_DELETE);
    }

    @Override
    public ResponseResult insertFeedback(FeedbackForm form) {
        FeedbackPo feedbackPo = new FeedbackPo();
        BeanUtils.copyProperties(form, feedbackPo);
        feedbackMapper.insertFeedback(feedbackPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }
}
