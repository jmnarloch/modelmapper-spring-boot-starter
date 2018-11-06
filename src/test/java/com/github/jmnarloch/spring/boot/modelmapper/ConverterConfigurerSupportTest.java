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
import com.github.jmnarloch.spring.boot.modelmapper.dto.UserDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.util.Assert.notNull;

/**
 * Tests the {@link ConverterConfigurerSupport} class.
 *
 * @author Jakub Narloch
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = ConverterConfigurerSupportTest.Application.class)
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
        final UserDto result = modelMapper.map(user, UserDto.class);

        // then
        Assert.assertEquals("John", result.getFirstName());
        Assert.assertEquals("Doe", result.getLastName());
    }

    @EnableAutoConfiguration
    public static class Application {

        @Bean
        public ConverterConfigurerSupport<User, UserDto> userConverter() {
            return new ConverterConfigurerSupport<User, UserDto>() {
                @Override
                protected Converter<User, UserDto> converter() {
                    return new AbstractConverter<User, UserDto>() {

                        @Override
                        protected UserDto convert(User source) {
                            String[] parts = source.getName().split(" ");
                            return new UserDto(parts[0], parts[1]);
                        }
                    };
                }
            };
        }
    }
}