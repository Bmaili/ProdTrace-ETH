package com.eth;

import com.eth.controller.DeptController;
import com.eth.entity.Dept;
import com.eth.entity.Operator;
import com.eth.entity.Product;
import com.eth.form.DeptListForm;
import com.eth.form.ProdListForm;
import com.eth.mapper.DeptMapper;
import com.eth.mapper.OperatorMapper;
import com.eth.service.DeptService;
import com.eth.service.ProdService;
import com.eth.vo.DeptInfoVO;
import com.eth.vo.ResponseResult;
import com.eth.vo.TableDataInfo;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class AllTest {
    @Autowired
    private OperatorMapper operatorMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private DeptService deptService;

    @Autowired
    private ProdService prodService;

    @Autowired
    private DeptController deptController;

    @Test
    public void test() {
        List<Dept> depts = deptMapper.selectDeptList(new DeptListForm());
        List<selectTreeItem> dept1 = depts.stream().filter(dept -> dept.getRole().equals("1"))
                .map(selectTreeItem::new).collect(Collectors.toList());
        dept1.forEach(System.out::println);
    }

    @Data
    class selectTreeItem {
        String deptId;
        String shortName;

        public selectTreeItem(Dept dept) {
            this.deptId = dept.getDeptId();
            this.shortName = dept.getShortName();
        }
    }

    @Test
    public void test2(){
        Operator admin = operatorMapper.selectById("admin");
        System.out.println(admin);
    }

    @Test
    public void test3(){
        DeptInfoVO info = deptService.getDeptById("0");
        System.out.println(info);
    }

    @Test
    public void test4(){
        List<Product> prods = prodService.selectProdList(new ProdListForm());
        prods.forEach(System.out::println);
    }

    @Test
    public void test5(){
        ResponseResult result = deptController.getListselect();
        System.out.println(result);
    }


}

