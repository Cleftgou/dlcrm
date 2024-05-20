package com.niu.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.niu.model.TUser;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bairimengchang
 * @version 1.0
 * @project dlyk
 * @description JWT（token）获取，验证，解析工具类
 * 密钥（SECRET）用于生成签名，以验证 JWT 的真实性，而不是用于加密负载数据。因此，即使用户拿到了生成的 JWT，也无法通过 JWT 推导出密钥的值
 * @date 2024/4/24 10:39:05
 */
@Slf4j
public class JWTUtils {

    //密钥
    public static final String SECRET = "dY8300olWQ3345;1d<3w48";

    //生成一个JWT
    public static String createJWT(String userJSON) {
        //头数据（固定）
        Map<String, Object> header = new HashMap<>();
        header.put("alg", "HS256");
        header.put("typ", "JWT");
        return JWT.create()
                //头部
                .withHeader(header)
                //负载
                .withClaim("user", userJSON)
                //签名
                .sign(Algorithm.HMAC256(SECRET));
    }

    //验证JWT
    public static Boolean verifyJWT(String jwt) {
        try {
            //使用秘钥创建一个JWT验证器对象
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            //验证JWT，如果没有抛出异常，说明验证通过，否则验证不通过
            jwtVerifier.verify(jwt);
            return true;
        } catch (Exception e) {
            //捕捉到异常，验证不通过，返回false
            System.out.println("token验证不通过，应该是token值有误：" + e.getMessage());
            log.warn("token验证不通过，应该是token值有误：" + e.getMessage());
            return false;
        }
    }

    //解析拿到JWT中的User对象
    public static TUser parseUserFromJWT(String jwt) {
        try {
            // 使用秘钥创建一个验证器对象
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRET)).build();
            //验证JWT，得到一个解码后的jwt对象
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt);

            //通过解码后的jwt对象，就可以获取里面的负载数据（User对象）
            Claim userClaim = decodedJWT.getClaim("user");
            //转成json
            String userJSON = userClaim.asString();
            //再把json转成Java对象
            return JSONUtils.toBean(userJSON, TUser.class);
        } catch (TokenExpiredException e) {
            System.out.println("token解析后拿不到token中的user对象：" + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
