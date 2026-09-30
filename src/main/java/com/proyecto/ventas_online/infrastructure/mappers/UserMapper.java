package com.proyecto.ventas_online.infrastructure.mappers;

import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.model.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toDomain(UserEntity userEntity){
        if (userEntity == null){
            return null;
        }
        return User.reconstitute(
                userEntity.getId(),
                userEntity.getName(),
                userEntity.getLastname(),
                userEntity.getEmail(),
                userEntity.getPasswordHash(),
                userEntity.getRole()
        );
    }

    public UserEntity toEntity(User user){
        if (user == null){
            return null;
        }
        return UserEntity.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .email(user.getEmail())
                .passwordHash(user.getPasswordHash())
                .role(user.getRole())
                .build();
    }
}
