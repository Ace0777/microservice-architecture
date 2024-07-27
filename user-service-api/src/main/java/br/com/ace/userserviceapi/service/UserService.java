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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;
    private final BCryptPasswordEncoder encoder;

    public UserResponse findById(final String id) {
        return mapper.fromEntity(find(id));
    }

    public void save(CreateUserRequest request) {
        verifyIfEmailALreadyExists(request.email(), null);
        repository.save(mapper.toEntity(request)
                .withPassword(encoder.encode(request.password()))
                );
    }

    private void verifyIfEmailALreadyExists(final String email, final String id) {
        repository.findByEmail(email)
                .filter(user -> !user.getId().equals(id))
                .ifPresent(user -> {
                    throw new DataIntegrityViolationException("Email ["+email+"] already exists!" );
                });
    }

    public List<UserResponse> findAll() {
        return repository.findAll().stream()
                .map(mapper::fromEntity)
                .toList();
    }

    public UserResponse update(final String id, final UpadateUserRequest request) {
        User entity = find(id);
        verifyIfEmailALreadyExists(request.email(), id);
        return mapper.fromEntity(repository.save(
                mapper.update(request, entity)
                        .withPassword(request.password()!= null ? encoder.encode(request.password()) : entity.getPassword())
        ));
    }

    private User find(final String id){
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Object not found! Id: " + id + ", Type: " + User.class.getSimpleName()));
    }
}

