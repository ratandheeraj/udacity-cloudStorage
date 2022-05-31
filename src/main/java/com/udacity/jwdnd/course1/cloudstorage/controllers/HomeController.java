package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserCredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.models.UserNoteDTO;
import com.udacity.jwdnd.course1.cloudstorage.models.UserDTO;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthorizationService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    private Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private AuthorizationService authorizationService;
    @Autowired
    private NoteService noteService;
    @Autowired
    private CredentialService credentialService;
    @Autowired
    private FileService fileService;


    @GetMapping("/home")
    public String getHomepage(
            @ModelAttribute("userNoteDto") UserNoteDTO userNoteDTO,
            @ModelAttribute("userCredentialDto") UserCredentialDTO userCredentialDTO,
            Authentication authentication,
            Model model
    ) {
        String username = (String) authentication.getPrincipal();
        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("noteList", this.noteService.getNotesByUsername(username));
        dataMap.put("credentialList", this.credentialService.getCredentialsByUsername(username));
        dataMap.put("fileList", this.fileService.getFilesByUser(username));

        model.addAllAttributes(dataMap);

        return "home";
    }

    @GetMapping("/logout")
    public String logOut(
            @ModelAttribute("userDto") UserDTO userDTO,
            Model model
    ) {

        this.logger.error("logout");

        return this.loginPage(userDTO, false, true, model);
    }

    @GetMapping("/login")
    public String loginPage(
            @ModelAttribute("userDto") UserDTO userDTO,
            @RequestParam(required = false, name = "error") Boolean errorValue,
            @RequestParam(required = false, name = "loggedOut") Boolean loggedOut,
            Model model
    ) {

        Boolean hasError = errorValue != null && errorValue;
        Boolean isLoggedOut = loggedOut != null && loggedOut;

        Map<String, Object> dataMap = new HashMap<>();

        dataMap.put("toLogin", true);
        dataMap.put("loginSuccessfully", false);
        dataMap.put("hasError", hasError);
        dataMap.put("isLoggedOut", isLoggedOut);

        model.addAllAttributes(dataMap);

        return "login";
    }

    @GetMapping("/signup")
    public String signupForm(
            @ModelAttribute("userDto") UserDTO userDTO,
            Model model
    ) {

        Map<String, Object> data = new HashMap<>();

        data.put("toSignUp", true);
        data.put("signupSuccessfully", false);
        data.put("hasError", false);

        model.addAllAttributes(data);

        return "signup";
    }

    @PostMapping("/signup")
    public String signupSubmit(
            @ModelAttribute("userDto") UserDTO userDTO,
            Model model
    ) {

        this.logger.error("Received user info from Signup Form: " + userDTO.toString());

        Map<String, Object> data = new HashMap<>();

        if (!this.authorizationService.signupUser(userDTO)) {

            data.put("toSignUp", true);
            data.put("signupSuccessfully", false);
            data.put("hasError", true);
        } else {
            data.put("toSignUp", false);
            data.put("signupSuccessfully", true);
            data.put("hasError", false);
        }

        model.mergeAttributes(data);

        return "signup";
    }

    @GetMapping("/result")
    public String showResult(
            Authentication authentication,
            @RequestParam(required = false, name = "isSuccess") Boolean isSuccess,
            @RequestParam(required = false, name = "errorType") Integer errorType,
            Model model
    ) {

        Map<String, Object> data = new HashMap<>();

        data.put("isSuccess", isSuccess);
        data.put("errorType", errorType);

        model.addAllAttributes(data);

        return "result";
    }
}