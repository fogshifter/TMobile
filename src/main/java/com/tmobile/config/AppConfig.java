package com.tmobile.config;

import com.tmobile.converter.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import org.modelmapper.ModelMapper;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.tmobile")
public class AppConfig implements WebMvcConfigurer {
    //public class AppConfig extends WebMvcConfigurer  {
    @Bean
    public InternalResourceViewResolver createViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Bean
    public ModelMapper createModelMapper() {
//		return new ModelMapper();
        ModelMapper mapper = new ModelMapper();
        mapper.addConverter(new ContractToContractEntryDTOConverter());
        mapper.addConverter(new ContractToContractInfoDTOConverter());
        mapper.addConverter(new OptionToOptionDTOConverter());
        mapper.addConverter(new TariffToTariffDTOConverter());

        return mapper;
    }
}
