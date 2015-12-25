# Spring Boot ModelMapper starter

> A Spring Boot starter that will help you configure [ModelMapper](http://modelmapper.org) within the application context. 

[![Build Status](https://travis-ci.org/jmnarloch/modelmapper-spring-boot-starter.svg?branch=master)](https://travis-ci.org/jmnarloch/modelmapper-spring-boot-starter)
[![Coverage Status](https://coveralls.io/repos/jmnarloch/modelmapper-spring-boot-starter/badge.svg?branch=master&service=github)](https://coveralls.io/github/jmnarloch/modelmapper-spring-boot-starter?branch=master)

## Features

Registers the ModelMapper as a Spring bean and allows to configure it and register specific object mappings. 

## Setup

In order to add ModelMapper to your project simply add this dependency to your classpath:

```xml
<dependency>
    <groupId>com.github.jmnarloch</groupId>
    <artifactId>modelmapper-spring-boot-starter</artifactId>
    <version>1.1.0</version>
</dependency>
```

## Additional configuration

If you need to set additonal configuration options simply register within Spring application context instance of 
`ModelMapperConfigurer`

```java

@Component
public class CustomConfigurer implements ModelMapperConfigurer {
 
       void configure(ModelMapper modelMapper) {
           modelMapper.getConfiguration()
               .setSourceNamingConvention(NamingConventions.NONE);
               .setDestinationNamingConvention(NamingConventions.NONE);
       }
  }
  
```

## Overriding the default mapping

Similarly registering the mapping can be performed through extending `PropertyMapConfigurerSupport` class.

```java

@Component
public class UserDtoMapping extends PropertyMapConfigurerSupport<User, UserDto> {

   @Override
   public PropertyMap<User, UserDto> mapping() {

       return new PropertyMap<User, UserDto>() {
           @Override
           protected void configure() {
               map().setName(source.getFirstName());
           }
       };
   }
}
  
```

Additionally custom ModelMapper's converters can be registered:

```java

@Component
public class UserDtoConverter extends ConverterConfigurerSupport<User, UserDTO> {
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
}

```


## License

Apache 2.0