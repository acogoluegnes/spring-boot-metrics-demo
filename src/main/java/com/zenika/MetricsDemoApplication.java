package com.zenika;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class MetricsDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MetricsDemoApplication.class, args);
    }

    @Configuration
    public static class MetricsConfiguration extends WebMvcConfigurerAdapter {

        @Override
        public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(metricsHandlerInterceptor());

        }

        @Bean public HandlerInterceptor metricsHandlerInterceptor() {
            return new MetricsHandlerInterceptor();
        }

    }
}
