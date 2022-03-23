package org.acme.utils;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

import org.eclipse.microprofile.config.inject.ConfigProperty;

@Provider
public class CorsFilter implements ContainerResponseFilter {

  @ConfigProperty(name = "quarkus.http.cors.origins", defaultValue = "*")
  String origins;

  @ConfigProperty(name = "quarkus.http.cors.methods", defaultValue = "*")
  String methods;

  @ConfigProperty(name = "quarkus.http.cors.headers", defaultValue = "*")
  String headers;

  @ConfigProperty(name = "quarkus.http.cors.access-control-allow-credentials",
      defaultValue = "true")
  String allowCredentials;

  @ConfigProperty(name = "quarkus.http.cors.access-control-max-age", defaultValue = "24H")
  String maxAge;

  @Override
  public void filter(ContainerRequestContext requestContext,
      ContainerResponseContext responseContext) throws IOException {

    String referer = requestContext.getHeaderString("Referer");
    if (referer != null && referer.endsWith("/")) {
      referer = referer.substring(0, referer.length() - 1);
    }

    List<String> orgininList =
        Stream.of(origins.split(",")).map(String::trim).collect(Collectors.toList());
    if (origins.equals("*") || orgininList.contains(referer)) {
      responseContext.getHeaders().add("Access-Control-Allow-Origin", referer);
      responseContext.getHeaders().add("Access-Control-Allow-Methods", methods);
      responseContext.getHeaders().add("Access-Control-Allow-Headers", headers);
      responseContext.getHeaders().add("Access-Control-Allow-Credentials", allowCredentials);
    }
  }
}
