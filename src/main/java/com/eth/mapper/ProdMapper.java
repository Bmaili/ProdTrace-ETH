package com.eth.mapper;

import com.eth.entity.Dept;
import com.eth.entity.Product;
import com.eth.form.DeptListForm;
import com.eth.form.ProdListForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProdMapper {

    List<Product> selectProdList(ProdListForm form);
}
