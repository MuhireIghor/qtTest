package com.blogiffy.Blogiffy.controllers;

import com.blogiffy.Blogiffy.dto.request.SignUpDto;
import com.blogiffy.Blogiffy.dto.response.ApiResponse;
import com.blogiffy.Blogiffy.enums.ERole;
import com.blogiffy.Blogiffy.models.User;
import com.blogiffy.Blogiffy.services.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@CrossOrigin

public class UserController {

    public final IUserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

//    @Operation(summary = "User Creation", description = "Api Endpoint for registering a new user to the system")
//    @PostMapping("/create")
//    public ResponseEntity<ApiResponse> registerUser(@RequestBody User user) {
//        return ResponseEntity.ok(new ApiResponse(true, "User registered successfully", userService.register(user)));
//    }

    @Operation(summary = "Get Logged in User", description = "Api Endpoint for getting the currently logged in User")

    @GetMapping(path = "/current-user")
    public ResponseEntity<ApiResponse> currentlyLoggedInUser() {
        return ResponseEntity.ok(new ApiResponse(true, userService.getLoggedInUser()));
    }

    @Operation(summary = "Register user as reader", description = "Api Endpoint for registering a new user as a reader in the system")
    @PostMapping(path = "/register/as-reader")
    public ResponseEntity<ApiResponse> registerAsReader(@Valid @RequestBody SignUpDto dto) {

        User user = new User();

        String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());

        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setRole(ERole.COMMENTER);
        user.setPassword(encodedPassword);

        User entity = this.userService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Commenter Created successfully ", entity));
    }

    @Operation(summary = "Register a new user as a publisher", description = "Api Endpoint for registering a new user as a publisher")
    @PostMapping(path = "/register/as-publisher")
    public ResponseEntity<ApiResponse> registerAsPublisher(@Valid @RequestBody SignUpDto dto) {

        User user = new User();

        String encodedPassword = bCryptPasswordEncoder.encode(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setRole(ERole.PUBLISHER);
        user.setPassword(encodedPassword);

        User entity = this.userService.register(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "Publisher created successfully", entity));
    }


}
