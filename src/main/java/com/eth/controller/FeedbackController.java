package com.eth.controller;

import com.eth.enums.ResultEnum;
import com.eth.form.FeedbackForm;
import com.eth.form.FeedbackListForm;
import com.eth.pojo.FeedbackPo;
import com.eth.service.FeedbackService;
import com.eth.service.UpFileService;
import com.eth.vo.FeedbackInfoVo;
import com.eth.vo.ResponseResult;
import com.eth.vo.TableDataInfo;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/feedback")
@Api(tags = "用户反馈操作接口")
@Slf4j
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private UpFileService upFileService;

    @ApiOperation("查询用户反馈列表")
    @GetMapping(name = "查询用户反馈列表", value = "/list")
    public TableDataInfo feedbackList(@Valid FeedbackListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<FeedbackPo> feedbackPos = feedbackService.selectFeedbackList(form);
        return new TableDataInfo(feedbackPos);
    }

    @ApiOperation("通过ID查询用户反馈信息")
    @GetMapping(name = "查询用户反馈")
    public ResponseResult getFeedback(@Valid Long feedbackId) {
        FeedbackInfoVo info = feedbackService.getFeedbackById(feedbackId);
        return new ResponseResult(info);

    }

    @ApiOperation("通过ID删除用户反馈")
    @DeleteMapping(name = "删除用户反馈")
    public ResponseResult delFeedback(@Valid Long feedbackId) {
        return feedbackService.delFeedbackById(feedbackId);
    }

    @ApiOperation("增加用户反馈")
    @PostMapping (name = "增加用户反馈")
    public ResponseResult addFeedback(@RequestBody @Valid FeedbackForm form) {
        return feedbackService.insertFeedback(form);
    }

    @ApiOperation("上传用户反馈资料图片")
    @PostMapping(value = "/upPic")
    public ResponseResult uploadFile(@RequestParam(value = "file", required = true) MultipartFile upload) {
        Map map = null;
        try {
            map = upFileService.upPicture(upload);
        }catch (Exception e){
            log.error(e.getMessage(),e);
            return new ResponseResult(ResultEnum.RUNTIME_ERROR);
        }
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD,map);
    }
}
