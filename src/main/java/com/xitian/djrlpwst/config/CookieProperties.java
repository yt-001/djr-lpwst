package com.xitian.djrlpwst.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class CookieProperties {
    
    @Value("${cookie.access-token-expiry:18000}")
    private int accessTokenExpiry;
    
    @Value("${cookie.refresh-token-expiry:604800}")
    private int refreshTokenExpiry;
}