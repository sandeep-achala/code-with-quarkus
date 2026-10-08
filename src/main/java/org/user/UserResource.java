package org.user;

import java.util.List;

import org.CommonResponse.ApiResponse;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService userService;

    @GET
    public ApiResponse<List<UserResponse>> getAll() {
        return ApiResponse.success("users fetched successfully", userService.getAll());
    }

    @POST
    public Response create(UserCreateRequest request) {
        UserResponse createdUser = userService.create(request);
        return Response.status(Response.Status.CREATED)
                .entity(ApiResponse.success("user created successfully", createdUser))
                .build();
    }
}
