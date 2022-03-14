package com.algalog.api.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configa o model mapper para injeção de dependencia
 * 
 * @author dkalbiak
 *
 */
@Configuration
public class ConfigModelMapperUtil {

	@Bean
	public ModelMapper madelMapper() {
		return new ModelMapper();
	}

}
