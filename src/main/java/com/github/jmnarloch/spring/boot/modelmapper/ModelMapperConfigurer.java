package com.github.jmnarloch.spring.boot.modelmapper;

import org.modelmapper.ModelMapper;

/**
 * Allows to set specific behaviour of the {@link ModelMapper} instance. The concrete implementation of this interface
 * needs to be registered within the application context in order to be processed.
 *
 * <code>
 * <pre><strong>Example:</strong>
 * &#064;Component
 * public class UserMapping implements ModelMapperConfigurer {
 *
 *      void configure(ModelMapper modelMapper) {
 *          modelMapper.getConfiguration()
 *              .setSourceNamingConvention(NamingConventions.NONE);
 *              .setDestinationNamingConvention(NamingConventions.NONE);
 *      }
 * }
 * </pre>
 * </code>
 *
 * @author Jakub Narloch
 * @see ModelMapper
 * @see ModelMapperFactoryBean
 */
public interface ModelMapperConfigurer {

    /**
     * Configures the instance of {@link org.modelmapper.ModelMapper}.
     *
     * @param modelMapper {@link ModelMapper} instance to be configured
     */
    void configure(ModelMapper modelMapper);
}
