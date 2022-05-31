package com.udacity.jwdnd.course1.cloudstorage.mappers;

import com.udacity.jwdnd.course1.cloudstorage.models.File;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

@Mapper
public interface FileMapper {

    @Select("SELECT * FROM FILES")
    List<File> getAllFiles();

    @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
    File getFileById(Integer fileid);

    @Insert("INSERT INTO FILES(filename, contenttype, filesize, filedata, userid) VALUES (#{filename}, #{contenttype}, #{filesize}, #{filedata}, #{userid})")
    @Options(useGeneratedKeys = true, keyProperty = "fileid")
    int createFile(File file);

    @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
    void deleteFile(Integer fileid);
}