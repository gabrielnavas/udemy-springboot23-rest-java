package com.api.modules.auth.services;

import com.api.modules.auth.models.User;
import com.api.modules.auth.repositories.UserRepositoryJpa;
import com.api.modules.person.services.CreateUpdatePartialsPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Date;
import java.util.Optional;
import java.util.logging.Logger;

public class UserService implements UserDetailsService {

    private final Logger logger = Logger.getLogger(CreateUpdatePartialsPerson.class.getName());

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    public UserService(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info(String.format("%s - find an user with username=%s", new Date(), username));
        Optional<User> optionalUser = userRepositoryJpa.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException(String.format("user wit username=%s not found", username));
        }
        return optionalUser.get();
    }
}
