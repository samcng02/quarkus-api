package org.acme.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vladmihalcea.hibernate.type.array.ListArrayType;
import com.vladmihalcea.hibernate.type.json.JsonType;

import org.acme.utils.CustomObjectMapper;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.UpdateTimestamp;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@TypeDef(name = "json", typeClass = JsonType.class)
@TypeDef(name = "list-array", typeClass = ListArrayType.class)
@MappedSuperclass
public class BaseEntity extends PanacheEntityBase {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;

  @UpdateTimestamp
  @Column(name = "modified_at")
  public LocalDateTime modifiedAt;

  public BaseEntity() {
  }

  public String toJSONString() throws JsonProcessingException {
    ObjectMapper mapper = new CustomObjectMapper(true, true);
    return mapper.writeValueAsString(this);
  }
}
