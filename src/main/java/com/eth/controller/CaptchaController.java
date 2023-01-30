package com.eth.controller;

import com.eth.enums.ResultEnum;
import com.eth.utils.Base64;
import com.eth.utils.RedisCache;
import com.eth.vo.ResponseResult;
import com.google.code.kaptcha.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class CaptchaController {

    @Resource(name = "captchaProducerMath")
    private Producer captchaProducerMath;

    @Autowired
    private RedisCache redisCache;

    /**
     * 生成验证码
     */
    @GetMapping("/captchaImage")
    public ResponseResult getCode(HttpServletResponse response) throws IOException {

        // 保存验证码信息
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");

        String verifyKey = "captchaImage:" + uuid;

        String capStr = null, code = null;
        BufferedImage image = null;


        String capText = captchaProducerMath.createText();
        capStr = capText.substring(0, capText.lastIndexOf("@"));
        code = capText.substring(capText.lastIndexOf("@") + 1);
        image = captchaProducerMath.createImage(capStr);


        redisCache.setCacheObject(verifyKey, code, 2, TimeUnit.MINUTES);
        // 转换流信息写出
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        try {
            ImageIO.write(image, "jpg", os);
        } catch (IOException e) {
            return new ResponseResult(ResultEnum.RUNTIME_ERROR);
        }

        HashMap<String, Object> map = new HashMap<>();

        map.put("uuid", uuid);
        map.put("img", Base64.encode(os.toByteArray()));
        return new ResponseResult(map);
    }
}

