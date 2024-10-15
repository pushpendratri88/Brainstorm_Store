package com.brainstorm.stock.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class ObjectMapperUtil {
    private static final ObjectMapper mapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());  // Register JSR310 Module

    private ObjectMapperUtil() {
        // Private constructor to prevent instantiation
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }
}
