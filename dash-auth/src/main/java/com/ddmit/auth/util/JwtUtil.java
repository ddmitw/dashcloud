package com.ddmit.auth.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.ddmit.common.core.constant.TokenConstants;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Winbert
 * @Date 2022/3/16 14:22
 */
public class JwtUtil {

    /**
     * 生成Token
     *
     * @param userId
     * @param userName
     * @return
     */
    public static String createToken(Long userId, String userName) {
        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.SECOND, TokenConstants.TOKEN_EXPIRE);
        Date expireDate = nowTime.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("alg", "HS256");
        map.put("typ", "JWT");

        String token = JWT.create().withHeader(map).withClaim("userId", userId).withClaim("userName", userName).withSubject("测试").withIssuedAt(new Date()).withExpiresAt(expireDate).sign(Algorithm.HMAC256(TokenConstants.TOKEN_SECRET));
        return token;
    }

    /**
     * 验证Token
     *
     * @param token
     * @return
     */
    public static Map<String, Claim> verifyToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(TokenConstants.TOKEN_SECRET)).build();
        DecodedJWT jwt = null;
        try {
            jwt = verifier.verify(token);
        } catch (Exception e) {
            throw new RuntimeException("凭证已过期，请重新登录");
        }
        return jwt.getClaims();
    }

    /**
     * 解析Token
     *
     * @param token
     * @return
     */
    public static Map<String, Claim> parseToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaims();
    }

}
