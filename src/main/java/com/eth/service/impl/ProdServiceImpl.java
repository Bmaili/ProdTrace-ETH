package com.eth.service.impl;

import com.eth.enums.ResultEnum;
import com.eth.form.ProductForm;
import com.eth.pojo.ProductPo;
import com.eth.form.ProdListForm;
import com.eth.mapper.ProdMapper;
import com.eth.service.ProdService;
import com.eth.vo.ProductInfoVo;
import com.eth.vo.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.SecureRandom;
import java.util.List;

@Slf4j
@Service
public class ProdServiceImpl implements ProdService {
    @Autowired
    ProdMapper prodMapper;

    @Override
    public ProductInfoVo getProductById(String id) {
        ProductPo productPo = prodMapper.selectById(id);
        ProductInfoVo infoVo = new ProductInfoVo();
        BeanUtils.copyProperties(productPo, infoVo);
        return infoVo;
    }

    @Override
    public ResponseResult updateProduct(ProductForm form) {
        if (!StringUtils.hasText(form.getProdId())) {
            return new ResponseResult(ResultEnum.BAD_REQUEST);
        }
        ProductPo productPo = new ProductPo();
        BeanUtils.copyProperties(form, productPo);
        prodMapper.updateById(productPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_UPDATE);
    }

    @Override
    public ResponseResult insertProduct(ProductForm form) {
        ProductPo productPo = new ProductPo();
        BeanUtils.copyProperties(form, productPo);
        String prodId = String.valueOf(new SecureRandom().nextInt(99999999));
        productPo.setProdId(prodId);
        prodMapper.insertProduct(productPo);
        return new ResponseResult(ResultEnum.SUCCESS_OF_ADD);
    }

    @Override
    public List<ProductPo> selectProdList(ProdListForm form) {
        return prodMapper.selectProdList(form);
    }
}
