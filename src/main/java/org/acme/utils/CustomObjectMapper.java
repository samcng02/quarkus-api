package org.acme.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class CustomObjectMapper extends ObjectMapper {

  public CustomObjectMapper() {
    this(true, false, false);
  }

  public CustomObjectMapper(boolean nonNull, boolean nonEmpty) {
    this(nonNull, nonEmpty, false);
  }

  public CustomObjectMapper(boolean nonNull, boolean nonEmpty, boolean failOnUnknownProperties) {
    this.registerModule(new JavaTimeModule());
    this.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    if (nonNull)
      this.setSerializationInclusion(Include.NON_NULL);
    if (nonEmpty)
      this.setSerializationInclusion(Include.NON_EMPTY);
    if (failOnUnknownProperties)
      this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }
}
