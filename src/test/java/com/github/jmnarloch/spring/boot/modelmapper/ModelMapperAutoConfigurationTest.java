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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.util.Assert.notNull;

/**
 * Tests the registration of {@link ModelMapper}.
 *
 * @author Jakub Narloch
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ModelMapperAutoConfigurationTest.Application.class)
public class ModelMapperAutoConfigurationTest {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private List<ModelMapperConfigurer> configurers;

    @Test
    public void shouldInstantiateMapper() {

        notNull(modelMapper, "Model mapper hasn't been created.");
    }

    @Test
    public void shouldInvokeConfigurer() {

        for(ModelMapperConfigurer configurer : configurers) {
            verify(configurer).configure(any(ModelMapper.class));
        }
    }

    @EnableAutoConfiguration
    public static class Application {

        @Bean
        public ModelMapperConfigurer modelMapperConfigurer() {
            return mock(ModelMapperConfigurer.class);
        }
    }
}