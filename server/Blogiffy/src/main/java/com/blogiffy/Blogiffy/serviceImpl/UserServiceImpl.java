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


    private final IUserRepository userRepository;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        validateNewRegistration(user);

        return this.userRepository.save(user);
    }


    @Override
    public boolean isNotUnique(User user) {
        Optional<User> userOptional = this.userRepository.findByEmail(user.getEmail());
        return userOptional.isPresent();
    }

    @Override
    public void validateNewRegistration(User user) {
        if (isNotUnique(user)) {
            throw new BadRequestAlertException(String.format("User with email '%s'  already exists", user.getEmail()));
        }
    }

    @Override
    public User getLoggedInUser() {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() == "anonymousUser") {
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
            throw new BadRequestAlertException("You are not logged in, try to log in");
        }

        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }

        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }

    @Override
    public User getUserById(UUID userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user was not found with id: " + userId
        ));

    }
}
