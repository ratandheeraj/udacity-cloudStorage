package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserCredentialDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

import java.util.List;

@Mapper
public interface UserCredentialMapper {

    List<UserCredentialDTO> getCredentialsByUsername(String username);

    @Options(useGeneratedKeys = true, keyProperty = "credentialid")
    int createCredentialByUsername(String url, String usernameC, String key, String password, String username);
}