package com.xitian.djrlpwst.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CookieProperties {
    
    @Value("${cookie.access-token-expiry:18000}")
    private int accessTokenExpiry;
    
    @Value("${cookie.refresh-token-expiry:604800}")
    private int refreshTokenExpiry;
    
    // getters and setters
    
    public int getAccessTokenExpiry() {
        return accessTokenExpiry;
    }
    
    public void setAccessTokenExpiry(int accessTokenExpiry) {
        this.accessTokenExpiry = accessTokenExpiry;
    }
    
    public int getRefreshTokenExpiry() {
        return refreshTokenExpiry;
    }
    
    public void setRefreshTokenExpiry(int refreshTokenExpiry) {
        this.refreshTokenExpiry = refreshTokenExpiry;
    }
}