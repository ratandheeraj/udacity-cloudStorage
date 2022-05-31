package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserCredentialDTO;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/v1/credentials")
public class CredentialController {
    private final Logger logger = LoggerFactory.getLogger(CredentialController.class);

    @Autowired
    private CredentialService credentialService;

    @PostMapping("/credential")
    public String credentialSubmit(
        @ModelAttribute("userCredentialDto") UserCredentialDTO userCredentialDTO,
        Authentication authentication,
        Model model
    ) {
        String username = (String) authentication.getPrincipal();
        Boolean isSuccess = this.credentialService.createOrUpdateCredential(
                userCredentialDTO, username);
        return "redirect:/result?isSuccess=" + isSuccess;
    }

    @GetMapping("/credential")
    public String credentialDeletion(
        @ModelAttribute("userCredentialDto") UserCredentialDTO userCredentialDto,
        @RequestParam(required = false, name = "credentialId") Integer credentialId,
        Authentication authentication,
        Model model
    ) {
        this.logger.error("CredentialId: " + credentialId.toString());
        Boolean isSuccess = this.credentialService.deleteCredential(credentialId);
        return "redirect:/result?isSuccess=" + isSuccess;
    }
}