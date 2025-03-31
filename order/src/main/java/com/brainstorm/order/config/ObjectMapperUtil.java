package com.brainstorm.order.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

public class ObjectMapperUtil {
    @Getter
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());  // Register JSR310 Module

    private ObjectMapperUtil() {
        // Private constructor to prevent instantiation
    }

}
