package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HashService hashService;


    public boolean isUsernameAvailable(String username) {
        return this.userMapper.getUserByUsername(username) != null;
    }

    public int createUser(UserDTO userDTO) {
        byte[] salt = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(salt);
        String encodedSalt = Base64.getEncoder().encodeToString(salt);

        String hashedPassword = hashService.getHashedValue(userDTO.getPassword(), encodedSalt);

        User newUser = new User(
                null,
                userDTO.getUsername(),
                encodedSalt,
                hashedPassword,
                userDTO.getFirstName(),
                userDTO.getLastName());

        return userMapper.createUser(newUser);
    }

    public User getUser(String username) {
        return this.userMapper.getUserByUsername(username);
    }
}