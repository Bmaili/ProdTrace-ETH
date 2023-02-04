package com.eth.controller;

import com.eth.form.BatchListForm;
import com.eth.pojo.BatchPo;
import com.eth.service.BatchService;
import com.eth.vo.TableDataInfo;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/batch")
@Api(tags = "产品批次操作接口")
public class BatchController {
    @Autowired
    private BatchService batchService;

    @ApiOperation("查询产品批次列表")
    @GetMapping(name = "查询产品批次列表", value = "/list")
    public TableDataInfo batchList(@Valid BatchListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<BatchPo> batchPos = batchService.selectBatchList(form);
        return new TableDataInfo(batchPos);
    }
}
