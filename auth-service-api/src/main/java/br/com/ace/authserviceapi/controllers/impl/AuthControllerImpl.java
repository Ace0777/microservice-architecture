package br.com.ace.authserviceapi.controllers.impl;


import br.com.ace.authserviceapi.controllers.AuthController;
import jakarta.validation.Valid;
import models.requests.AuthenticateRequest;
import models.responses.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthControllerImpl implements AuthController {

    @Override
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody final AuthenticateRequest request) {
        return ResponseEntity.ok(AuthenticationResponse.builder()
                        .type("Bearer")
                        .token("token")
                .build());
    }
}
