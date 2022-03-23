package org.acme;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.acme.model.Product;
import org.acme.payload.ProductPayload;
import org.acme.repository.ProductRepository;
import org.acme.utils.ApiResponse;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("/v1/product")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@PermitAll
public class ProductResource {

    @Inject
    ProductRepository productRepo;

    @GET
    @Path("/{id}")
    public Response get(@PathParam Long id) {
        try {
            Product product = productRepo.get(id);
            if (product == null)
                return ApiResponse.empty(Status.NOT_FOUND);

            return ApiResponse.ok(product);
        } catch (Exception e) {
            return ApiResponse.error(Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @POST
    public Response create(ProductPayload payload) {
        try {
            Product product = productRepo.create(payload);
            return ApiResponse.ok(product.id);
        } catch (Exception e) {
            return ApiResponse.error(Status.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @PUT
    public Response update(ProductPayload payload) {
        productRepo.update(payload);
        return ApiResponse.ok();
    }

    @DELETE
    @Path("/id")
    public Response delete(Long id) {
        Product product = productRepo.get(id);
        if (product == null)
            return ApiResponse.empty(Status.NOT_FOUND);
        productRepo.delete(product);

        return ApiResponse.ok();
    }
}