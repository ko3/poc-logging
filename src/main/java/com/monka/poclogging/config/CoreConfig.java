package com.monka.poclogging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.monka.poclogging.config.context.RequestAccessConfigurer;

@Configuration
@Import({RequestAccessConfigurer.class})
public class CoreConfig {
	
	@Bean
	public CustomWebFilter getCustomWebFilter() {
		return new CustomWebFilter();
	}

}
