package com.eth;

import com.eth.controller.DeptController;
import com.eth.controller.OperatorController;
import com.eth.form.flow.CreateFlowForm;
import com.eth.pojo.DeptPo;
import com.eth.pojo.OperatorPo;
import com.eth.pojo.ProductPo;
import com.eth.form.DeptListForm;
import com.eth.form.OperatorForm;
import com.eth.form.ProdListForm;
import com.eth.mapper.DeptMapper;
import com.eth.mapper.OperatorMapper;
import com.eth.service.DeptService;
import com.eth.service.FlowService;
import com.eth.service.ProdService;
import com.eth.utils.AliOss;
import com.eth.utils.FileSHAUtils;
import com.eth.vo.DeptInfoVO;
import com.eth.vo.ResponseResult;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
public class AllTest {
    @Autowired
    private FlowService flowService;
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

    @Autowired
    private OperatorController operatorController;

    @Autowired
    private AliOss aliOss;

    @Test
    public void test() {
        List<DeptPo> deptPos = deptMapper.selectDeptList(new DeptListForm());
        List<selectTreeItem> dept1 = deptPos.stream().filter(dept -> dept.getRole().equals("1"))
                .map(selectTreeItem::new).collect(Collectors.toList());
        dept1.forEach(System.out::println);
    }

    @Data
    class selectTreeItem {
        String deptId;
        String shortName;

        public selectTreeItem(DeptPo deptPo) {
            this.deptId = deptPo.getDeptId();
            this.shortName = deptPo.getShortName();
        }
    }

    @Test
    public void test2(){
        OperatorPo admin = operatorMapper.selectById("admin");
        System.out.println(admin);
    }

    @Test
    public void test3(){
        DeptInfoVO info = deptService.getDeptById("0");
        System.out.println(info);
    }

    @Test
    public void test4(){
        List<ProductPo> prods = prodService.selectProdList(new ProdListForm());
        prods.forEach(System.out::println);
    }


    @Test
    public void test6(){
        OperatorForm form = new OperatorForm();
        form.setOperatorName("王大锤");
        form.setOperatorId("10018");
        ResponseResult result = operatorController.updateOperator(form);
        System.out.println(result);
    }
    @Test
    public void test7(){
        OperatorForm form = new OperatorForm();
        form.setOperatorName("王大锤");
        form.setOperatorId("10018");
        OperatorPo po = new OperatorPo();
        BeanUtils.copyProperties(form, po);
        System.out.println(po);
    }
    @Test
    public void test8(){
        OperatorForm form = new OperatorForm();
        form.setOperatorName("王铁柱");
        form.setOperatorId("10088");
        ResponseResult result = operatorController.addOperator(form);
        System.out.println(result);
    }

    @Test
    public void test9() throws Exception {
        File file = new File("D:\\Desktop\\若依前端结构.png");
        InputStream stream = new FileInputStream(file);
        // String md5Hex = DigestUtils.md5Hex(stream);
        // System.out.println(md5Hex);
        // MessageDigest md5 = MessageDigest.getInstance("SHA-256");
        String hash = FileSHAUtils.getFileSHA256(file);
        System.out.println(hash);
    }

    @Test
    public void test10() throws Exception {
         String str = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        String df = sdf.format(new Date());
        System.out.println(df);
    }

    @Test
    public void test11() throws Exception {
        CreateFlowForm form = new CreateFlowForm();
        form.setDeptName("娃哈哈生产");
        form.setNum("998");
        form.setCategory("食品");
        form.setProdId("998");
        form.setPhone("187783443432");
        form.setQuality("180天");
        form.setOrigin("广西桂林");
        form.setOperatorName("周杰伦");
        flowService.addCreateFlow(form);
    }




    // public void test9(){
    //     MultipartFile upload = new MultipartFile("");
    //     String fileName = upload.getOriginalFilename();
    //     // 获取文件后缀
    //     String prefix = fileName.substring(fileName.lastIndexOf("."));
    //     assert fileName != null;
    //     File file = null;
    //
    //     // Thumbnails库进行图片压缩
    //     // try {
    //     //     file = File.createTempFile(fileName, prefix);
    //     //     Thumbnails.of(upload.getInputStream()).scale(0.5f) //图片大小（长宽）压缩比例 从0-1，1表示原图
    //     //             .outputQuality(0.2f) //图片质量压缩比例 从0-1，越接近1质量越好
    //     //             .toFile(file);
    //     //     System.out.println("图片压缩成功");
    //     // } catch (IOException e) {
    //     //     e.printStackTrace();
    //     // }
    //
    //     // 调用OSS服务上传图片
    //     String puth = aliOss.ossUpPic(file);
    //     HashMap<Object, Object> map = new HashMap<>();
    //     map.put("picPuth", puth);
    //     return new ResponseResult(ResultEnum.SUCCESS_GET, map);
    // }


}

