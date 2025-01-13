package com.novelerp.appbase.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
/*@ComponentScan(basePackages = "org.mseb.etendering")*/
@ComponentScan(basePackages = "com.novelerp.appbase")
public class BaseAppConfig implements WebMvcConfigurer{

}


