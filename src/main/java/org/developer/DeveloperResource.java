package org.developer;

import java.util.List;

import org.CommonResponse.ApiResponse;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/developers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DeveloperResource {

    // 1. READ (Get All)
    @GET
    public ApiResponse<List<Developer>> getAll() {
        System.out.println("Fetching all developers...");
        return ApiResponse.success("developers fetched successfully", Developer.listAll());
    }

    // 1b. READ (Get by ID)
    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Long id) {
        System.out.println("Fetching developer with ID: " + id);
        Developer developer = Developer.findById(id);
        if (developer == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ApiResponse.failure("developer not found")).build();
        }
        return Response.ok(ApiResponse.success("developer fetched successfully", developer)).build();
    }

    // 2. CREATE
    @POST
    @Transactional
    public Response create(Developer developer) {
        developer.persist();
        return Response.status(Response.Status.CREATED)
                .entity(ApiResponse.success("developer created successfully", developer)).build();
    }

    // 3. UPDATE
    @PUT
    @Path("/{id}")
    @Transactional
    public Response update(@PathParam("id") Long id, Developer updatedDeveloper) {
        Developer entity = Developer.findById(id);
        if (entity == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ApiResponse.failure("developer not found")).build();
        }
        
        // Update fields
        entity.name = updatedDeveloper.name;
        entity.programmingLanguage = updatedDeveloper.programmingLanguage;
        
        return Response.ok(ApiResponse.success("developer updated successfully", entity)).build();
    }

    // 4. DELETE
    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(@PathParam("id") Long id) {
        boolean deleted = Developer.deleteById(id);
        
        if (deleted) {
            return Response.ok(ApiResponse.success("developer deleted successfully", null)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity(ApiResponse.failure("developer not found")).build();
    }
}