package com.xitian.djrlpwst.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT工具类
 */
public class JwtUtil {

    // 密钥（生产环境中应该从配置文件读取）
    private static final String SECRET_KEY = "smart_health_hub_secret_key";
    
    // 访问令牌过期时间（毫秒）
    private static final long ACCESS_TOKEN_EXPIRATION = 24 * 60 * 60 * 1000; // 24小时
    
    // 刷新令牌过期时间（毫秒）
    private static final long REFRESH_TOKEN_EXPIRATION = 7 * 24 * 60 * 60 * 1000; // 7天
    
    /**
     * 生成访问令牌
     * @param userDetails 用户信息
     * @return 访问令牌
     */
    public static String generateAccessToken(Map<String, Object> userDetails) {
        return createToken(userDetails, ACCESS_TOKEN_EXPIRATION);
    }
    
    /**
     * 生成刷新令牌
     * @param userDetails 用户信息
     * @return 刷新令牌
     */
    public static String generateRefreshToken(Map<String, Object> userDetails) {
        return createToken(userDetails, REFRESH_TOKEN_EXPIRATION);
    }
    
    /**
     * 创建令牌
     * @param claims 声明信息
     * @param expiration 过期时间
     * @return 令牌
     */
    private static String createToken(Map<String, Object> claims, long expiration) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }
    
    /**
     * 解析令牌中的所有声明
     * @param token 令牌
     * @return 声明信息
     */
    public static Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }
    
    /**
     * 从令牌中提取用户名
     * @param token 令牌
     * @return 用户名
     */
    public static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    
    /**
     * 从令牌中提取过期时间
     * @param token 令牌
     * @return 过期时间
     */
    public static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
    
    /**
     * 从令牌中提取声明信息
     * @param token 令牌
     * @param claimsResolver 声明解析器
     * @param <T> 声明类型
     * @return 声明值
     */
    public static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    
    /**
     * 验证令牌是否过期
     * @param token 令牌
     * @return 是否过期
     */
    public static Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    
    /**
     * 验证令牌有效性
     * @param token 令牌
     * @param username 用户名
     * @return 是否有效
     */
    public static Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}