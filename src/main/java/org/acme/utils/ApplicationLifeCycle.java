package org.acme.utils;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import org.jboss.logging.Logger;

import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;

@ApplicationScoped
public class ApplicationLifeCycle {

  @Inject
  Logger logger;

  void onStart(@Observes StartupEvent ev) {
    logger.info("The application is starting with profile " + ProfileManager.getActiveProfile());
  }
}