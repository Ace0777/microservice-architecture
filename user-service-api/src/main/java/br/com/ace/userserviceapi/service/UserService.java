package br.com.ace.userserviceapi.service;

import br.com.ace.userserviceapi.entity.User;
import br.com.ace.userserviceapi.mapper.UserMapper;
import br.com.ace.userserviceapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import models.exceptions.ResourceNotFoundException;
import models.requests.CreateUserRequest;
import models.requests.UpadateUserRequest;
import models.responses.UserResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse findById(final String id) {
        return userMapper.fromEntity(find(id));
    }

    public void save(CreateUserRequest createUserRequest) {
        verifyIfEmailALreadyExists(createUserRequest.email(), null);
        userRepository.save(userMapper.toEntity(createUserRequest));
    }

    private void verifyIfEmailALreadyExists(final String email, final String id) {
        userRepository.findByEmail(email)
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new DataIntegrityViolationException("Email ["+email+"] already exists!" );
                });
    }

    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::fromEntity)
                .toList();
    }

    public UserResponse update(final String id, final UpadateUserRequest upadateUserRequest) {
        User entity = find(id);
        verifyIfEmailALreadyExists(upadateUserRequest.email(), id);
        return userMapper.fromEntity(userRepository.save(userMapper.update(upadateUserRequest, entity)));
    }

    private User find(final String id){
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Object not found! Id: " + id + ", Type: " + User.class.getSimpleName()));
    }
}

