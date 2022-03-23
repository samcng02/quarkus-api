package org.acme.utils;

import javax.inject.Singleton;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.jackson.ObjectMapperCustomizer;

import javax.enterprise.inject.Instance;

public class ObjectMapperConfiguration {

  @Singleton
  ObjectMapper objectMapper(Instance<ObjectMapperCustomizer> customizers) {
    // Your own `ObjectMapper` or one provided by another library
    ObjectMapper mapper = new CustomObjectMapper();
    // Apply customizations (includes customizations from Quarkus)
    for (ObjectMapperCustomizer customizer : customizers) {
      customizer.customize(mapper);
    }
    return mapper;
  }
}