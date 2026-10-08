package org.user;

import java.util.List;

import org.CommonResponse.ConflictException;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class UserService {

    @Transactional
    public UserResponse create(UserCreateRequest request) {
        if (request.email != null && User.count("email", request.email) > 0) {
            throw new ConflictException("email already exists");
        }
        if (request.phoneNumber != null && User.count("phoneNumber", request.phoneNumber) > 0) {
            throw new ConflictException("phone number already exists");
        }

        User user = new User();
        
        user.name = request.name;
        user.email = request.email;
        user.phoneNumber = request.phoneNumber;
        user.setPassword(request.password);
        user.persist();
        return UserResponse.from(user);
    }

    public List<UserResponse> getAll() {
        return User.<User>listAll().stream()
                .map(UserResponse::from)
                .toList();
    }
}
