package com.api.modules.auth.services;

import com.api.modules.auth.dtos.AccountCredentialsDto;
import com.api.modules.auth.dtos.Token;
import com.api.modules.auth.models.User;
import com.api.modules.auth.repositories.UserRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AuthService {

    private final Logger logger = Logger.getLogger(AuthService.class.getName());

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    public ResponseEntity<Object> signin(AccountCredentialsDto data) {
        try {
            String username = data.getUsername();
            String password = data.getPassword();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            Optional<User> optionalUser = userRepositoryJpa.findByUsername(username);

            if (optionalUser.isEmpty()) {
                String msg = String.format("username=%s not found", username);
                logger.info(msg);
                throw new UsernameNotFoundException(msg);
            }
            User user = optionalUser.get();
            Token tokenResponse = jwtTokenProvider.createAccessToken(username, user.getRoles());

            return ResponseEntity.status(HttpStatus.OK).body(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("invalid username/password supplied");
        }
    }
}
