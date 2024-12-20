package com.kosta.bank.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		System.out.println("-------------");
		registry.addMapping("/**")
				.allowedOrigins("http://13.124.129.97:3000")
				.allowedMethods("GET","POST","PUT", "DELETE");
	}
	
}
