package br.com.ace.userserviceapi.service;

import br.com.ace.userserviceapi.entity.User;
import br.com.ace.userserviceapi.mapper.UserMapper;
import br.com.ace.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.responses.UserResponse;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse findById(final String id) {
        return userMapper.fromEntity(
                userRepository.findById(id).
                        orElseThrow(() -> new ResourceNotFoundException(
                                "Object not found! Id: " + id + ", Type: " + UserResponse.class.getSimpleName()))
        );
    }

    public void save(CreateUserRequest createUserRequest) {
        userRepository.save(userMapper.toEntity(createUserRequest));
    }
}

