package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService {

    @Autowired
    private UserService userService;


    public boolean signupUser(UserDTO userDTO) {

        String username = userDTO.getUsername();

        if (this.userService.isUsernameAvailable(username)) {
            return false;
        }

        this.userService.createUser(userDTO);

        return true;
    }
}