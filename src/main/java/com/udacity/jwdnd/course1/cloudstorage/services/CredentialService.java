package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserCredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.UserCredentialDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {

    private Logger logger = LoggerFactory.getLogger(CredentialService.class);

    @Autowired
    private EncryptionService encryptionService;
    @Autowired
    private CredentialMapper credentialMapper;
    @Autowired
    private UserCredentialMapper userCredentialMapper;

    public List<UserCredentialDTO> getCredentialsByUsername(String username) {

        List<UserCredentialDTO> userCredentialDTOList = this.userCredentialMapper.getCredentialsByUsername(username);

        return userCredentialDTOList.stream().peek(userCredentialDTO -> {
            String encodedKey = userCredentialDTO.getKey();
            String encryptedPassword = userCredentialDTO.getPassword();
            String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
            userCredentialDTO.setDecryptedPassword(decryptedPassword);
        }).collect(Collectors.toList());
    }

    public Boolean createOrUpdateCredential(UserCredentialDTO userCredentialDTO, String username) {

        String password = userCredentialDTO.getPassword();
        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(password, encodedKey);
        Integer credentialId = userCredentialDTO.getCredentialId();

        if (credentialId == null) {
            this.userCredentialMapper.createCredentialByUsername(
                    userCredentialDTO.getUrl(),
                    userCredentialDTO.getUsername(),
                    encodedKey,
                    encryptedPassword,
                    username);
        } else {
            this.credentialMapper.update(
                    userCredentialDTO.getUrl(),
                    userCredentialDTO.getUsername(),
                    encodedKey,
                    encryptedPassword,
                    credentialId);
        }

        return true;
    }

    public Boolean deleteCredential(Integer credentialId) {
        this.credentialMapper.deleteCredential(credentialId);
        return true;
    }
}