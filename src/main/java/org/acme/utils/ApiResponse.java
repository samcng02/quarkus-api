package org.acme.utils;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.MediaType;

public final class ApiResponse {

  public static Response empty(Status status) {
    return Response.status(status).build();
  }

  public static Response ok() {
    return Response.status(Status.OK).build();
  }

  public static Response ok(String message) {
    return Response.ok().entity((new ApiResponseBody(message))).type(MediaType.APPLICATION_JSON).build();
  }

  public static Response ok(Object data) {
    return Response.ok().entity(new ApiResponseBody(data)).type(MediaType.APPLICATION_JSON).build();
  }

  public static Response ok(String message, Object data) {
    return Response.ok().entity(new ApiResponseBody(message, data)).type(MediaType.APPLICATION_JSON).build();
  }

  public static Response error(Status status, String message) {
    if (status == Status.OK) {
      status = Status.INTERNAL_SERVER_ERROR;
    }
    return Response.status(status).entity((new ApiResponseBody(message))).type(MediaType.APPLICATION_JSON).build();
  }

  public static Response error(Status status, Object data) {
    if (status == Status.OK) {
      status = Status.INTERNAL_SERVER_ERROR;
    }
    return Response.status(status).entity(new ApiResponseBody(data)).type(MediaType.APPLICATION_JSON).build();
  }

  public static Response error(Status status, String message, Object data) {
    if (status == Status.OK) {
      status = Status.INTERNAL_SERVER_ERROR;
    }
    return Response.status(status).entity(new ApiResponseBody(message, data)).type(MediaType.APPLICATION_JSON).build();
  }

  private ApiResponse() {

  }

  public static class ApiResponseBody {
    public String message;
    public Object data;

    public ApiResponseBody() {

    }

    public ApiResponseBody(String message, Object data) {
      this.message = message;
      this.data = data;
    }

    public ApiResponseBody(String message) {
      this.message = message;
    }

    public ApiResponseBody(Object data) {
      this.data = data;
    }
  }
}