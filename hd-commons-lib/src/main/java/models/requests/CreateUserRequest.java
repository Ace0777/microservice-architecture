package models.requests;

import lombok.With;

import java.util.Set;

@With
public record CreateUserRequest(
        String name,
        String email,
        String password,
        Set<String> profiles
) { }
