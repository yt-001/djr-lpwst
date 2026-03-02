package com.xitian.djrlpwst.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置类，用于配置静态资源映射
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Autowired
    private FileUploadConfig fileUploadConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 将 /public/images/** 映射到本地文件路径
        String uploadDir = fileUploadConfig.getUploadDir();
        if (!uploadDir.endsWith("/") && !uploadDir.endsWith("\\")) {
            uploadDir += "/";
        }
        
        // 如果是绝对路径且不以 file: 开头，则补充 file:/// (Windows)
        String resourceLocation = uploadDir;
        if (!resourceLocation.startsWith("file:")) {
            // Windows 绝对路径建议使用 file:///F:/...
            if (resourceLocation.contains(":") && !resourceLocation.startsWith("/")) {
                resourceLocation = "file:///" + resourceLocation;
            } else {
                resourceLocation = "file:" + resourceLocation;
            }
        }

        registry.addResourceHandler("/public/images/**")
                .addResourceLocations(resourceLocation);
    }
}
