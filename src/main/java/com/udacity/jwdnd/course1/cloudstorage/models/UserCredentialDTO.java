package com.udacity.jwdnd.course1.cloudstorage.models;

public class UserCredentialDTO {

    private Integer credentialId;
    private Integer userid;
    private String url;
    private String username;
    private String key;
    private String password;
    private String decryptedPassword;

    public UserCredentialDTO() {
    }

    public UserCredentialDTO(
            Integer credentialId,
            Integer userid,
            String url,
            String username,
            String key,
            String password) {

        this.credentialId = credentialId;
        this.userid = userid;
        this.url = url;
        this.username = username;
        this.key = key;
        this.password = password;
    }

    public Integer getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Integer credentialId) {
        this.credentialId = credentialId;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userId) {
        this.userid = userId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDecryptedPassword() {
        return decryptedPassword;
    }

    public void setDecryptedPassword(String decryptedPassword) {
        this.decryptedPassword = decryptedPassword;
    }

    @Override
    public String toString() {
        return "UserCredentialDTO {" +
                "credentialId=" + credentialId +
                ", userid=" + userid +
                ", url='" + url + '\'' +
                ", username='" + username + '\'' +
                ", key='" + key + '\'' +
                ", password='" + password + '\'' +
                ", decryptedPassword='" + decryptedPassword + '\'' +
                '}';
    }
}