package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mappers.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.UserNoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.models.UserNoteDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    private final Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NoteMapper noteMapper;
    @Autowired
    private UserNoteMapper userNoteMapper;

    public List<UserNoteDTO> getNotesByUsername(String username) {
        return this.userNoteMapper.getNotesByUsername(username);
    }

    public Boolean insertOrUpdateNoteByUser(String username, UserNoteDTO userNoteDTO) {

        String noteTitle = userNoteDTO.getNoteTitle();
        String noteDescription = userNoteDTO.getNoteDescription();
        Integer noteId = userNoteDTO.getNoteId();

        if (noteId == null || noteId.toString().equals("")) {
            this.userNoteMapper.createNoteByUsername(username, noteTitle, noteDescription);
        } else {
            this.noteMapper.updateNote(noteTitle, noteDescription, noteId);
        }

        return true;
    }

    public Boolean deleteNoteByNoteId(Integer noteId, String username) {
        this.noteMapper.deleteNoteByNoteId(noteId);
        return true;
    }
}