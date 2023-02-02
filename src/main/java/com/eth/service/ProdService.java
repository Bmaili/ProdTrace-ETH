package com.eth.service;

import com.eth.form.ProductForm;
import com.eth.pojo.ProductPo;
import com.eth.form.ProdListForm;
import com.eth.vo.ProductInfoVo;
import com.eth.vo.ResponseResult;

import java.util.List;

public interface ProdService  {
    List<ProductPo> selectProdList(ProdListForm form);

    ProductInfoVo getProductById(String id);

    ResponseResult updateProduct(ProductForm form);

    ResponseResult insertProduct(ProductForm form);
}
