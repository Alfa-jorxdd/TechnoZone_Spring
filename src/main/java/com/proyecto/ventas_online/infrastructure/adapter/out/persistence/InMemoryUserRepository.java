package com.proyecto.ventas_online.infrastructure.adapter.out.persistence;

import com.proyecto.ventas_online.application.ports.out.UserRepositoryPort;
import com.proyecto.ventas_online.domain.factory.DefaultUserFactory;
import com.proyecto.ventas_online.domain.model.User;
import com.proyecto.ventas_online.infrastructure.mappers.UserMapper;
import com.proyecto.ventas_online.infrastructure.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryUserRepository implements UserRepositoryPort {

    private final UserMapper userMapper;
    private final HashMap<UUID, UserEntity> repositoryUser = new HashMap<>();

    public InMemoryUserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
        DefaultUserFactory userFactory = new DefaultUserFactory();
        userFactory.createDefaultUsers().forEach(this::saveUser);
    }

    @Override
    public Optional<User> findUserById(UUID id) {
        return Optional.ofNullable(repositoryUser.get(id))
                .map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return repositoryUser.values().stream()
                .filter(userEntity -> userEntity.getEmail().equals(email))
                .map(userMapper::toDomain)
                .findFirst();
    }

    @Override
    public User saveUser(User userBySave) {
        UserEntity userEntityBySave = userMapper.toEntity(userBySave);
        repositoryUser.put(userEntityBySave.getId(), userEntityBySave);
        return userBySave;
    }

    @Override
    public User deleteUser(UUID id) {
        UserEntity userDeleted = repositoryUser.remove(id);
        return userMapper.toDomain(userDeleted);
    }

    @Override
    public User updateUser(User userByUpdate) {
        UserEntity userUpdated = userMapper.toEntity(userByUpdate);
        repositoryUser.put(userUpdated.getId(), userUpdated);
        return userByUpdate;
    }

    @Override
    public List<User> getAll() {
        return repositoryUser
                .values().stream().map(userMapper::toDomain).toList();
    }
}
