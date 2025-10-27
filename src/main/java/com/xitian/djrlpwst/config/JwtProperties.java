package com.xitian.djrlpwst.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtProperties {
    
    @Value("${jwt.secret:smart_health_hub_secret_key}")
    private String secret;
    
    @Value("${jwt.access-token-expiration:18000000}")
    private long accessTokenExpiration;
    
    @Value("${jwt.refresh-token-expiration:604800000}")
    private long refreshTokenExpiration;
    
    // getters and setters
    
    public String getSecret() {
        return secret;
    }
    
    public void setSecret(String secret) {
        this.secret = secret;
    }
    
    public long getAccessTokenExpiration() {
        return accessTokenExpiration;
    }
    
    public void setAccessTokenExpiration(long accessTokenExpiration) {
        this.accessTokenExpiration = accessTokenExpiration;
    }
    
    public long getRefreshTokenExpiration() {
        return refreshTokenExpiration;
    }
    
    public void setRefreshTokenExpiration(long refreshTokenExpiration) {
        this.refreshTokenExpiration = refreshTokenExpiration;
    }
}