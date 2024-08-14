package br.com.ace.authserviceapi.controllers.impl;


import br.com.ace.authserviceapi.controllers.AuthController;
import br.com.ace.authserviceapi.security.dtos.JWTAuthenticationimpl;
import br.com.ace.authserviceapi.utils.JWTUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import models.requests.AuthenticateRequest;
import models.responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthControllerImpl implements AuthController {


    private final JWTUtils jwtUtils;
    private final AuthenticationConfiguration authenticationConfiguration;


    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(final AuthenticateRequest request) throws Exception {
        return ResponseEntity.ok().body(
                new JWTAuthenticationimpl(jwtUtils, authenticationConfiguration.getAuthenticationManager()).authenticate(request)
        );
    }
}
