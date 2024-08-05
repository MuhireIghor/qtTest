package com.blogiffy.Blogiffy.services;

import com.blogiffy.Blogiffy.dto.request.UserDto;
import com.blogiffy.Blogiffy.models.User;

import java.util.UUID;

public interface IUserService {
    User register(User user);
    boolean isNotUnique(User user);
    void validateNewRegistration(User user);
    User getLoggedInUser();
    User getUserById(UUID userId);


}
