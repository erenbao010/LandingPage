package com.atcollabo;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class ConfigWeb implements WebMvcConfigurer {

		@Value("${com.atcollabo.uploadpath}")
		private String uploadPath;

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
        	File path = new File(uploadPath);
        	log.debug(path.getAbsolutePath());
        	int cacheperiod = (60*60*24*7);
            registry.addResourceHandler("/uploads/**").addResourceLocations("file:"+path.getAbsolutePath()).setCachePeriod(cacheperiod);
            registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/").setCachePeriod(cacheperiod);
            registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/favicon/favicon.ico").setCachePeriod(cacheperiod);
            registry.addResourceHandler("/html/**").addResourceLocations("classpath:/static/html").setCachePeriod(cacheperiod);
        }
        @Override
        public void addCorsMappings(CorsRegistry registry) {
        	registry.addMapping("/api/**").allowedOrigins("http://localhost","https://atcollabo.com");
        }

}
