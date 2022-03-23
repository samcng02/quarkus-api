package org.acme.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends BaseEntity {
  @Column(name = "name")
  public String name;

  @Column(name = "code")
  public String code;

  @Column(name = "price")
  public BigDecimal price;

  public Product() {
  }
}
