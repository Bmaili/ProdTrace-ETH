package com.eth.controller;

import com.eth.entity.Product;
import com.eth.form.ProdListForm;
import com.eth.service.ProdService;
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
@RequestMapping("/prod")
@Api(tags = "产品相关接口")
public class ProdController {

    @Autowired
    private ProdService prodService;

    @ApiOperation("查询产品列表")
    @GetMapping(name = "查询产品列表", value = "/list")
    public TableDataInfo prodList(@Valid ProdListForm form) {
        PageHelper.startPage(form.getPageNum(), form.getPageSize());
        List<Product> prods = prodService.selectProdList(form);
        return new TableDataInfo(prods);
    }
}
