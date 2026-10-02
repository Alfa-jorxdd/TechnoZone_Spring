package com.proyecto.ventas_online.infrastructure.mappers;

import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.dto.user.*;
import com.proyecto.ventas_online.infrastructure.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity userEntity){
        return User.reconstitute(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLastname(),
                userEntity.getEmail(),
                userEntity.getPasswordHash(),
                userEntity.getRole()
        );
    }

    public UpdateUserCommand toCommand(UpdateUserRequest request) {
        return new UpdateUserCommand(request.id(), request.name(), request.lastname(),
                request.email(), request.password(), request.role());
    }

    public UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getLastname(),
                user.getEmail(),
                user.getRole()
        );
    }

    public UserEntity toEntity(User user){
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .passwordHash(user.getPasswordHash())
                .role(user.getRole())
                .build();
    }

    public CreateUserCommand toCommand(CreateUserRequest request){
        return new CreateUserCommand(
                request.name(),
                request.lastname(),
                request.email(),
                request.password(),
                request.role()
        );
    }
}
