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
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.util.Assert.notNull;

/**
 * Tests the registration of {@link ModelMapper}.
 *
 * @author Jakub Narloch
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ModelMapperOrderedConfigurerTest.Application.class)
public class ModelMapperOrderedConfigurerTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private List<ModelMapperConfigurer> configurers;

    @Test
    public void shouldInstantiateMapper() {

        notNull(modelMapper, "Model mapper hasn't been created.");
    }

    @Test
    public void shouldInjectOrderedConfigurers() {

        // given
        final List<ModelMapperConfigurer> expectedConfigurersOrder =
                Arrays.asList(Application.STRICT_CONFIGURER, Application.FIRST_MAPPING, Application.SECOND_MAPPING);

        // then
        assertEquals(expectedConfigurersOrder, configurers);
    }

    @EnableAutoConfiguration
    public static class Application {

        public static final ModelMapperConfigurer STRICT_CONFIGURER = new ModelMapperConfigurer() {
            @Override
            public void configure(ModelMapper modelMapper) {
                modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            }
        };

        public static final PropertyMapConfigurerSupport<String, Object> FIRST_MAPPING =
                new PropertyMapConfigurerSupport<String, Object>() {
            @Override
            public PropertyMap<String, Object> mapping() {
                return new PropertyMap<String, Object>() {
                    @Override
                    protected void configure() {
                        // DO SOMETHING
                    }
                };
            }
        };

        public static final PropertyMapConfigurerSupport<String, Object> SECOND_MAPPING =
                new PropertyMapConfigurerSupport<String, Object>() {
            @Override
            public PropertyMap<String, Object> mapping() {
                return new PropertyMap<String, Object>() {
                    @Override
                    protected void configure() {
                        // DO SOMETHING
                    }
                };
            }
        };

        @Order(Ordered.LOWEST_PRECEDENCE)
        @Bean
        public PropertyMapConfigurerSupport<String, Object> secondMapping() {
            return SECOND_MAPPING;
        }

        @Order(Ordered.LOWEST_PRECEDENCE - 1)
        @Bean
        public PropertyMapConfigurerSupport<String, Object> firstMapping() {
            return FIRST_MAPPING;
        }

        @Order(Ordered.HIGHEST_PRECEDENCE)
        @Bean
        public ModelMapperConfigurer strictConfigurer() {
            return STRICT_CONFIGURER;
        }
    }
}