package com.eth.mapper;

import com.eth.pojo.ProductPo;
import com.eth.form.ProdListForm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProdMapper {

    List<ProductPo> selectProdList(ProdListForm form);

    ProductPo selectById(String prodId);

    void updateById(ProductPo product);

    void insertProduct(ProductPo product);
}
