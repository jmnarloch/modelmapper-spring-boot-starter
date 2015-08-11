package com.github.jmnarloch.spring.boot.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The {@code ModelMapper} auto configuration. It requires that the model mapper will be on classpath and afterwards
 * it's going to register {@link ModelMapperFactoryBean} within the application context.
 *
 * @author Jakub Narloch
 * @see ModelMapper
 * @see ModelMapperFactoryBean
 */
@Configuration
@ConditionalOnClass(ModelMapper.class)
public class ModelMapperAutoConfiguration {

    /**
     * Defines the {@link ModelMapperFactoryBean} instance.
     *
     * @return {@link ModelMapperFactoryBean}
     */
    @Bean
    @ConditionalOnMissingBean(ModelMapperFactoryBean.class)
    public ModelMapperFactoryBean modelMapperFactoryBean() {

        return new ModelMapperFactoryBean();
    }
}
