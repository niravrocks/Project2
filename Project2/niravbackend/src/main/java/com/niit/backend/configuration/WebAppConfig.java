package com.niit.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
//<mvc:annotation-driven></mvc:annotation-driven>
@EnableWebMvc

//scan the components for creating the beans - controllers, services and repository
@ComponentScan("com.niit")
public class WebAppConfig extends WebMvcConfigurerAdapter{
	//create an instance 
	@Bean
	public InternalResourceViewResolver viewResolver(){
		InternalResourceViewResolver viewResolver=new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

public void addResourceHandlers(ResourceHandlerRegistry registry){
	registry.addResourceHandler("/resources/**").addResourceLocations("/WEB-INF/resources/");
	}
	

}
