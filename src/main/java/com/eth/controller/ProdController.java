package com.eth.controller;

import com.eth.form.ProductForm;
import com.eth.pojo.ProductPo;
import com.eth.form.ProdListForm;
import com.eth.service.ProdService;
import com.eth.vo.ProductInfoVo;
import com.eth.vo.ResponseResult;
import com.eth.vo.TableDataInfo;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        List<ProductPo> prods = prodService.selectProdList(form);
        return new TableDataInfo(prods);
    }
    @ApiOperation("通过ID查询产品信息")
    @GetMapping(name = "查询产品")
    public ResponseResult getProduct(@Valid String prodId) {
        ProductInfoVo product = prodService.getProductById(prodId);
        return new ResponseResult(product);
    }

    @ApiOperation("更新产品")
    @PutMapping(name = "更新产品")
    public ResponseResult updateProduct(@RequestBody @Valid ProductForm form) {
        return prodService.updateProduct(form);
    }

    @ApiOperation("增加产品")
    @PostMapping(name = "增加产品")
    public ResponseResult addProduct(@RequestBody @Valid ProductForm form) {
        return prodService.insertProduct(form);
    }

    @ApiOperation("更新产品状态")
    @PutMapping(name = "更新产品状态", value = "/changeStatus")
    public ResponseResult changeProductStatus(@RequestBody @Valid ProductForm form) {
        return prodService.updateProduct(form);
    }
}
