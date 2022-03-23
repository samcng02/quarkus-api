package org.acme.repository;

import org.acme.model.BaseEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

public abstract class BaseRepository<Entity> implements PanacheRepository<Entity> {

  public void myPersist(Entity entity) {
    BaseEntity baseEntity = (BaseEntity) entity;
    persist(entity);
  }
}
