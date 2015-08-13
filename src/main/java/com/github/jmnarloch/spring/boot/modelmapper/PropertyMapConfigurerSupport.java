/**
 * Copyright (c) 2015 the original author or authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
    public abstract PropertyMap<S, D> mapping();

    /**
     * Configures the passed {@link ModelMapper} instance by registering the {@link PropertyMap} defined by
     * {@link #mapping()} method.
     *
     * @param modelMapper {@link ModelMapper} instance to be configured
     */
    @Override
    public void configure(ModelMapper modelMapper) {

        modelMapper.addMappings(mapping());
    }
}
