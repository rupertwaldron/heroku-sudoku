package com.ruppyrup.herokusudoku.config;

import com.ruppyrup.herokusudoku.util.ViewNames;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // == bean methods ==
    @Bean
    public LocaleResolver localeResolver() {
        return new SessionLocaleResolver();
    }

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName(ViewNames.SUDOKU);
    }

}
