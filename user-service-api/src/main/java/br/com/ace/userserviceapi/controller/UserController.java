package br.com.ace.userserviceapi.controller;

import br.com.ace.userserviceapi.entity.User;
import models.responses.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/api/user")
public interface UserController {

    @GetMapping("/{id}")
    ResponseEntity<UserResponse> findById(@PathVariable(name = "id") final String id);

}
