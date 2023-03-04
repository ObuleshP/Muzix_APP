package com.example.demo;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.jwtfilter.AuthFilter;

@SpringBootApplication
@EnableDiscoveryClient
@CrossOrigin(origins="*")

public class SpringSecurityApplication {
	
	
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityApplication.class, args);
    }
    
    @Bean
    public FilterRegistrationBean<Filter> jwtFilter() {
        FilterRegistrationBean<Filter> bean =
                new FilterRegistrationBean<>(); //<Filter> change
        bean.setFilter(new AuthFilter());
        bean.addUrlPatterns("/api/v1/*");
        
        return bean;
    }

}