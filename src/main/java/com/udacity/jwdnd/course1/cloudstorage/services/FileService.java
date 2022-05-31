package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.FileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserFileMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.File;
import com.udacity.jwdnd.course1.cloudstorage.models.User;
import com.udacity.jwdnd.course1.cloudstorage.models.UserFileDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserFileMapper userFileMapper;
    @Autowired
    private UserMapper userMapper;


    public Boolean isFileNameAvailableForUser(String username, String filename) {
        Map<String, Object> objectMap = new HashMap<>();
        objectMap.put("username", username);
        objectMap.put("filename", filename);

        return this.userFileMapper.getFileByUsernameAndFileName(objectMap).isEmpty();
    }

    public List<UserFileDTO> getFilesByUser(String username) {
        return this.userFileMapper.getFileByUsername(username);
    }

    public Boolean saveFile(MultipartFile file, String username) throws IOException {

        User user = this.userMapper.getUserByUsername(username);
//        byte[] fileData = file.getBytes();
//        int userId = user.getUserid();
//        String contentType = file.getContentType();
//        String fileSize = String.valueOf(file.getSize());
//        String fileName = file.getOriginalFilename();
        File newFile = new File();
        try{
            newFile.setFileId(null);
            newFile.setFilename(file.getOriginalFilename());
            newFile.setContenttype(file.getContentType());
            newFile.setFiledata(file.getBytes());
            newFile.setUserid(user.getUserid());
            newFile.setFilesize(String.valueOf(file.getSize()));
        }catch (IOException ioException){
            ioException.printStackTrace();
        }
        this.fileMapper.createFile(newFile);

      //  this.fileMapper.createFile(new File(null, fileName, contentType, fileSize, userId, fileData));

        return true;
    }

    public Boolean deleteFile(Integer fileId) {

        this.fileMapper.deleteFile(fileId);

        return true;
    }

    public File getFileByFileId(Integer fileId) {
        return this.fileMapper.getFileById(fileId);
    }
}