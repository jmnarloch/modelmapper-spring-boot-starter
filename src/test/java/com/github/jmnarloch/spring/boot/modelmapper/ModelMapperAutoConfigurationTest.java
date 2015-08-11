package com.github.jmnarloch.spring.boot.modelmapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

/**
 * Tests the registration of {@link ModelMapper}.
 *
 * @author Jakub Narloch
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ModelMapperAutoConfigurationTest.Application.class)
public class ModelMapperAutoConfigurationTest {

    @Autowired
    private ModelMapper modelMapper;

    @Test
    public void shouldInstantiateMapper() {

        Assert.notNull(modelMapper, "Model mapper hasn't been created.");
    }

    @SpringBootApplication
    public static class Application {

    }
}