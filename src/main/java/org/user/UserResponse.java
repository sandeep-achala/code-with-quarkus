package org.user;


public record UserResponse(long id, String name, String email, String phoneNumber) {
    public static UserResponse from(User user) {
        return new UserResponse(user.id, user.name, user.email, user.phoneNumber);
    }
}
