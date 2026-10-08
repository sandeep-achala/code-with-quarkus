package org.acme;

import org.CommonResponse.ApiResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    GreetingService greetingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<String> hello() {
        return ApiResponse.success("greeting fetched successfully", greetingService.greet("Sandeep"));
    }

    @GET
    @Path("/greet")
    @Produces(MediaType.APPLICATION_JSON)
    public ApiResponse<String> greet() {
        return ApiResponse.success("greeting fetched successfully", "Greetings from Quarkus REST");
    }
}
