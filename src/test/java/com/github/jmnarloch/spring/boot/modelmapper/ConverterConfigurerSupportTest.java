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

import com.github.jmnarloch.spring.boot.modelmapper.domain.User;
import com.github.jmnarloch.spring.boot.modelmapper.dto.UserDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.util.Assert.notNull;

/**
 * Tests the {@link ConverterConfigurerSupport} class.
 *
 * @author Jakub Narloch
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConverterConfigurerSupportTest.Application.class)
public class ConverterConfigurerSupportTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void shouldInstantiateMapper() {

        notNull(modelMapper, "Model mapper hasn't been created.");
    }

    @Test
    public void shouldMapUserEntity() {

        // given
        final User user = new User("John Doe");

        // when
        final UserDTO result = modelMapper.map(user, UserDTO.class);

        // then
        Assert.assertEquals("John", result.getFirstName());
        Assert.assertEquals("Doe", result.getLastName());
    }

    @EnableAutoConfiguration
    public static class Application {

        @Bean
        public ConverterConfigurerSupport<User, UserDTO> userConverter() {
            return new ConverterConfigurerSupport<User, UserDTO>() {
                @Override
                protected Converter<User, UserDTO> converter() {
                    return new AbstractConverter<User, UserDTO>() {

                        @Override
                        protected UserDTO convert(User source) {
                            String[] parts = source.getName().split(" ");
                            return new UserDTO(parts[0], parts[1]);
                        }
                    };
                }
            };
        }
    }
}