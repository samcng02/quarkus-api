package org.acme.repository;

import java.math.BigDecimal;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;

import org.acme.model.Product;
import org.acme.payload.ProductPayload;

@ApplicationScoped
public class ProductRepository extends BaseRepository<Product> {

  @Transactional
  public Product create(ProductPayload payload) {
    Product productModel = new Product();
    productModel.name = payload.name;
    productModel.price = payload.price;
    productModel.code = payload.code;

    productModel.persist();
    return productModel;
  }

  @Transactional
  public Boolean update(ProductPayload payload) {
    Product p = this.findById(payload.id);
    if (p == null)
      throw new NotFoundException("product.not_found");

    p.name = payload.name;
    p.price = payload.price;

    p.persist();
    return true;
  }

  public Product get(Long id) {
    return this.findById(id);
  }
}
