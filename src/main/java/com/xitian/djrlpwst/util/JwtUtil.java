package com.xitian.djrlpwst.util;

import com.xitian.djrlpwst.config.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * JWT工具类
 */
@Component
public class JwtUtil {

    private static JwtProperties jwtProperties;
    private static SecretKey secretKey;
    
    @Autowired
    public void setJwtProperties(JwtProperties jwtProperties) {
        JwtUtil.jwtProperties = jwtProperties;
        // 使用JWT库生成符合HS512要求的安全密钥
        JwtUtil.secretKey = Keys.hmacShaKeyFor(jwtProperties.getSecret().getBytes());
    }
    
    /**
     * 生成访问令牌
     * @param userDetails 用户信息
     * @return 访问令牌
     */
    public static String generateAccessToken(Map<String, Object> userDetails) {
        return createToken(userDetails, jwtProperties.getAccessTokenExpiration());
    }
    
    /**
     * 生成访问令牌（为了向后兼容）
     * @param userDetails 用户信息
     * @param role 用户角色
     * @return 访问令牌
     */
    public static String generateAccessToken(Map<String, Object> userDetails, String role) {
        return createToken(userDetails, jwtProperties.getAccessTokenExpiration());
    }
    
    /**
     * 生成刷新令牌
     * @param userDetails 用户信息
     * @return 刷新令牌
     */
    public static String generateRefreshToken(Map<String, Object> userDetails) {
        return createToken(userDetails, jwtProperties.getRefreshTokenExpiration());
    }
    
    /**
     * 生成刷新令牌（为了向后兼容）
     * @param userDetails 用户信息
     * @param role 用户角色
     * @return 刷新令牌
     */
    public static String generateRefreshToken(Map<String, Object> userDetails, String role) {
        return createToken(userDetails, jwtProperties.getRefreshTokenExpiration());
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
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
    
    /**
     * 解析令牌中的所有声明
     * @param token 令牌
     * @return 声明信息
     */
    public static Claims extractAllClaims(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
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