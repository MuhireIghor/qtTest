package com.blogiffy.Blogiffy.configs;

import com.blogiffy.Blogiffy.models.Blog;
import com.blogiffy.Blogiffy.utils.BlogSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Jackson configuration file for data serialization into json format
@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();


        // Register custom serializer for Blog class
        SimpleModule module = new SimpleModule();
        module.addSerializer(Blog.class, new BlogSerializer());
        mapper.registerModule(module);
        // Register the JavaTimeModule to handle Java 8 Date/Time types
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }
}
