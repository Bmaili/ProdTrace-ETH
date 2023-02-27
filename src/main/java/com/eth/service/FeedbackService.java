package com.eth.service;

import com.eth.form.FeedbackForm;
import com.eth.form.FeedbackListForm;
import com.eth.pojo.FeedbackPo;
import com.eth.vo.FeedbackInfoVo;
import com.eth.vo.ResponseResult;

import java.util.List;

public interface FeedbackService {
    List<FeedbackPo> selectFeedbackList(FeedbackListForm form);

    FeedbackInfoVo getFeedbackById(Long id);

    ResponseResult delFeedbackById(Long id);

    ResponseResult insertFeedback(FeedbackForm form);

}
