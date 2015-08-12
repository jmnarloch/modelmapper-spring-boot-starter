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

import org.junit.Test;
import org.modelmapper.ModelMapper;

import static org.junit.Assert.assertNotNull;

/**
 * Tests the registration of {@link ModelMapper}.
 *
 * @author Jakub Narloch
 */
public class ModelMapperFactoryBeanTest {

    @Test
    public void shouldInstantiateModelMapper() throws Exception {

        // given
        final ModelMapperFactoryBean factoryBean = new ModelMapperFactoryBean();

        // when
        final ModelMapper modelMapper = factoryBean.getObject();

        // then
        assertNotNull("Result was null", modelMapper);
    }
}