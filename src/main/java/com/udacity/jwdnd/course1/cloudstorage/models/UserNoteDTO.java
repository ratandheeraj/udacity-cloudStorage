package com.udacity.jwdnd.course1.cloudstorage.models;

public class UserNoteDTO {

    private Integer userid;
    private Integer noteId;
    private String noteTitle;
    private String noteDescription;

    public UserNoteDTO() {
    }

    public UserNoteDTO(Integer userid, Integer noteId, String noteTitle, String noteDescription) {
        this.userid = userid;
        this.noteId = noteId;
        this.noteTitle = noteTitle;
        this.noteDescription = noteDescription;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserId(Integer userId) {
        this.userid = userId;
    }

    public Integer getNoteId() {
        return noteId;
    }

    public void setNoteId(Integer noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteDescription() {
        return noteDescription;
    }

    public void setNoteDescription(String noteDescription) {
        this.noteDescription = noteDescription;
    }

    @Override
    public String toString() {
        return "UserNoteDTO{" +
                "userId=" + userid +
                ", noteId=" + noteId +
                ", noteTitle='" + noteTitle + '\'' +
                ", noteDescription='" + noteDescription + '\'' +
                '}';
    }
}