package com.github.jmnarloch.spring.boot.modelmapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

/**
 * An {@link PropertyMap} model mapper configurer. This template method class allows to specify
 * the {@link PropertyMap} that will be registered within the model mapper.
 *
 * @param <S> the source object type
 * @param <D> the destination object type
 *
 * @author Jakub Narloch
 */
public abstract class PropertyMapConfigurerSupport<S, D> implements ModelMapperConfigurer {

    /**
     * Allows to specify the specific property mapping between two different objects.
     *
     * @return the property map
     */
    protected abstract PropertyMap<S, D> propertyMap();

    /**
     * Configures
     *
     * @param modelMapper {@link ModelMapper} instance to be configured
     */
    @Override
    public void configure(ModelMapper modelMapper) {

        modelMapper.addMappings(propertyMap());
    }
}
