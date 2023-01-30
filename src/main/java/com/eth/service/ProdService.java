package com.eth.service;

import com.eth.entity.Product;
import com.eth.form.ProdListForm;

import java.util.List;

public interface ProdService  {
    List<Product> selectProdList(ProdListForm form);
}
