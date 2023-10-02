package com.api.services;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class PasswordEncoderBCrypt implements PasswordEncoder {

    private final Logger logger = Logger.getLogger(PasswordEncoderBCrypt.class.getName());

    @Override
    public String encode(CharSequence rawPassword) {
        logger.info("create a password");
        return BCrypt.hashpw(rawPassword.toString(), BCrypt.gensalt());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        logger.info("matches the password");
        return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
    }
}
