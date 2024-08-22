package br.com.ace.authserviceapi.services;

import br.com.ace.authserviceapi.models.RefreshToken;
import br.com.ace.authserviceapi.repositories.RefreshTokenRepository;
import br.com.ace.authserviceapi.security.dtos.UserDetailsDTO;
import br.com.ace.authserviceapi.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import models.exceptions.RefreshTokenExpired;
import models.exceptions.ResourceNotFoundException;
import models.responses.RefreshTokenResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static java.time.LocalDateTime.now;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    @Value("${jwt.expiration-sec.refresh-token}")
    private Long refreshTokenExpirationSec;

    private final RefreshTokenRepository repository;
    private final UserDetailsServiceImpl userDetailsService;
    private final JWTUtils jwtUtils;

    public RefreshToken save(final String username) {
        return repository.save(
                RefreshToken.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(now())
                .expiresAt(now().plusSeconds(refreshTokenExpirationSec))
                .username(username)
                .build()
        );
    }

    public RefreshTokenResponse refreshToken(final String refreshTokenID) {

        final var refreshToken = repository.findById(refreshTokenID)
                .orElseThrow(() -> new ResourceNotFoundException("Refresh token not found with id: " + refreshTokenID));

        if(refreshToken.getExpiresAt().isBefore(now())){
            throw new RefreshTokenExpired("Refresh token expired with id: " + refreshTokenID);
        }

        return new RefreshTokenResponse(jwtUtils.generateToken((UserDetailsDTO) userDetailsService.loadUserByUsername(refreshToken.getUsername())));
    }

}
