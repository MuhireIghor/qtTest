package com.blogiffy.Blogiffy.security;


import com.blogiffy.Blogiffy.exceptions.BadRequestAlertException;
import com.blogiffy.Blogiffy.models.User;
import com.blogiffy.Blogiffy.repositories.IUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final IUserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDetails loadByUserId(UUID id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + id));
        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserByUsername(String s) throws BadRequestAlertException {
        User user = userRepository.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException("user not found with email of " + s));

        return UserPrincipal.create(user);
    }
}
