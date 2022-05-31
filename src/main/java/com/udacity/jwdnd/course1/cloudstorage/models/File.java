package com.udacity.jwdnd.course1.cloudstorage.models;

import java.util.Arrays;

public class File {
    private Integer fileid;
    private String filename;
    private String contenttype;
    private String filesize;
    private Integer userid;
    private byte[] filedata;

    public File() {
    }

    public File(String filename, String contenttype, String filesize, Integer userid, byte[] filedata) {
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public File(Integer fileId, String filename, String contenttype, String filesize, Integer userid, byte[] filedata) {
        this.fileid = fileId;
        this.filename = filename;
        this.contenttype = contenttype;
        this.filesize = filesize;
        this.userid = userid;
        this.filedata = filedata;
    }

    public Integer getFileId() {
        return fileid;
    }

    public void setFileId(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getContenttype() {
        return contenttype;
    }

    public void setContenttype(String contenttype) {
        this.contenttype = contenttype;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public byte[] getFiledata() {
        return filedata;
    }

    public void setFiledata(byte[] filedata) {
        this.filedata = filedata;
    }


    @Override
    public String toString() {
        return "File {" +
                "fileId=" + fileid +
                ", filename='" + filename + '\'' +
                ", contenttype='" + contenttype + '\'' +
                ", filesize='" + filesize + '\'' +
                ", userid=" + userid +
                ", filedata=" + Arrays.toString(filedata) +
                '}';
    }
}
