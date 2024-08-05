package com.blogiffy.Blogiffy.serviceImpl;

import com.blogiffy.Blogiffy.dto.request.UserDto;
import com.blogiffy.Blogiffy.exceptions.BadRequestAlertException;
import com.blogiffy.Blogiffy.exceptions.NotFoundException;
import com.blogiffy.Blogiffy.models.User;
import com.blogiffy.Blogiffy.repositories.IUserRepository;
import com.blogiffy.Blogiffy.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository; // Injecting the user repository

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        validateNewRegistration(user); // Validate the new user registration

        return this.userRepository.save(user); // Save the user to the repository and return it
    }

    @Override
    public boolean isNotUnique(User user) {
        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail()); // Check if a user with the given email already exists
        return userOptional.isPresent(); // Return true if the user exists, false otherwise
    }

    @Override
    public void validateNewRegistration(User user) {
        if (isNotUnique(user)) {
            throw new BadRequestAlertException(String.format("User with email '%s' already exists", user.getEmail())); // Throw an exception if the email is not unique
        }
    }

    @Override
    public User getLoggedInUser() {
        // Check if the user is anonymous
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
            throw new BadRequestAlertException("You are not logged in, try to log in"); // Throw an exception if the user is not logged in
        }

        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // Get the email of the logged-in user
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        // Find the user by email or throw an exception if not found
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }

    @Override
    public User getUserById(UUID userId) {
        // Find the user by ID or throw an exception if not found
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User was not found with id: " + userId));
    }
}
