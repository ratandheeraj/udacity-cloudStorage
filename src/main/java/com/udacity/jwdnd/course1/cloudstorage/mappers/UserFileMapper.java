package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.UserFileDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserFileMapper {

    List<UserFileDTO> getFileByUsername(String username);

    List<UserFileDTO> getFileByUsernameAndFileName(Map<String, Object> objectMap);
}