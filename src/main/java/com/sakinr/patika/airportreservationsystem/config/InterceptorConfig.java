package com.sakinr.patika.airportreservationsystem.config;

import com.sakinr.patika.airportreservationsystem.aop.AirportInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    private final AirportInterceptor airportInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(airportInterceptor).addPathPatterns("/api/airport/*");
    }

}
