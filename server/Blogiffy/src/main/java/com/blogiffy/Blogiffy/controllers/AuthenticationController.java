package com.blogiffy.Blogiffy.controllers;

import com.blogiffy.Blogiffy.dto.request.SignInDto;
import com.blogiffy.Blogiffy.dto.response.ApiResponse;
import com.blogiffy.Blogiffy.dto.response.JWTAuthenticationResponse;
import com.blogiffy.Blogiffy.models.User;
import com.blogiffy.Blogiffy.security.JwtTokenProvider;
import com.blogiffy.Blogiffy.security.UserPrincipal;
import com.blogiffy.Blogiffy.services.IUserService;
import com.blogiffy.Blogiffy.utils.ExceptionUtils;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/auth")
@CrossOrigin
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final IUserService userService;

    @Autowired
    public AuthenticationController(JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, IUserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @Operation(summary = "User authentication", description = "Api end point for user login operation")
    @PostMapping(path = "/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody SignInDto signInDTO) {
        String jwt = null;
        UserPrincipal userPrincipal = null;
        User user = null;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInDTO.getEmail(), signInDTO.getPassword()));
        try {
            SecurityContextHolder.getContext().setAuthentication(authentication);
            jwt = jwtTokenProvider.generateToken(authentication);
            userPrincipal = (UserPrincipal) authentication.getPrincipal();
            user = userService.getUserById(userPrincipal.getId());
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }

        return ResponseEntity.ok(new ApiResponse(true, "User logged in successfully", new JWTAuthenticationResponse(jwt, user)));
    }
}