package com.eth.mapper;

import com.eth.form.FeedbackListForm;
import com.eth.pojo.FeedbackPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FeedbackMapper {
    void delById(Long id);

    FeedbackPo selectById(Long id);

    List<FeedbackPo> selectFeedbackList(FeedbackListForm form);

    void insertFeedback(FeedbackPo feedbackPo);
}
