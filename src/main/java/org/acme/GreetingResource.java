package org.acme;

import org.CommonResponse.GetResponse;

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
    public String hello() {
        return greetingService.greet("Sandeep");
    }

    @GET
    @Path("/greet")
    @Produces(MediaType.APPLICATION_JSON)
    public GetResponse greet() {
        return new GetResponse("Greetings from Quarkus REST", true);
    }
}
