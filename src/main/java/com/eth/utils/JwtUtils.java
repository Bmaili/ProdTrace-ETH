package com.eth.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * JWT工具类
 *
 */
@Configuration
public class JwtUtils {
    public static final long JWT_TTL = 24 * 60 * 60 * 1000L;//24个小时
    public static final String JWT_KEY = "BgqETH"; //密钥明文

    public static String getUUID() {
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        return token;

    }

    public static String createJWT(String subject) {
        JwtBuilder builder = getJwtBuilder(subject, null, getUUID());//设置过期时间
        return builder.compact();
    }

    public static String createJWT(String subject, Long ttlMillis) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMillis, getUUID());//设置过期时间
        return builder.compact();
    }

    /**
     * 生成jtw
     *
     * @param subject    token中要存放的数据（JSON格式）
     * @param ttlMikllis token超时时间
     * @param id         id
     * @return
     * @date 13:48 2022/1/26
     */
    public static String createJWT(String subject, Long ttlMikllis, String id) {
        JwtBuilder builder = getJwtBuilder(subject, ttlMikllis, id);//设置过期时间
        return builder.compact();
    }

    private static JwtBuilder getJwtBuilder(String subject, Long ttlMillis, String uuid) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        SecretKey secretKey = generalkey();
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JwtUtils.JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);

        JwtBuilder builder = Jwts.builder()
                .setId(uuid)  //唯一的ID
                .setSubject(subject)  //主题 可以是JSON数据
                .setIssuer("gq")  //签发者
                .setIssuedAt(now)  //签发时间
                .signWith(signatureAlgorithm, secretKey)  //使用HS256对称加密算法签名，第二个参数为密钥
                .setExpiration(expDate);

        return Jwts.builder()
                .setId(uuid)  //唯一的ID
                .setSubject(subject)  //主题 可以是JSON数据
                .setIssuer("gq")  //签发者
                .setIssuedAt(now)  //签发时间
                .signWith(signatureAlgorithm, secretKey)  //使用HS256对称加密算法签名，第二个参数为密钥
                .setExpiration(expDate);


    }

    //生成加密后的密钥
    public static SecretKey generalkey() {
        byte[] encodedkey = Base64.getDecoder().decode(JwtUtils.JWT_KEY);
        SecretKeySpec key = new SecretKeySpec(encodedkey, 0, encodedkey.length, "AES");
        return key;
    }

    // 解析
    public static Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalkey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    public static void main(String[] args) throws Exception {
        // String jwt = JwtUtils.createJWT("10003");
        // System.out.println(jwt);
        String BCPassword = new BCryptPasswordEncoder().encode("123456");
        System.out.println(BCPassword);
    }

}
