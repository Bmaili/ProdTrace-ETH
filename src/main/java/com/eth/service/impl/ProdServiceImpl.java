package com.eth.service.impl;

import com.eth.entity.Product;
import com.eth.form.ProdListForm;
import com.eth.mapper.ProdMapper;
import com.eth.service.ProdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProdServiceImpl implements ProdService {
    @Autowired
    ProdMapper prodMapper;

    @Override
    public List<Product> selectProdList(ProdListForm form) {
        return prodMapper.selectProdList(form);
    }
}
