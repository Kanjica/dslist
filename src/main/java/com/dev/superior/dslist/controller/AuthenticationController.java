package com.dev.superior.dslist.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.superior.dslist.dto.AuthenticationDTO;
import com.dev.superior.dslist.dto.LoginResponseDTO;
import com.dev.superior.dslist.dto.RegisterDTO;
import com.dev.superior.dslist.repository.UserRepository;
import com.dev.superior.dslist.service.TokenService;
import com.dev.superior.dslist.users.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    AuthenticationManager authenticationManager;

    UserRepository repository;

    TokenService tokenService;
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository repository, TokenService tokenService){
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login() , data.password());
        var auth = authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterDTO data){
        if(repository.findByLogin(data.login()) != null) return ResponseEntity.badRequest().build();
        
        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());

        User newUser = new User(data.login(), encryptedPassword, data.role());
        this.repository.save(newUser);
        
        return ResponseEntity.ok().build();
    }
}
