package com.tms.api.config;

import com.tms.api.service.BaseService;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    private final List<BaseService> baseServices;

    public WebMvcConfig(List<BaseService> baseServices) {
        this.baseServices = baseServices;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // Only add 1 baseService -> Reload config 1 time
        registry.addInterceptor(baseServices.get(0));
    }
}